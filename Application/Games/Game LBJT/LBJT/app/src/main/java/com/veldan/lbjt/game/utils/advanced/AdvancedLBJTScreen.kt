package com.veldan.lbjt.game.utils.advanced

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.box2d.AbstractJoint
import com.veldan.lbjt.game.box2d.WorldUtil
import com.veldan.lbjt.game.box2d.bodies.BStatic
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.actor.disable
import com.veldan.lbjt.game.utils.toB2
import space.earlygrey.shapedrawer.ShapeDrawer

abstract class AdvancedLBJTScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    val user = Image(SpriteManager.GameRegion.USER.region)

    // Joint
    private val jMouse = AbstractJoint<MouseJoint>(this)

    // Body
    private val bStatic = BStatic(this)


    override fun show() {
        super.show()
        stageUI.addLBJTActorsOnStageUI()
        mainGroup.addListener(getInputListenerForMouseJoint())
        mainGroup.addListener(getInputListenerForUser())
    }

    private fun AdvancedStage.addLBJTActorsOnStageUI() {
        addUser()

        createB_Static()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addUser() {
        addActor(user)
        user.apply {
            disable()
            setSize(87f, 107f)
            animHide()
        }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        bStatic.create(0f, 0f, 1f, 1f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun getInputListenerForMouseJoint() = object : InputListener() {

        var hitAbstractBody: AbstractBody? = null
        val touchPointInBox = Vector2()
        val callback        = QueryCallback { fixture ->
            if (fixture.testPoint(touchPointInBox)) {
                hitAbstractBody = fixture.body?.userData as AbstractBody
                return@QueryCallback false
            }
            return@QueryCallback true
        }

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchPointInBox.set(Vector2(x, y).toB2)
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
                    maxForce = 1000 * bodyB.mass
                })
            }
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            jMouse.joint?.target = Vector2(x, y).toB2
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            jMouse.startDestroy()
        }
    }

    private fun getInputListenerForUser() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            user.apply {
                clearActions()
                animShow(0.3f)
                this.x = x - 29f
                this.y = y - 102f
            }
            return true
        }
        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            user.apply {
                this.x = x - 29f
                this.y = y - 102f
            }
        }
        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            user.apply {
                clearActions()
                animHide(0.3f)
            }
        }
    }

}