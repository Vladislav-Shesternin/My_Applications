package com.doradogames.confli.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.doradogames.confli.game.LibGDXGame
import com.doradogames.confli.game.actors.AButton
import com.doradogames.confli.game.box2d.AbstractBody
import com.doradogames.confli.game.box2d.WorldUtil
import com.doradogames.confli.game.box2d.bodies.BItem
import com.doradogames.confli.game.utils.*
import com.doradogames.confli.game.utils.actor.animHide
import com.doradogames.confli.game.utils.actor.animShow
import com.doradogames.confli.game.utils.actor.setOnClickListener
import com.doradogames.confli.game.utils.advanced.AdvancedBox2dScreen
import com.doradogames.confli.game.utils.advanced.AdvancedStage
import com.doradogames.confli.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {
companion object {
     var RECORD = 0

}
    private val parmezan = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(62)
    private val font     = fontGenerator_Kanit.generateFont(parmezan)

    // Actor
    private val aBack      = AButton(this, AButton.Static.Type.Back)
    private val aRecordLbl = Label(RECORD.toString(), Label.LabelStyle(font, Color.WHITE))

    // Body
    private val bItemList = List(30) { BItem(this) }

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(30)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.load.Loading.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addAcatacors()
        createB_Item()
    }

    // Add
    private fun AdvancedStage.addAcatacors() {
        addActor(aBack)
        aBack.apply {
            setBounds(390f, 65f, 283f, 80f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(Image(game.assets.record).apply { setBounds(354f, 1703f, 356f, 166f) })
        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(354f, 1705f, 356f,94f)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.create(0f, HEIGHT+300, 215f, 215f)

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if((bItem.body?.position?.y ?: 0f) <= 0f) {
                        if (bItem.isOnStart.getAndSet(false)) {
                            itemFlow.tryEmit(bItem)
                        }
                    }
                }
            })

            itemFlow.tryEmit(bItem)

            bItem.actor?.setOnClickListener {
                if (bItem.isOnStart.getAndSet(false)) {

                    game.soundUtil.apply { play(fruit) }

                    RECORD += (1..10).random()
                    aRecordLbl.setText(RECORD)
                    itemFlow.tryEmit(bItem)
                }
            }
        }

        val startPos = Vector2()

        coroutine?.launch {
            itemFlow.collect { bItem ->
                bItem.body?.apply {
                    setLinearVelocity(0f, 0f)
                    isAwake      = false
                    gravityScale = 0f

                    runGDX {
                        setTransform(
                            startPos.set(
                                (90..800).random().toFloat(),
                                HEIGHT+300
                            ).toB2,
                            0f
                        )
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((50..320L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
                    applyLinearImpulse(Vector2(0f, -(10..20).random().toFloat()), worldCenter, true)
                }
            }
        }

    }

}