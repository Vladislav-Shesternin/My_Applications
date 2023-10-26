package com.bettilt.mobile.pt.game.utils.advanced

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bettilt.mobile.pt.game.LibGDXGame
import com.bettilt.mobile.pt.game.box2d.AbstractBody
import com.bettilt.mobile.pt.game.box2d.AbstractJoint
import com.bettilt.mobile.pt.game.box2d.BodyId
import com.bettilt.mobile.pt.game.box2d.WorldUtil
import com.bettilt.mobile.pt.game.box2d.bodies.BStatic
import com.bettilt.mobile.pt.game.utils.actor.animHide
import com.bettilt.mobile.pt.game.utils.actor.animShow
import com.bettilt.mobile.pt.game.utils.actor.disable
import com.bettilt.mobile.pt.game.utils.toB2
import com.bettilt.mobile.pt.util.log

abstract class AdvancedMouseScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    val aTouchImg by lazy { Image(game.spriteUtil.TOUCH) }

    // Joint
    private val jMouse by lazy { AbstractJoint<MouseJoint, MouseJointDef>(this) }

    // Body
    private val bStatic by lazy { BStatic(this) }

    // Fields
    private val listenerForMouseJoint : InputListener by lazy { getInputListenerForMouseJoint() }
    private val listenerForTouch      : InputListener by lazy { getInputListenerForTouch() }

    override fun show() {
        super.show()

        stageUI.addMouseActorsOnStageUI()
        stageUI.addListener(listenerForMouseJoint)
        stageUI.addListener(listenerForTouch)
    }

    override fun dispose() {
        log("dispose AdvancedMouseScreen: ${this::class.java.name.substringAfterLast('.')}")
        super.dispose()
    }

    private fun AdvancedStage.addMouseActorsOnStageUI() {
        addTouchImg()
        createB_Static()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addTouchImg() {
        addActor(aTouchImg)
        aTouchImg.apply {
            disable()
            setSize(97f, 97f)
            animHide()
        }
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
            if (pointer != 0) return true

            touchPointInBox.set(tmpVector2.set(x, y).toB2)
            hitAbstractBody = null

            worldUtil.world.QueryAABB(callback,
                touchPointInBox.x - 0.01f,
                touchPointInBox.y - 0.01f,
                touchPointInBox.x + 0.01f,
                touchPointInBox.y + 0.01f)

            hitAbstractBody?.let {
                playSound_TouchDown()

                jMouse.create(MouseJointDef().apply {
                    bodyA = bStatic.body
                    bodyB = it.body
                    collideConnected = true

                    target.set(touchPointInBox)
                    maxForce = 500 * bodyB.mass
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
            hitAbstractBody?.let {
                playSound_TouchUp()
                jMouse.destroy()
            }
        }


        private fun playSound_TouchDown() {
            if (System.currentTimeMillis().minus(timeTouchDown) >= 200) {
                game.soundUtil.apply { play(TOUCH, 4) }
                timeTouchDown = System.currentTimeMillis()
            }
        }

        private fun playSound_TouchUp() {
            if (System.currentTimeMillis().minus(timeTouchDown) >= 500) game.soundUtil.apply { play(TOUCH,4) }
        }

    }

    private fun getInputListenerForTouch() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            if (pointer != 0) return true

            aTouchImg.apply {
                clearActions()
                animShow(0.3f)
                this.x = x - 48.5f
                this.y = y - 48.5f
            }

            return true
        }
        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            if (pointer != 0) return
            aTouchImg.apply {
                this.x = x - 48.5f
                this.y = y - 48.5f
            }
        }
        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (pointer != 0) return
            aTouchImg.apply {
                clearActions()
                animHide(0.3f)
            }
        }
    }

}