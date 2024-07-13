package com.bitmango.go.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.bitmango.go.game.LibGDXGame
import com.bitmango.go.game.actors.AButton
import com.bitmango.go.game.actors.checkbox.ACheckBox
import com.bitmango.go.game.box2d.AbstractBody
import com.bitmango.go.game.box2d.WorldUtil
import com.bitmango.go.game.box2d.bodies.BItem
import com.bitmango.go.game.utils.*
import com.bitmango.go.game.utils.actor.animHide
import com.bitmango.go.game.utils.actor.animShow
import com.bitmango.go.game.utils.actor.setOnClickListener
import com.bitmango.go.game.utils.advanced.AdvancedBox2dScreen
import com.bitmango.go.game.utils.advanced.AdvancedStage
import com.bitmango.go.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame) : AdvancedBox2dScreen(WorldUtil()) {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(33)
    private val font          = fontGenerator_Hubballi.generateFont(fontParameter)

    // Actor
    private val panelImg   = Image(game.bbb.weq)
    private val aRecordLbl = Label("0", Label.LabelStyle(font, Color.valueOf("FBF7A7")))
    private val aBack      = AButton(this, AButton.Static.Type.Back)
    private val cbPAUSE    = ACheckBox(this, ACheckBox.Static.Type.PAUSE)

    // Body
    private val bItemList = List(10) { BItem(this) }

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(10)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aaa.back.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Item()
        addUrbanizm()
    }

    // Add
    private fun AdvancedStage.addUrbanizm() {
        addActor(panelImg)
        panelImg.setBounds(153f, 854f, 234f, 94f)

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(227f, 879f, 87f, 46f)
            setAlignment(Align.center)
        }

        addActor(aBack)
        aBack.apply {
            setBounds(395f, 12f, 131f, 54f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(cbPAUSE)
        cbPAUSE.apply {
            setBounds(431f, 866f, 69f, 71f)
            setOnCheckListener { isPauseWorld = it }
        }

    }

    // Create Body ------------------------------------------------------------------------

    private var ser = 0

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.create(0f, HEIGHT + 100, 90f, 90f)

            bItem.actor?.setOnClickListener {
                if (bItem.isOnStart.getAndSet(false)) {
                    game.soundUtil.apply {
                        game.soundUtil.apply { play(choice, 25f) }
                    }

                    ser += (50..555).random()
                    aRecordLbl.setText(ser)
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
                        val xxx = (45..495).random().toFloat()
                        setTransform(startPos.set(xxx, HEIGHT + 45).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((400..632L).random())

                runGDX {
                    bItem.body?.apply {
                        gravityScale = 1f
                        isAwake = true
                        if (Random.nextBoolean()) applyTorque(10f, false) else applyTorque(-10f, false)
                        applyLinearImpulse(Vector2(0f, -1f), worldCenter, true)
                    }
                }
            }
        }

    }

}