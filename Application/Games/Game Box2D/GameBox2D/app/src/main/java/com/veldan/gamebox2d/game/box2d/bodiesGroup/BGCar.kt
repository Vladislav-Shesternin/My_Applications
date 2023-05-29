package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.bodies.BCar
import com.veldan.gamebox2d.game.box2d.bodies.BWheel
import com.veldan.gamebox2d.game.utils.DEGTORAD
import com.veldan.gamebox2d.game.utils.RADTODEG
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.addNew
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.util.log

class BGCar(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val fromSize: Size = Size(386f, 196f)

    // Body

    private val bCar        = BCar(screenBox2d)
    private val bBackWheel  = BWheel(screenBox2d)
    private val bFrontWheel = BWheel(screenBox2d)

    // Joint

    private val jRevoluteBack  = AbstractJoint<RevoluteJoint>(screenBox2d)
    private val jRevoluteFront = AbstractJoint<RevoluteJoint>(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createB_Car()
        createB_Wheels()

        createJ_Revolute()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Car() {
        createBody(bCar, 0f, 26f,386f, 170f)
    }

    private fun createB_Wheels() {
        createBody(bBackWheel, 41f, 0f,66f, 66f)
        createBody(bFrontWheel, 247f, 0f,66f, 66f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Revolute() {
        createJoint(jRevoluteBack, RevoluteJointDef().apply {
            bodyA = bCar.body
            bodyB = bBackWheel.body

            localAnchorA.set(Vector2(74f, 7f).subCenter(bCar.center))

            enableMotor    = true
            maxMotorTorque = 500f
        })
        createJoint(jRevoluteFront, RevoluteJointDef().apply {
            bodyA = bCar.body
            bodyB = bFrontWheel.body

            localAnchorA.set(Vector2(280f, 7f).subCenter(bCar.center))
        })
    }

    fun right() {
        jRevoluteBack.joint?.apply {
            motorSpeed     = 360 * DEGTORAD
        }
    }

    fun left() {
        jRevoluteBack.joint?.apply {
            motorSpeed     = -360 * DEGTORAD
        }
    }

}