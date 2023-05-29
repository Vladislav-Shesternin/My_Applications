package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.bodies.BArm
import com.veldan.gamebox2d.game.box2d.bodies.BTrunk
import com.veldan.gamebox2d.game.utils.DEGTORAD
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.util.log
import kotlin.math.cos
import kotlin.math.sin

class BGPrismatic(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(242f, 153f)

    // Body
    private val bTrunk = BTrunk(screenBox2d)
    private val bArm   = BArm(screenBox2d)

    // Joint
    private val jPrismatic = AbstractJoint<PrismaticJoint>(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createB_Trunk()
        createB_Arm()

        createJ_Prismatic()

    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Trunk() {
        createBody(bTrunk, 0f, 0f, 206f, 95f)
    }

    private fun createB_Arm() {
        createBody(bArm, 223f, 0f, 19f, 153f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Prismatic() {
        createJoint(jPrismatic, PrismaticJointDef().apply {
            localAxisA.set(1f, 0f)
            bodyA = bTrunk.body
            bodyB = bArm.body

            localAnchorA.set(Vector2(218f, 0f).subCenter((bodyA.userData as AbstractBody).center))
            localAnchorB.set(Vector2(0f, 0f).subCenter((bodyB.userData as AbstractBody).center))

            enableLimit      = true
            lowerTranslation = -15f
            upperTranslation = screenBox2d.sizeConverterUIToBox.getSizeY(224f.toGroupY)

            enableMotor   = true
            motorSpeed    = -10f
            maxMotorForce = 700f
        })
    }
}