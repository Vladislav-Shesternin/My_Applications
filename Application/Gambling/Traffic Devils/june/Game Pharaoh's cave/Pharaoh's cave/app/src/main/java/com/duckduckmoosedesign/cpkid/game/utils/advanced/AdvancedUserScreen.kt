package com.duckduckmoosedesign.cpkid.game.utils.advanced

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.duckduckmoosedesign.cpkid.game.box2d.AbstractBody
import com.duckduckmoosedesign.cpkid.game.box2d.AbstractJoint
import com.duckduckmoosedesign.cpkid.game.box2d.BodyId
import com.duckduckmoosedesign.cpkid.game.box2d.WorldUtil
import com.duckduckmoosedesign.cpkid.game.box2d.bodies.BStatic
import com.duckduckmoosedesign.cpkid.game.utils.currentTimeMinus
import com.duckduckmoosedesign.cpkid.game.utils.toB2
import com.duckduckmoosedesign.cpkid.util.log

abstract class AdvancedUserScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Joint
    private val jMouse by lazy { List(2) { AbstractJoint<MouseJoint, MouseJointDef>(this) } }

    // Body
    private val bStatic by lazy { BStatic(this) }

    // Fields
    protected val listenerForMouseJoint : InputListener by lazy { getInputListenerForMouseJoint() }

    private var maxForce     = 1000f
    private var frequencyHz  = 5.0f
    private var dampingRatio = 0.7f

    override fun show() {
        super.show()

        addMouseActorsOnStageUI()
        stageUI.addListener(listenerForMouseJoint)
    }

    override fun dispose() {
        log("dispose AdvancedMouseScreen: ${this::class.java.name.substringAfterLast('.')}")
        super.dispose()
    }

    private fun addMouseActorsOnStageUI() {
        createB_Static()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        bStatic.create(0f, 0f,1f,1f)
    }

    // ------------------------------------------------------------------------
    // Listener
    // ------------------------------------------------------------------------

    private fun getInputListenerForMouseJoint() = object : InputListener() {

        var hitAbstractBody: AbstractBody? = null
        val touchPointInBox = Vector2()
        val tmpVector2      = Vector2()

        val callback        = QueryCallback { fixture ->
            if (
                fixture.isSensor.not() &&
                fixture.testPoint(touchPointInBox) &&
                (fixture.body?.userData as AbstractBody).id != BodyId.NONE
            ) {
                hitAbstractBody = fixture.body?.userData as AbstractBody
                return@QueryCallback false
            }
            return@QueryCallback true
        }

        var timeTouchDown = 0L

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            if (pointer > 1) return true

            touchPointInBox.set(tmpVector2.set(x, y).toB2)
            hitAbstractBody = null

            worldUtil.world.QueryAABB(callback,
                touchPointInBox.x - 0.01f,
                touchPointInBox.y - 0.01f,
                touchPointInBox.x + 0.01f,
                touchPointInBox.y + 0.01f)

            hitAbstractBody?.let {
               // playSound_TouchDown()

                jMouse[pointer].create(MouseJointDef().apply {
                    bodyA = bStatic.body
                    bodyB = it.body
                    collideConnected = true

                    target.set(touchPointInBox)

                    maxForce     = this@AdvancedUserScreen.maxForce * bodyB.mass
                    frequencyHz  = this@AdvancedUserScreen.frequencyHz
                    dampingRatio = this@AdvancedUserScreen.dampingRatio
                })
            }

            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            if (pointer > 1) return
            jMouse[pointer].joint?.target = tmpVector2.set(x, y).toB2
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (pointer > 1) return
            hitAbstractBody?.let {
                //playSound_TouchUp()
                jMouse[pointer].destroy()
            }
        }


//        private fun playSound_TouchDown() {
//            if (currentTimeMinus(timeTouchDown) >= 202) {
//                game.soundUtil.apply { play() }
//                timeTouchDown = System.currentTimeMillis()
//            }
//        }
//
//        private fun playSound_TouchUp() {
//            if (currentTimeMinus(timeTouchDown) >= 405) game.soundUtil.apply { play(touch) }
//        }

    }

}