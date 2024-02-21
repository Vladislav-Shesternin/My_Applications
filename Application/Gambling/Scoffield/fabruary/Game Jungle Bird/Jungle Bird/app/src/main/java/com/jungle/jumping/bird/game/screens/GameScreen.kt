package com.jungle.jumping.bird.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.jungle.jumping.bird.game.actors.label.ALabelStyle
import com.jungle.jumping.bird.game.box2d.BodyId
import com.jungle.jumping.bird.game.box2d.WorldUtil
import com.jungle.jumping.bird.game.box2d.bodies.Border
import com.jungle.jumping.bird.game.box2d.bodies.Bird
import com.jungle.jumping.bird.game.manager.GameDataStoreManager
import com.jungle.jumping.bird.game.manager.NavigationManager
import com.jungle.jumping.bird.game.manager.SpriteManager
import com.jungle.jumping.bird.game.utils.BalkGenerator
import com.jungle.jumping.bird.game.utils.Once
import com.jungle.jumping.bird.game.utils.actor.setBounds
import com.jungle.jumping.bird.game.utils.advanced.AdvancedBox2dScreen
import com.jungle.jumping.bird.game.utils.advanced.AdvancedGroup
import com.jungle.jumping.bird.game.utils.advanced.AdvancedStage
import com.jungle.jumping.bird.game.utils.runGDX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.jungle.jumping.bird.game.utils.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    private var manBlock: () -> Unit = {}

    private val recordFlow = MutableStateFlow(0)

    private val balkGenerator = BalkGenerator(worldUtil, this)

    // Body
    private val borderTop    = Border(worldUtil)
    private val borderBottom = Border(worldUtil)
    private val bird         = Bird(worldUtil)

    // Actors
    private val recordLabel = Label(recordFlow.value.toString(), ALabelStyle.font_100)



    override fun show() {
        //setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine.launch {
            runGDX {
                val imgG   = AdvancedGroup()
                val image1 = Image(SpriteManager.GameRegion.BACKGROUND.region)
                val image2 = Image(SpriteManager.GameRegion.BACKGROUND.region)
                stageBack.addActor(imgG)
                imgG.setBounds(0f, 0f, Gdx.graphics.width.toFloat() * 2, Gdx.graphics.height.toFloat() * 2)
                imgG.addActors(image1, image2)

                image1.apply {
                    setBounds(0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

                }
                image2.apply {
                    setBounds(Gdx.graphics.width.toFloat(), 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
                }

                imgG.addAction(Actions.forever(Actions.sequence(
                    Actions.moveTo(-Gdx.graphics.width.toFloat(), 0f, 10f),
                    Actions.moveTo(0f, 0f),
                )))

                balkGenerator.initialize()
                addRecordLabel()
            }

            createBorders()
            createBird()
        }
    }

    override fun render(delta: Float) {
        super.render(delta)
        balkGenerator.render(delta)
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        manBlock()
        swing?.play(1f)
        return true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

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

    private fun createBird() {
        runGDX {
            bird.also { m ->
                m.bodyDef.fixedRotation = true

                m.initialize(
                    stageUI,
                    sizeConverterUIToBox,
                    sizeConverterBoxToUI,
                    LG.bird.position(),
                    LG.bird.size(),
                )

                var isDoor = false
                val onceBack = Once()
                m.setBeginContactBlock { enemy ->
                    when (enemy.id) {
                        BodyId.Balk   -> onceBack.once {
                            fail?.play(1f)
                            NavigationManager.back()
                        }
                        BodyId.Door   -> isDoor = true
                        else          -> {}
                    }
                }

                m.setEndContactBlock { enemy ->
                    when (enemy.id) {
                        BodyId.Door -> {
                            if (isDoor) {
                                bonus?.play(1f)
                                recordFlow.value += 1
                            }
                            isDoor = false
                        }
                        else        -> {}
                    }
                }

                manBlock = {
                    m.body.apply {
                        applyLinearImpulse(Vector2(0f, 200f), m.center, true)
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