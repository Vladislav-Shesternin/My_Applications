package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.GearJoint
import com.badlogic.gdx.physics.box2d.joints.GearJointDef
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.bodies.BCircle
import com.veldan.gamebox2d.game.box2d.bodies.BWheel
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.game.utils.runGDX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BGGear(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(772f, 350f)

    // Body
    private val bWheel1 = BWheel(screenBox2d)
    private val bWheel2 = BWheel(screenBox2d)
    private val bCentr1  = BCircle(screenBox2d)
    private val bCentr2  = BCircle(screenBox2d)

    // Joint
    private val jRevolute1 = AbstractJoint<RevoluteJoint>(screenBox2d)
    private val jRevolute2 = AbstractJoint<RevoluteJoint>(screenBox2d)
    private val jGear      = AbstractJoint<GearJoint>(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createB_Centr()
        createB_Wheel()

        createJ_Revolute1()
        createJ_Revolute2()
        createJ_Gear()

    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Centr() {
        createBody(bCentr1, 74f, 74f, 26f, 26f)
        createBody(bCentr2, 570f, 148f, 53f, 53f)
    }

    private fun createB_Wheel() {
        createBody(bWheel1, 0f, 0f, 175f, 175f)
        createBody(bWheel2, 422f, 0f, 350f, 350f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Revolute1() {
        createJoint(jRevolute1, RevoluteJointDef().apply {
            bodyA = bCentr1.body
            bodyB = bWheel1.body
        })
    }

    private fun createJ_Revolute2() {
        createJoint(jRevolute2, RevoluteJointDef().apply {
            bodyA = bCentr2.body
            bodyB = bWheel2.body
        })
    }

    private fun createJ_Gear() {
        createJoint(jGear, GearJointDef().apply {
            bodyA = bWheel1.body
            bodyB = bWheel2.body
            joint1 = jRevolute1.joint
            joint2 = jRevolute2.joint
            ratio = -2f
        })
    }
}