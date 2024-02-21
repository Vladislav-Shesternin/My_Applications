package com.elastic.couben.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.elastic.couben.game.actors.button.AButton
import com.elastic.couben.game.actors.button.AButtonStyle
import com.elastic.couben.game.actors.label.ALabelStyle
import com.elastic.couben.game.box2d.BodyId
import com.elastic.couben.game.box2d.WorldUtil
import com.elastic.couben.game.box2d.bodies.BBorders
import com.elastic.couben.game.box2d.bodies.BOrb
import com.elastic.couben.game.box2d.bodiesGroup.BGTest
import com.elastic.couben.game.utils.Size
import com.elastic.couben.game.utils.actor.disable
import com.elastic.couben.game.utils.actor.setBounds
import com.elastic.couben.game.utils.advanced.AdvancedBox2dScreen
import com.elastic.couben.game.utils.advanced.AdvancedStage
import com.elastic.couben.game.utils.runGDX
import com.elastic.couben.utils.log
import kotlinx.coroutines.launch
import com.elastic.couben.game.utils.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Actors
    private val upBtn    = AButton(AButtonStyle.btn)
    private val downBtn  = AButton(AButtonStyle.btn)
    private val leftBtn  = AButton(AButtonStyle.btn)
    private val rightBtn = AButton(AButtonStyle.btn)

    private val progressLabel = Label("0", ALabelStyle.style(ALabelStyle.Roboto._40))

    // Body
    private val bBorders = BBorders(this)
    private val bgTest   = BGTest(this)

    private val orb = BOrb(this, BOrb.Type.GREEN)


    var i = 0

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine.launch {
            createBBorders()
            createBGTest()

            addProgress()

            createOrb()

            addUpBtns()

        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addUpBtns() {
        runGDX {
            val listBlock = listOf<() -> Unit>(
                { bgTest.left()   },
                { bgTest.top()    },
                { bgTest.right()  },
                { bgTest.bottom() },
            )

            val angleList = listOf(90f, 0f, -90f, 180f)
            listOf(leftBtn, upBtn, rightBtn, downBtn).onEachIndexed { index, btn ->
                addActor(btn)
                btn.apply {
                    setBounds(LG.btns[index])
                    setOrigin(Align.center)
                    rotation = angleList[index]
                    setOnClickListener {
                        cartoon_jump?.play(1f)
                        listBlock[index].invoke()
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addProgress() {
        runGDX {
            addActor(progressLabel)
            progressLabel.setAlignment(Align.center)
            progressLabel.setBounds(LG.progress)
        }
    }

    // ------------------------------------------------------------------------
    // Create Bodies
    // ------------------------------------------------------------------------
    private fun createBBorders() {
        runGDX {
            bBorders.create(Vector2(LG.borders.x, LG.borders.y), Size(LG.borders.w, LG.borders.h),)

            bBorders.beginContactBlock = {
                if (currentTimeMinus(timeContactWin) >= 100) {
                    knocking?.play(0.1f)
                    timeContactWin = System.currentTimeMillis()
                }
            }
        }
    }

    private fun createBGTest() {
        runGDX { bgTest.create(Vector2(281f, 140f)) }
    }

    private var timeContactWin = 0L

    private fun createOrb() {
        runGDX {
            val posX = (26..1260).shuffled().first().toFloat()
            val posY = (26..545).shuffled().first().toFloat()

            orb.id = BodyId.MOMKA
            orb.collisionList.add(BodyId.NONE)
            orb.create(Vector2(posX, posY), Size(125f, 125f))
            orb.actor.disable()


            orb.beginContactBlock = {

                if (it.id == BodyId.NONE) {
                    runGDX {
                        log("ss =")
                        val posX1 = (26..1260).shuffled().first().toFloat()
                        val posY1 = (26..545).shuffled().first().toFloat()

                        i++
                        progressLabel.setText(i)
                        if (currentTimeMinus(timeContactWin) >= 100) {
                            message?.play(0.5f)
                            timeContactWin = System.currentTimeMillis()
                        }

                        orb.body.setTransform(
                            sizeConverterUIToBox.getSize(Vector2(posX1, posY1)),
                            0f
                        )
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    fun currentTimeMinus(time: Long) = System.currentTimeMillis().minus(time)


}