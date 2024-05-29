package com.orange.magic.board.doodle.color.game.utils.advanced

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.orange.magic.board.doodle.color.game.LidaGame
import com.orange.magic.board.doodle.color.game.box2d.AbstractBody
import com.orange.magic.board.doodle.color.game.box2d.AbstractJoint
import com.orange.magic.board.doodle.color.game.box2d.BodyId
import com.orange.magic.board.doodle.color.game.box2d.WorldUtil
import com.orange.magic.board.doodle.color.game.box2d.bodies.BStatic
import com.orange.magic.board.doodle.color.game.utils.toB2
import com.orange.magic.board.doodle.color.util.log

abstract class AdvancedUserScreen(override val game: LidaGame): AdvancedBox2dScreen(WorldUtil()) {

    // Joint
    private val jMouse by lazy { AbstractJoint<MouseJoint, MouseJointDef>(this) }

    // Body
    private val bStatic by lazy { BStatic(this) }

    // Fields
    private val listenerForMouseJoint : InputListener by lazy { getInputListenerForMouseJoint() }

    protected var maxForce     = 1000f
    protected var frequencyHz  = 10.0f
    protected var dampingRatio = 0.1f

    override fun show() {
        super.show()

        addMouseActorsOnStageUI()
        stageUI.addListener(listenerForMouseJoint)
    }

    override fun dispose() {
        super.dispose()
        log("dispose AdvancedMouseScreen: ${this::class.java.name.substringAfterLast('.')}")
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
                (fixture.body?.userData as AbstractBody).id == BodyId.MAL
            ) {
                hitAbstractBody = fixture.body?.userData as AbstractBody
                return@QueryCallback false
            }
            return@QueryCallback true
        }

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            if (pointer != 0) return true

            touchPointInBox.set(tmpVector2.set(x, y).toB2)
            hitAbstractBody = null

            worldUtil.world.QueryAABB(callback,
                touchPointInBox.x - 0.01f,
                touchPointInBox.y - 0.01f,
                touchPointInBox.x + 0.01f,
                touchPointInBox.y + 0.01f)

            hitAbstractBody?.let {
                jMouse.create(MouseJointDef().apply {
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
            if (pointer != 0) return
            jMouse.joint?.target = tmpVector2.set(x, y).toB2
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (pointer != 0) return
            hitAbstractBody?.let { jMouse.destroy() }
        }
    }

}