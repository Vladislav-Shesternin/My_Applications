package com.veldan.base.box2d.game.box2d.bodiesGroup//package com.veldan.gamebox2d.game.box2d.bodiesGroup
//
//import com.badlogic.gdx.math.Vector2
//import com.badlogic.gdx.physics.box2d.joints.MotorJoint
//import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
//import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
//import com.veldan.gamebox2d.game.box2d.AbstractJoint
//import com.veldan.gamebox2d.game.box2d.bodies.BCircle
//import com.veldan.gamebox2d.game.box2d.bodies.BWheel
//import com.veldan.gamebox2d.game.utils.DEGTORAD
//import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
//
//class BGMotor(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {
//
//    override val standartSize = Vector2(192f, 192f)
//
//    // Body
//    private val bWheel = BWheel(screenBox2d)
//    private val bCenter = BCircle(screenBox2d)
//
//
//    // Joint
//    private val jMotor = AbstractJoint<MotorJoint>(screenBox2d)
//
//
//    override fun create(position: Vector2, size: Vector2) {
//        super.create(position, size)
//
//        createB_Center()
//        createB_Wheel()
//
//        createJ_Motor()
//
//    }
//
//    // ---------------------------------------------------
//    // Create Body
//    // ---------------------------------------------------
//
//    private fun createB_Center() {
//        createBody(bCenter, 81f, 81f, 29f, 29f)
//    }
//
//    private fun createB_Wheel() {
//        createBody(bWheel, 0f, 0f, 192f, 192f)
//    }
//
//    // ---------------------------------------------------
//    // Create Joint
//    // ---------------------------------------------------
//
//    private fun createJ_Motor() {
//        createJoint(jMotor, MotorJointDef().apply {
//            bodyA     = bCenter.body
//            bodyB     = bWheel.body
//            maxForce  = 1000f
//            maxTorque = 1000f
//
//            correctionFactor = 0.1f
//
//            angularOffset = 90 * DEGTORAD
//            linearOffset.set(5f, 5f)
//        })
//    }
//}