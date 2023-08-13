package com.zeuse.zeusjackpotjamboree.game.utils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.zeuse.zeusjackpotjamboree.game.box2d.AbstractBody
import com.zeuse.zeusjackpotjamboree.game.box2d.AbstractJoint
import com.zeuse.zeusjackpotjamboree.game.box2d.WorldUtil
import com.zeuse.zeusjackpotjamboree.game.box2d.bodies.BDynamic
import com.zeuse.zeusjackpotjamboree.game.box2d.bodies.BStatic
import com.zeuse.zeusjackpotjamboree.game.box2d.bodies.BZeus
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedBox2dScreen
import com.zeuse.zeusjackpotjamboree.util.log

class ControllerMouseUtil(
    val screenBox2d: AdvancedBox2dScreen,
    val bZeus: BZeus
) {

    // Body
    private val bStatic = BStatic(screenBox2d)

    // Joint
    private val jMouse = AbstractJoint<MouseJoint, MouseJointDef>(screenBox2d)

    // Fields
    private val listenerForMouseJoint : InputListener = getInputListenerForMouseJoint()



    fun initialize(block: () -> Unit = {}) {
        createB_Static(block)
        screenBox2d.stageUI.root.addListener(listenerForMouseJoint)
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static(block: () -> Unit = {}) {
        bStatic.requestToCreate(Vector2(0f, 0f), Vector2(1f, 1f), block)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun getInputListenerForMouseJoint() = object : InputListener() {

        var hitAbstractBody: AbstractBody? = null
        val touchPointInBox = Vector2()
//        val callback        = QueryCallback { fixture ->
//            if (fixture.testPoint(touchPointInBox)) {
//                hitAbstractBody = fixture.body?.userData as AbstractBody
//                return@QueryCallback false
//            }
//            return@QueryCallback true
//        }

        var timeTouchDown = 0L

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            if (pointer != 0) return true

            timeTouchDown = System.currentTimeMillis()

            touchPointInBox.set(Vector2(x, y).toB2)
            hitAbstractBody = bZeus

//            worldUtil.world.QueryAABB(callback,
//                touchPointInBox.x - 0.01f,
//                touchPointInBox.y - 0.01f,
//                touchPointInBox.x + 0.01f,
//                touchPointInBox.y + 0.01f)

            hitAbstractBody?.let {
                jMouse.requestToCreate(MouseJointDef().apply {
                    bodyA = bStatic.body
                    bodyB = it.body
                    collideConnected = true

                    target.set(bodyB.position)
                    maxForce     = 1000 * bodyB.mass
                    frequencyHz  = 0.5f
                    dampingRatio = 0.2f
                })

                it.actor?.blockPreDraw = { alpha -> jMouse.joint?.run {
                    screenBox2d.drawerUtil.drawer.line(anchorB.toUI, target.toUI, GameColor.yellow.apply { a = alpha }, 3f)
                } }

            }

            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            if (pointer != 0) return
            jMouse.joint?.target = Vector2(x, y).toB2
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (pointer != 0) return
            hitAbstractBody?.let { jMouse.requestToDestroy() }
        }
    }

}