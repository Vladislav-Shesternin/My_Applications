package com.minimal.tennis.fortwo.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.minimal.tennis.fortwo.game.LibGDXGame
import com.minimal.tennis.fortwo.game.box2d.AbstractBody
import com.minimal.tennis.fortwo.game.box2d.AbstractJoint
import com.minimal.tennis.fortwo.game.box2d.BodyId
import com.minimal.tennis.fortwo.game.box2d.bodies.BBall
import com.minimal.tennis.fortwo.game.box2d.bodies.BPortalA
import com.minimal.tennis.fortwo.game.box2d.bodies.BPortalB
import com.minimal.tennis.fortwo.game.box2d.bodies.BUserA
import com.minimal.tennis.fortwo.game.box2d.bodies.BUserB
import com.minimal.tennis.fortwo.game.box2d.bodiesGroup.BGBorders
import com.minimal.tennis.fortwo.game.utils.advanced.AdvancedStage
import com.minimal.tennis.fortwo.game.utils.advanced.AdvancedTwoHandScreen
import com.minimal.tennis.fortwo.game.utils.font.FontParameter
import com.minimal.tennis.fortwo.game.utils.region
import com.minimal.tennis.fortwo.game.utils.runGDX
import com.minimal.tennis.fortwo.game.utils.toB2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class TennisScreen(_game: LibGDXGame): AdvancedTwoHandScreen(_game) {

    private val fontParam = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(50)
    private val font50    = fontGenerator_KoulenRegular.generateFont(fontParam)

    // Actor
    private val aLblA = Label("0", Label.LabelStyle(font50, Color.WHITE))
    private val aLblB = Label("0", Label.LabelStyle(font50, Color.WHITE))

    // Body
    private val bUserA = BUserA(this)
    private val bUserB = BUserB(this)
    private val bBall  = BBall(this)
    private val bPortalA = BPortalA(this)
    private val bPortalB = BPortalB(this)

    // BodyGroup
    private val bgBorders = BGBorders(this)

    // Joint
    private val jMotorA = AbstractJoint<MotorJoint, MotorJointDef>(this)
    private val jMotorB = AbstractJoint<MotorJoint, MotorJointDef>(this)
    private val jMotorBall = AbstractJoint<MotorJoint, MotorJointDef>(this)

    // Field
    private val isDestroyJoint = AtomicBoolean(false)
    private val isStartBall    = AtomicBoolean(false)

    private var counterA = 0
    private var counterB = 0

    override fun show() {
        setBackBackground(game.allAssets.BACKGROUND.region)
        super.show()
    }

    override fun dispose() {
        bgBorders.destroy()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()

        createB_Portal()
        createB_User()
        createB_Ball()

        createJ_User()
        createJ_Ball()

        addLbl()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addLbl() {
        addActors(aLblA, aLblB)
        aLblA.apply {
            setBounds(26f, 1006f, 142f, 74f)
            setAlignment(Align.left)
        }
        aLblB.apply {
            setBounds(1752f, 1006f, 142f, 74f)
            setAlignment(Align.right)
        }
    }

    // ---------------------------------------------------
    // create BodyGroup
    // ---------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(0f, 0f, WIDTH, HEIGHT)
    }

    // ---------------------------------------------------
    // create Body
    // ---------------------------------------------------

    private fun createB_User() {
        // A
        bUserA.apply {
            id = BodyId.USER_A
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.USER_B, BodyId.BALL))
        }
        bUserA.create(199f, 396f, 195f, 289f)

        // B
        bUserB.apply {
            id = BodyId.USER_B
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.USER_A, BodyId.BALL))
        }
        bUserB.create(1526f, 396f, 195f, 289f)
    }

    private fun createB_Ball() {
        bBall.apply {
            id = BodyId.BALL
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.USER_A, BodyId.USER_B, BodyId.PORTAL))
        }
        bBall.create(913f, 645f, 94f, 94f)

        bBall.beginContactBlockArray.add(AbstractBody.ContactBlock {
            if (isDestroyJoint.getAndSet(true).not()) runGDX {
                isStartBall.set(false)
                jMotorBall.destroy()
            }
        })
    }

    private fun createB_Portal() {
        // A
        bPortalA.apply {
            id = BodyId.PORTAL
            collisionList.add(BodyId.BALL)
        }
        bPortalA.create(0f, 345f, 34f, 391f)

        bPortalA.beginContactBlockArray.add(AbstractBody.ContactBlock {
            toStartBall()

            runGDX {
                counterB++
                aLblB.setText(counterB)
            }
        })

        // B
        bPortalB.apply {
            id = BodyId.PORTAL
            collisionList.add(BodyId.BALL)
        }
        bPortalB.create(1886f, 345f, 34f, 391f)

        bPortalB.beginContactBlockArray.add(AbstractBody.ContactBlock {
            toStartBall()

            runGDX {
                counterA++
                aLblA.setText(counterA)
            }
        })
    }

    // ---------------------------------------------------
    // create Joint
    // ---------------------------------------------------

    private fun createJ_User() {
        // A
        jMotorA.create(MotorJointDef().apply {
            bodyA = bgBorders.bDown.body
            bodyB = bUserA.body
            collideConnected = true

            maxForce  = 80f
            maxTorque = 30f
            correctionFactor = 0.8f
            linearOffset.set(Vector2(258f, 495f).toB2)
        })

        // B
        jMotorB.create(MotorJointDef().apply {
            bodyA = bgBorders.bDown.body
            bodyB = bUserB.body
            collideConnected = true

            maxForce  = 80f
            maxTorque = 30f
            correctionFactor = 0.8f
            linearOffset.set(Vector2(1662f, 495f).toB2)
        })
    }

    private fun createJ_Ball() {
        jMotorBall.create(MotorJointDef().apply {
            bodyA = bgBorders.bDown.body
            bodyB = bBall.body
            collideConnected = true

            maxForce  = 20f
            maxTorque = 20f
            correctionFactor = 0f
            linearOffset.set(Vector2(960f, 692f).toB2)
        })
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun toStartBall() {
        if (isStartBall.getAndSet(true).not()) {
            runGDX {
                game.soundUtil.apply { play(gool, /*volumeLevel * 0.2f*/) }

                bBall.body?.apply {
                    setLinearVelocity(0f, 0f)
                    isAwake = false
                    setTransform(Vector2(960f, 692f).toB2, 0f)
                }

                createJ_Ball()

                isDestroyJoint.set(false)
            }
        }
    }

}