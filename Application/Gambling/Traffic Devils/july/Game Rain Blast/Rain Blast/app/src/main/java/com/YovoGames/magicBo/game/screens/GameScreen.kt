package com.YovoGames.magicBo.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.YovoGames.magicBo.game.LibGDXGame
import com.YovoGames.magicBo.game.actors.AButton
import com.YovoGames.magicBo.game.box2d.AbstractBody
import com.YovoGames.magicBo.game.box2d.BodyId
import com.YovoGames.magicBo.game.box2d.WorldUtil
import com.YovoGames.magicBo.game.box2d.bodies.BItem
import com.YovoGames.magicBo.game.utils.*
import com.YovoGames.magicBo.game.utils.actor.animHide
import com.YovoGames.magicBo.game.utils.actor.animShow
import com.YovoGames.magicBo.game.utils.actor.setOnClickListener
import com.YovoGames.magicBo.game.utils.advanced.AdvancedBox2dScreen
import com.YovoGames.magicBo.game.utils.advanced.AdvancedStage
import com.YovoGames.magicBo.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame) : AdvancedBox2dScreen(WorldUtil()) {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(38)
    private val font          = fontGenerator_KoHo.generateFont(fontParameter)

    // Actor
    private val resultPanelImg = Image(game.jakarta.bablo)
    private val aRecordLbl     = Label("0", Label.LabelStyle(font, Color.WHITE))
    private val aBack          = AButton(this, AButton.Static.Type.Back)

    // Body
    private val bItemList = List(32) { BItem(this) }

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(32)

    override fun show() {
        stageUI.root.x = -WIDTH_UI
        setBackBackground(game.surgut.Soloha.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Item()
        addEleven()
    }

    // Add
    private fun AdvancedStage.addEleven() {
        addActor(resultPanelImg)
        resultPanelImg.setBounds(33f, 15f, 222f, 68f)


        addActor(aBack)
        aBack.apply {
            setBounds(284f, 15f, 222f, 68f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(86f, 25f, 117f, 50f)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    var counter = 0

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.id = BodyId.ITEM
            bItem.create(0f, HEIGHT + 300, 110f, 110f)

            bItem.actor?.setOnClickListener {
                if (bItem.isOnStart.getAndSet(false)) {
                    game.soundUtil.apply { play(takeList.random(), 70f) }

                    counter += (1000..2000).random()
                    aRecordLbl.setText(counter)
                    itemFlow.tryEmit(bItem)
                }
            }

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if ((bItem.body?.position?.y ?: 0f) <= 0f) {
                        if (bItem.isOnStart.getAndSet(false)) {
                            itemFlow.tryEmit(bItem)
                        }
                    }
                }
            })

            itemFlow.tryEmit(bItem)
        }

        val startPos = Vector2()

        coroutine?.launch {
            itemFlow.collect { bItem ->
                bItem.body?.apply {
                    setLinearVelocity(0f, 0f)
                    isAwake = false
                    gravityScale = 0f

                    runGDX {
                        val xxx = (55..470).random().toFloat()
                        setTransform(startPos.set(xxx, HEIGHT + 300).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((90..350L).random())

                runGDX {
                    bItem.body?.apply {
                        gravityScale = 1f
                        isAwake = true
                        if (Random.nextBoolean()) applyTorque(6000f, false) else applyTorque(-6000f, false)
                        applyLinearImpulse(Vector2(0f, -2f), worldCenter, true)
                    }
                }
            }
        }

    }

}