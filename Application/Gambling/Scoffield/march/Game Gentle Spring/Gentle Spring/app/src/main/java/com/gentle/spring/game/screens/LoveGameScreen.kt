package com.gentle.spring.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.gentle.spring.game.LibGDXGame
import com.gentle.spring.game.actors.AImage
import com.gentle.spring.game.box2d.AbstractBody
import com.gentle.spring.game.box2d.BodyId
import com.gentle.spring.game.box2d.WorldUtil
import com.gentle.spring.game.box2d.bodies.BHart
import com.gentle.spring.game.utils.actor.setOnClickListener
import com.gentle.spring.game.utils.advanced.AdvancedBox2dScreen
import com.gentle.spring.game.utils.advanced.AdvancedStage
import com.gentle.spring.game.utils.font.FontParameter
import com.gentle.spring.game.utils.region
import com.gentle.spring.game.utils.runGDX
import com.gentle.spring.game.utils.toB2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class LoveGameScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    private val hartImg = Image(game.allAssets.hart)


    private val param = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(30)
    private val font  = fontGenerator_JuaRegular.generateFont(param)

    private val lbl = Label("0", Label.LabelStyle(font, Color.WHITE))

    // Field
    private val hartFlow = MutableSharedFlow<BHart>(replay = 20)


    override fun show() {
        setBackBackground(game.loaderAssets.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addHartImg()

        createB_Hart()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addHartImg() {
        addActor(hartImg)
        hartImg.setBounds(14f, 11f, 55f, 55f)




        addActor(lbl)
        lbl.setBounds(80f, 11f, 50f, 38f)
        lbl.setAlignment(Align.center)
    }

    // ---------------------------------------------------
    // create Hart
    // ---------------------------------------------------
    var countHart = 0

    private fun createB_Hart() {
        repeat(20) {
            BHart(this).also { bHart ->

                bHart.renderBlockArray.add(AbstractBody.RenderBlock {
                    bHart.body?.let {
                        if (it.position.y <= -2f) {
                            if (bHart.atomicBoolean.getAndSet(false)) hartFlow.tryEmit(bHart)
                        }
                    }
                })

                bHart.actor?.setOnClickListener {
                    game.soundUtil.apply { play(BUBBLEBEAM) }
                    hartFlow.tryEmit(bHart)

                    countHart++
                    lbl.setText(countHart)
                }

                bHart.bodyDef.gravityScale  = 0f
                bHart.create(-200f, HEIGHT+200f, 100f, 100f)

                hartFlow.tryEmit(bHart)
            }
        }

        coroutine?.launch {
            hartFlow.collect { bHart ->
                runGDX {
                    bHart.body?.apply {
                        setTransform(Vector2(-200f, HEIGHT+200f).toB2, 0f)

                        setLinearVelocity(0f, 0f)
                        gravityScale = 0f
                        isAwake = false
                    }
                }
            }
        }
        coroutine?.launch {
            hartFlow.collect { bItem ->
                delay((200L..400L).random())
                runGDX {
                    bItem.body?.apply {
                        setTransform((50..1350).random().toFloat().toB2, (HEIGHT+100f).toB2, 0f)
                        gravityScale = 1f
                        isAwake      = true
                    }
                }
                delay(100)
                bItem.atomicBoolean.set(true)
            }
        }
    }

}