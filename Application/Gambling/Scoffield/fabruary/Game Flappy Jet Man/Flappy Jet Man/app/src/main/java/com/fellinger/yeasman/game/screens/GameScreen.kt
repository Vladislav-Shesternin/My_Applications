package com.fellinger.yeasman.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.fellinger.yeasman.game.actors.JetAnim
import com.fellinger.yeasman.game.actors.label.ALabelStyle
import com.fellinger.yeasman.game.box2d.BodyId
import com.fellinger.yeasman.game.box2d.WorldUtil
import com.fellinger.yeasman.game.box2d.bodies.Border
import com.fellinger.yeasman.game.box2d.bodies.Man
import com.fellinger.yeasman.game.manager.GameDataStoreManager
import com.fellinger.yeasman.game.manager.NavigationManager
import com.fellinger.yeasman.game.manager.SpriteManager
import com.fellinger.yeasman.game.utils.BalkGenerator
import com.fellinger.yeasman.game.utils.Once
import com.fellinger.yeasman.game.utils.actor.setBounds
import com.fellinger.yeasman.game.utils.advanced.AdvancedBox2dScreen
import com.fellinger.yeasman.game.utils.advanced.AdvancedStage
import com.fellinger.yeasman.game.utils.runGDX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.fellinger.yeasman.game.utils.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    private var manBlock: () -> Unit = {}

    private val recordFlow = MutableStateFlow(0)

    private val balkGenerator = BalkGenerator(worldUtil, this)

    // Body
    private val borderTop    = Border(worldUtil, Border.Type.TOP)
    private val borderBottom = Border(worldUtil, Border.Type.BOTTOM)

    private val man = Man(worldUtil)

    // Actors
    private val manAnim     = JetAnim()
    private val recordLabel = Label(recordFlow.value.toString(), ALabelStyle.red_100)



    override fun show() {
        super.show()
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine.launch {
            addJetAnim()

            runGDX { balkGenerator.initialize() }

            addRecordLabel()
            createBorders()
            createMan()
        }
    }

    override fun render(delta: Float) {
        super.render(delta)
        balkGenerator.render(delta)
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        manBlock()
        return true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private suspend fun AdvancedStage.addJetAnim() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            addActor(manAnim)
            manAnim.apply {
                setBounds(LG.jet)
                addAction(Actions.sequence(
                    Actions.alpha(0f),
                    Actions.fadeIn(1f),
                    Actions.run { continuation.resume(Unit) }
                ))
            }
        }
    }

    private fun AdvancedStage.addRecordLabel() {
        runGDX {
            addActor(recordLabel)
            recordLabel.apply {
                setBounds(LG.record)
                setAlignment(Align.center)
                addAction(Actions.sequence(
                    Actions.alpha(0f),
                    Actions.fadeIn(1f),
                    Actions.run { coroutine.launch { collectRecord() } }
                ))
            }
        }
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createBorders() {
        runGDX {
            borderTop.also { border ->
                border.initialize(
                    stageUI,
                    sizeConverterUIToBox,
                    sizeConverterBoxToUI,
                    LG.borderTop.position(),
                    LG.borderTop.size(),
                )
            }
            borderBottom.also { border ->
                border.initialize(
                    stageUI,
                    sizeConverterUIToBox,
                    sizeConverterBoxToUI,
                    LG.borderBottom.position(),
                    LG.borderBottom.size(),
                )
            }
        }
    }

    private fun createMan() {
        runGDX {
            man.also { m ->
                m.bodyDef.fixedRotation = true

                m.initialize(
                    stageUI,
                    sizeConverterUIToBox,
                    sizeConverterBoxToUI,
                    LG.man.position(),
                    LG.man.size(),
                )

                m.setRenderBlock {
                    manAnim.x = sizeConverterBoxToUI.getSizeX(m.body.position.x - m.center.x) - 110f
                    manAnim.y = sizeConverterBoxToUI.getSizeY(m.body.position.y - m.center.y) - 32f
                }
                var i = 0
                m.setEndContactBlock { enemy ->
                    when (enemy.id) {
                        BodyId.Door -> {
                            proshel?.play(1f)
                            if (++i % 4 == 0) recordFlow.value += 1
                        }
                        else        -> {}
                    }
                }
                val onceBack = Once()
                m.setBeginContactBlock { enemy ->
                    when (enemy.id) {
                        BodyId.Balk   -> onceBack.once {
                            error?.play(1f)
                            NavigationManager.back()
                        }

                        BodyId.Border -> onceBack.once {
                            error?.play(1f)
                            NavigationManager.back()
                        }

                        else          -> {}
                    }
                }

                manBlock = {
                    m.body.apply {
                        happy_pop?.play(1f)
                        applyLinearImpulse(Vector2(0f, 25f), m.center, true)
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private suspend fun collectRecord() {
        recordFlow.collect { record ->
            runGDX { recordLabel.setText(record) }
            withContext(Dispatchers.IO) { GameDataStoreManager.Record.update { if (record > (it ?: 0)) record else (it ?: 0) } }
        }
    }

}