package com.minimal.tennis.fortwo.game.utils.advanced

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.minimal.tennis.fortwo.game.LibGDXGame
import com.minimal.tennis.fortwo.game.box2d.AbstractBody
import com.minimal.tennis.fortwo.game.box2d.AbstractJoint
import com.minimal.tennis.fortwo.game.box2d.BodyId
import com.minimal.tennis.fortwo.game.box2d.WorldUtil
import com.minimal.tennis.fortwo.game.box2d.bodies.standart.BStatic
import com.minimal.tennis.fortwo.game.utils.actor.animHide
import com.minimal.tennis.fortwo.game.utils.actor.animShow
import com.minimal.tennis.fortwo.game.utils.actor.disable
import com.minimal.tennis.fortwo.game.utils.currentTimeMinus
import com.minimal.tennis.fortwo.game.utils.toB2
import com.minimal.tennis.fortwo.util.Once
import com.minimal.tennis.fortwo.util.log
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class AdvancedTwoHandScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    val aHandA by lazy { Image(game.allAssets.a_hand) }
    val aHandB by lazy { Image(game.allAssets.b_hand) }

    // Joint
    private val jMouseA by lazy { AbstractJoint<MouseJoint, MouseJointDef>(this) }
    private val jMouseB by lazy { AbstractJoint<MouseJoint, MouseJointDef>(this) }

    // Body
    private val bStatic by lazy { BStatic(this) }

    // Fields
    private val listenerForMouseJoint: InputListener by lazy { getInputListenerForMouseJoint() }
    private val listenerForUser      : InputListener by lazy { getInputListenerForUser() }

    private var maxForce     = 1000f
    private var frequencyHz  = 5.0f
    private var dampingRatio = 0f

    private var PointerHandA = -1
    private var PointerHandB = -1

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
        addActor(aHandA)
        aHandA.apply {
            disable()
            setSize(129f, 120f)
            animHide()
        }
        addActor(aHandB)
        aHandB.apply {
            disable()
            setSize(129f, 120f)
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

        var hitAbstractBody_A: AbstractBody? = null
        val touchPointInBox_A = Vector2()
        val tmpVector2_A      = Vector2()

        val callback_A = QueryCallback { fixture ->
            if (
                fixture.isSensor.not() &&
                fixture.testPoint(touchPointInBox_A) &&
                (fixture.body?.userData as AbstractBody).id != BodyId.NONE
            ) {
                hitAbstractBody_A = fixture.body?.userData as AbstractBody
                return@QueryCallback false
            }
            return@QueryCallback true
        }

        var hitAbstractBody_B: AbstractBody? = null
        val touchPointInBox_B = Vector2()
        val tmpVector2_B      = Vector2()

        val callback_B = QueryCallback { fixture ->
            if (
                fixture.isSensor.not() &&
                fixture.testPoint(touchPointInBox_B) &&
                (fixture.body?.userData as AbstractBody).id != BodyId.NONE &&
                (fixture.body?.userData as AbstractBody).id != BodyId.BALL
            ) {
                hitAbstractBody_B = fixture.body?.userData as AbstractBody
                return@QueryCallback false
            }
            return@QueryCallback true
        }

        var timeTouchDown = 0L

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            when(pointer) {
                0 -> {
                    touchPointInBox_A.set(tmpVector2_A.set(x, y).toB2)
                    hitAbstractBody_A = null

                    worldUtil.world.QueryAABB(
                        callback_A,
                        touchPointInBox_A.x - 0.01f,
                        touchPointInBox_A.y - 0.01f,
                        touchPointInBox_A.x + 0.01f,
                        touchPointInBox_A.y + 0.01f
                    )

                    hitAbstractBody_A?.let { hit ->
                        playSound_TouchDown()

                        when(hit.id) {
                            BodyId.USER_A -> {
                                PointerHandA = 0
                                PointerHandB = 1
                            }
                            BodyId.USER_B -> {
                                PointerHandB = 0
                                PointerHandA = 1
                            }
                        }

                        jMouseA.create(MouseJointDef().apply {
                            bodyA = bStatic.body
                            bodyB = hit.body
                            collideConnected = true

                            target.set(touchPointInBox_A)

                            maxForce     = this@AdvancedTwoHandScreen.maxForce * bodyB.mass
                            frequencyHz  = this@AdvancedTwoHandScreen.frequencyHz
                            dampingRatio = this@AdvancedTwoHandScreen.dampingRatio
                        })
                    }
                }
                1 -> {
                    touchPointInBox_B.set(tmpVector2_B.set(x, y).toB2)
                    hitAbstractBody_B = null

                    worldUtil.world.QueryAABB(
                        callback_B,
                        touchPointInBox_B.x - 0.01f,
                        touchPointInBox_B.y - 0.01f,
                        touchPointInBox_B.x + 0.01f,
                        touchPointInBox_B.y + 0.01f
                    )

                    hitAbstractBody_B?.let { hit ->
                        playSound_TouchDown()

                        when(hit.id) {
                            BodyId.USER_A -> {
                                PointerHandA = 1
                                PointerHandB = 0
                            }
                            BodyId.USER_B -> {
                                PointerHandB = 1
                                PointerHandA = 0
                            }
                        }

                        jMouseB.create(MouseJointDef().apply {
                            bodyA = bStatic.body
                            bodyB = hit.body
                            collideConnected = true

                            target.set(touchPointInBox_B)

                            maxForce     = this@AdvancedTwoHandScreen.maxForce * bodyB.mass
                            frequencyHz  = this@AdvancedTwoHandScreen.frequencyHz
                            dampingRatio = this@AdvancedTwoHandScreen.dampingRatio
                        })
                    }
                }
            }

            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            when(pointer) {
                0 -> jMouseA.joint?.target = tmpVector2_A.set(x, y).toB2
                1 -> jMouseB.joint?.target = tmpVector2_B.set(x, y).toB2
            }
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            //playSound_TouchUp()

            when(pointer) {
                0 -> hitAbstractBody_A?.let { jMouseA.destroy() }
                1 -> hitAbstractBody_B?.let { jMouseB.destroy() }
            }

        }


        private fun playSound_TouchDown() {
            if (currentTimeMinus(timeTouchDown) >= 200) {
                //game.soundUtil.apply { play(TOUCH_DOWN) }
                timeTouchDown = System.currentTimeMillis()
            }
        }

        private fun playSound_TouchUp() {
            //if (currentTimeMinus(timeTouchDown) >= 500) game.soundUtil.apply { play(TOUCH_DOWN) }
        }

    }

    private fun getInputListenerForUser() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            //if (pointer != 0) return true

            when(pointer) {
                PointerHandA -> aHandA.apply {
                    clearActions()
                    animShow(0.3f)
                    this.x = x - 64f
                    this.y = y - 60f
                }
                PointerHandB -> aHandB.apply {
                    clearActions()
                    animShow(0.3f)
                    this.x = x - 64f
                    this.y = y - 60f
                }
            }

            return true
        }
        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            //if (pointer != 0) return
            when(pointer) {
                PointerHandA -> aHandA.apply {
                    this.x = x - 64f
                    this.y = y - 60f
                }
                PointerHandB -> aHandB.apply {
                    this.x = x - 64f
                    this.y = y - 60f
                }
            }
        }
        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            //if (pointer != 0) return
            when(pointer) {
                PointerHandA -> aHandA.apply {
                    clearActions()
                    animHide(0.3f)
                }
                PointerHandB -> aHandB.apply {
                    clearActions()
                    animHide(0.3f)
                }
            }
        }
    }

}