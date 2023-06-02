package com.veldan.gamebox2d.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.game.actors.button.AButton
import com.veldan.gamebox2d.game.actors.button.AButtonStyle
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.box2d.bodiesGroup.*
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.actor.setBounds
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.game.utils.runGDX
import com.veldan.gamebox2d.game.utils.vector2
import com.veldan.gamebox2d.util.log
import com.veldan.gamebox2d.game.utils.Layout.Game as LG

class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Actors
    private val upBtn    = AButton(AButtonStyle.btn)
    private val downBtn  = AButton(AButtonStyle.btn)
    private val leftBtn  = AButton(AButtonStyle.btn)
    private val rightBtn = AButton(AButtonStyle.btn)

    // Body

    // Joint
    private val jMouse = AbstractJoint<MouseJoint>(this)

    // BodyGroup
    private val bgBorders   = BGBorders(this)
    private val bgCar       = BGCar(this)
    private val bgRotate    = BGRotate(this)
    private val bgPrismatic = BGPrismatic(this)
    private val bgPulley    = BGPulley(this)
    private val bgGear      = BGGear(this)
    private val bgMotor     = BGMotor(this)
    private val bgFriction  = BGFriction(this)
    private val bgWeld      = BGWeld(this)
    private val bgWheel     = BGWheel(this)
    private val bgRope      = BGRope(this)


    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        // createBG_Car()
        // createBG_Rotate()
        // createBG_Prismatic()
        //createBG_Pulley()
        //createBG_Gear()
        //createBG_Motor()
        //createBG_Friction()
        //createBG_Weld()
        //createBG_Wheel()
        createBG_Rope()

        addBtns()

        mainGroup.addListener(getInputListener())
    }

    override fun dispose() {
        super.dispose()
        listOf<AbstractBodyGroup>(bgBorders, bgCar)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBtns() {
        runGDX {
            val listBlock = listOf<() -> Unit>(
                { bgCar.left() },
                { bgCar.right() },
                { bgCar.right() },
                { bgCar.right() },
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
        bgCar.create(Vector2(45f, 55f), Size(386f, 196f))
    }

    private fun createBG_Rotate() {
        bgRotate.create(Vector2(1013f, 327f), Size(22f, 118f))
    }

    private fun createBG_Prismatic() {
        bgPrismatic.create(Vector2(229f, 36f), Size(242f, 153f))
    }

    private fun createBG_Pulley() {
        bgPulley.create(Vector2(325f, 25f), Size(581f, 95f))
    }

    private fun createBG_Gear() {
        bgGear.create(Vector2(262f, 78f), Size(772f, 350f))
    }

    private fun createBG_Motor() {
        bgMotor.create(Vector2(470f, 133f), Size(192f, 192f))
    }

    private fun createBG_Friction() {
        bgFriction.create(Vector2(496f, 232f), Size(120f, 120f))
    }

    private fun createBG_Weld() {
        bgWeld.create(Vector2(496f, 232f), Size(259f, 120f))
    }

    private fun createBG_Wheel() {
        bgWheel.create(Vector2(416f, 125f), Size(206f, 199f))
    }

    private fun createBG_Rope() {
        bgRope.create(Vector2(608f, 208f), Size(43f, 359f))
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Distance() {

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun getInputListener() = object : InputListener() {

        var hitAbstractBody: AbstractBody? = null
        val touchPointInBox = Vector2()
        val callback   = QueryCallback { fixture ->
            if (fixture.testPoint(touchPointInBox)) {
                hitAbstractBody = fixture.body?.userData as AbstractBody
                return@QueryCallback false
            }
            return@QueryCallback true
        }

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchPointInBox.set(sizeConverterUIToBox.getSize(x, y).vector2)
            hitAbstractBody = null
            worldUtil.world.QueryAABB(callback, touchPointInBox.x - 0.01f, touchPointInBox.y - 0.01f, touchPointInBox.x + 0.01f, touchPointInBox.y + 0.01f)

            hitAbstractBody?.let {
                jMouse.create(MouseJointDef().apply {
                    bodyA = bgBorders.bDown.body
                    bodyB = it.body
                    target.set(touchPointInBox)
                    collideConnected = true
                    maxForce = 1000 * bodyB.mass
                })
            }
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            jMouse.joint?.target = sizeConverterUIToBox.getSize(x, y).vector2
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            jMouse.startDestroy()
        }
    }
}