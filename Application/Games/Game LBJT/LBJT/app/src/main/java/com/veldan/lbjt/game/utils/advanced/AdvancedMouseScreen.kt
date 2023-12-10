package com.veldan.lbjt.game.utils.advanced

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.box2d.AbstractBody
import com.veldan.lbjt.game.box2d.AbstractJoint
import com.veldan.lbjt.game.box2d.BodyId
import com.veldan.lbjt.game.box2d.WorldUtil
import com.veldan.lbjt.game.box2d.bodies.standart.BStatic
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.actor.disable
import com.veldan.lbjt.game.utils.currentTimeMinus
import com.veldan.lbjt.game.utils.toB2
import com.veldan.lbjt.util.Once
import com.veldan.lbjt.util.log
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class AdvancedMouseScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    val user by lazy { Image(game.themeUtil.assets.USER) }

    // Joint
    private val jMouse by lazy { AbstractJoint<MouseJoint, MouseJointDef>(this) }

    // Body
    private val bStatic by lazy { BStatic(this) }

    // Fields
    protected val listenerForMouseJoint : InputListener by lazy { getInputListenerForMouseJoint() }
    private val listenerForUser         : InputListener by lazy { getInputListenerForUser() }

    private val onceUserFirstTouch = Once()
    val isUserFirstTouchFlow       = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    override fun show() {
        super.show()

        stageUI.addMouseActorsOnStageUI()
        stageUI.addListener(listenerForMouseJoint)
        stageUI.addListener(listenerForUser)
    }

    override fun dispose() {
        log("dispose AdvancedMouseScreen: ${this::class.java.name.substringAfterLast('.')}")
        super.dispose()
    }

    private fun AdvancedStage.addMouseActorsOnStageUI() {
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
                    maxForce = 1000 * bodyB.mass
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
            if (currentTimeMinus(timeTouchDown) >= 200) {
                game.soundUtil.apply { play(TOUCH_DOWN) }
                timeTouchDown = System.currentTimeMillis()
            }
        }

        private fun playSound_TouchUp() {
            if (currentTimeMinus(timeTouchDown) >= 500) game.soundUtil.apply { play(TOUCH_DOWN) }
        }

    }

    private fun getInputListenerForUser() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            if (pointer != 0) return true
            onceUserFirstTouch.once { isUserFirstTouchFlow.tryEmit(Unit) }

            user.apply {
                clearActions()
                animShow(0.3f)
                this.x = x - 29f
                this.y = y - 102f
            }

            return true
        }
        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            if (pointer != 0) return
            user.apply {
                this.x = x - 29f
                this.y = y - 102f
            }
        }
        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (pointer != 0) return
            user.apply {
                clearActions()
                animHide(0.3f)
            }
        }
    }

}