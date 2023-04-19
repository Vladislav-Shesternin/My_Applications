package com.veldan.gamebox2d.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.actors.button.AButton
import com.veldan.gamebox2d.game.actors.button.AButtonStyle
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.box2d.bodies.BBorders
import com.veldan.gamebox2d.game.box2d.bodies.BOrb
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.actor.disable
import com.veldan.gamebox2d.game.utils.actor.setBounds
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.game.utils.runGDX
import com.veldan.gamebox2d.util.log
import com.veldan.gamebox2d.game.utils.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Actors
    private val upBtn    = AButton(AButtonStyle.btn)
    private val downBtn  = AButton(AButtonStyle.btn)
    private val leftBtn  = AButton(AButtonStyle.btn)
    private val rightBtn = AButton(AButtonStyle.btn)

    // Body
    private val bBorders = BBorders(this)
    private val bOrbG    = BOrb(this, BOrb.Type.GREEN)
    private val bOrbR    = BOrb(this, BOrb.Type.RED)

    // Joint
    private val jOrbG_OrbR = AbstractJoint<MouseJoint>(this)


    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Borders()
        createB_Orb()
       // addUpBtns()
        
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addUpBtns() {
        runGDX {
            val listBlock = listOf<() -> Unit>(
                { bOrbG.body?.applyLinearImpulse(Vector2(-20f, 0f), bOrbG.body?.worldCenter, true) },
                { bOrbG.body?.applyLinearImpulse(Vector2(0f, 50f),  bOrbG.body?.worldCenter,true) },
                { bOrbG.body?.applyLinearImpulse(Vector2(20f, 0f),  bOrbG.body?.worldCenter,true) },
                { bOrbG.body?.applyLinearImpulse(Vector2(0f, -50f), bOrbG.body?.worldCenter, true) },
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
    private fun createB_Borders() {
        bBorders.create(LG.borders)
        bBorders.actor.disable()
    }

    private fun createB_Orb() {
       // bOrbG.bodyDef.type = BodyDef.BodyType.KinematicBody
        bOrbG.create(LG.orbPos, LG.orbSize)
        bOrbG.actor.disable()

       // bOrbR.bodyDef.type = BodyDef.BodyType.KinematicBody
        bOrbR.create(LG.orbPos.add(100f, 0f), LG.orbSize)
        bOrbR.actor.disable()
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

}