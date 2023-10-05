package com.lohina.titantreasuretrove.game.utils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.lohina.titantreasuretrove.game.box2d.AbstractBody
import com.lohina.titantreasuretrove.game.box2d.AbstractJoint
import com.lohina.titantreasuretrove.game.box2d.BodyId
import com.lohina.titantreasuretrove.game.box2d.bodies.BDynamic
import com.lohina.titantreasuretrove.game.box2d.bodies.BLightning
import com.lohina.titantreasuretrove.game.box2d.bodies.BStatic
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedBox2dScreen
import com.lohina.titantreasuretrove.util.log
import java.util.concurrent.atomic.AtomicBoolean

class LightningUtil(val screenBox2d: AdvancedBox2dScreen) {

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

        val callback        = QueryCallback { fixture ->
            if (fixture.testPoint(touchPointInBox) && (fixture.body.userData as AbstractBody).id == BodyId.Game.ZEUS) {
                val bLightning = BLightning(screenBox2d).apply {
                    id = BodyId.Game.ZEUS
                    collisionList.add(BodyId.Game.ITEM)
                    fixtureDef.density = 15f

                    var timer = 0f
                    val isTrue = AtomicBoolean(true)
                    renderBlock = { time ->
                        timer += time
                        if (timer >= 0.5) {
                            timer = 0f
                            body?.let { body -> if (body.position.y <= 0) { if (isTrue.getAndSet(false)) { requestToDestroy() } } }
                        }
                    }
                }

                bLightning.requestToCreate(touchPointInBox.toUI, Vector2(77f, 89f)) {
                    hitAbstractBody = bLightning

                    jMouse.requestToCreate(MouseJointDef().apply {
                        bodyA = bStatic.body
                        bodyB = hitAbstractBody?.body
                        collideConnected = true

                        target.set(bodyB.position)
                        maxForce = 1000 * bodyB.mass
                    })
                }

                return@QueryCallback false
            }
            return@QueryCallback true
        }

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            if (pointer != 0) return true

            touchPointInBox.set(Vector2(x, y).toB2)

            screenBox2d.worldUtil.world.QueryAABB(callback,
                touchPointInBox.x - 0.01f,
                touchPointInBox.y - 0.01f,
                touchPointInBox.x + 0.01f,
                touchPointInBox.y + 0.01f)

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