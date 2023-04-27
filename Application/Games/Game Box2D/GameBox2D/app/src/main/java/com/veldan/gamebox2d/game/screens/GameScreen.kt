package com.veldan.gamebox2d.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.actors.button.AButton
import com.veldan.gamebox2d.game.actors.button.AButtonStyle
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.box2d.bodiesGroup.BGBorders
import com.veldan.gamebox2d.game.box2d.bodiesGroup.BGCar
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.actor.setBounds
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.game.utils.runGDX
import com.veldan.gamebox2d.game.utils.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Actors
    private val upBtn    = AButton(AButtonStyle.btn)
    private val downBtn  = AButton(AButtonStyle.btn)
    private val leftBtn  = AButton(AButtonStyle.btn)
    private val rightBtn = AButton(AButtonStyle.btn)

    // Body

    // Joint

    // BodyGroup
    private val bgBorders = BGBorders(this)
    private val bgCar     = BGCar(this)


    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_Car()

        createB_Orb()
        createJ_Distance()

        addBtns()

    }

    override fun dispose() {
        super.dispose()
        listOf<AbstractBodyGroup>(bgBorders)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBtns() {
        runGDX {
            val listBlock = listOf<() -> Unit>(
                { bgCar.ddd() }
              //  { bOrbG.body?.applyLinearImpulse(Vector2(-20f, 0f), bOrbG.body?.worldCenter, true) },
              // { bOrbG.body?.applyLinearImpulse(Vector2(0f, 50f),  bOrbG.body?.worldCenter,true) },
              // { bOrbG.body?.applyLinearImpulse(Vector2(20f, 0f),  bOrbG.body?.worldCenter,true) },
              // { bOrbG.body?.applyLinearImpulse(Vector2(0f, -50f), bOrbG.body?.worldCenter, true) },
            )

            val angleList = listOf(90f, 0f, -90f, 180f)
            listOf(leftBtn, upBtn, rightBtn, downBtn).onEachIndexed { index, btn ->
                addActor(btn)
                btn.apply {
                    setBounds(LG.btns[index])
                    setOrigin(Align.center)
                    rotation = angleList[index]
                    setOnClickListener { listBlock[index].invoke() }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Orb() {

    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    
    private fun createBG_Borders() {
        bgBorders.create(Vector2(0f, 0f), Size(WIDTH, HEIGHT))
    }

    private fun createBG_Car() {
        bgCar.create(Vector2(45f, 15f), Size(386f, 196f))
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Distance() {

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

}