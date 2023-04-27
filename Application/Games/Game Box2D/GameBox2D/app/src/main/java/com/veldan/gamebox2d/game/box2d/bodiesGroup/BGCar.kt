package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.bodies.BCar
import com.veldan.gamebox2d.game.box2d.bodies.BWheel
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.addNew
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.game.utils.vector2
import com.veldan.gamebox2d.util.log

class BGCar(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val fromSize: Size = Size(386f, 196f)

    // Body

    private val bCar        = BCar(screenBox2d)
    private val bBackWheel  = BWheel(screenBox2d)
    private val bFrontWheel = BWheel(screenBox2d)

    // Joint

    private val jRevoluteBack = AbstractJoint<RevoluteJoint>(screenBox2d)
    private val jDistanceStart = AbstractJoint<DistanceJoint>(screenBox2d)


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
        createBody(bCar, position.addNew(Vector2(0f, 26f).toGroupSize), Size(386f, 170f).toGroupSize)
    }

    private fun createB_Wheels() {
        createBody(bBackWheel, position.addNew(Vector2(41f, 0f).toGroupSize), Size(66f, 66f).toGroupSize)
        createBody(bFrontWheel, position.addNew(Vector2(247f, 0f).toGroupSize), Size(66f, 66f).toGroupSize)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Revolute() {
        jRevoluteBack.create(RevoluteJointDef().apply {
            bodyA = bCar.body
            bodyB = bBackWheel.body

            localAnchorA.set(Vector2(74f, 7f).subCenter(bCar.center))
        })

//        jDistanceStart.create(DistanceJointDef().apply {
//            bodyA  = bOrbG.body
//            bodyB  = bSpring.body
//            length = 0f
//            frequencyHz = 3f
//
//            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(6f, 6f).vector2)
//        })
//        jDistanceEnd.create(DistanceJointDef().apply {
//            bodyA  = bOrbR.body
//            bodyB  = bSpring.body
//            length = 0f
//            frequencyHz = 3f
//
//            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(181f, 6f).vector2)
//        })
    }

    fun ddd() {
        bCar.body?.applyLinearImpulse(200f, 0f, 0f, 0f, true)
    }

}