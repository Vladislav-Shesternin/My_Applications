package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.bodies.BCircle
import com.veldan.gamebox2d.game.box2d.bodies.BWheel
import com.veldan.gamebox2d.game.utils.DEGTORAD
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen

class BGFriction(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(120f, 120f)

    // Body
    private val bWheel = BWheel(screenBox2d)
    private val bCenter = BCircle(screenBox2d)


    // Joint
    private val jFriction = AbstractJoint<FrictionJoint>(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createB_Center()
        createB_Wheel()

        createJ_Friction()

    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Center() {
        createBody(bCenter, 50f, 50f, 18f, 18f)
    }

    private fun createB_Wheel() {
        createBody(bWheel, 0f, 0f, 120f, 120f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Friction() {
        createJoint(jFriction, FrictionJointDef().apply {
            bodyA     = bCenter.body
            bodyB     = bWheel.body
            maxForce  = 500f
            maxTorque = 500f
            localAnchorA.set(Vector2(60f, 10f).subCenter((bodyB.userData as AbstractBody).center))
        })
    }
}