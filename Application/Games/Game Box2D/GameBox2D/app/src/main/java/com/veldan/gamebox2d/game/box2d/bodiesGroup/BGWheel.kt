//package com.veldan.gamebox2d.game.box2d.bodiesGroup
//
//import com.badlogic.gdx.math.Vector2
//import com.badlogic.gdx.physics.box2d.joints.WheelJoint
//import com.badlogic.gdx.physics.box2d.joints.WheelJointDef
//import com.veldan.gamebox2d.game.box2d.AbstractBody
//import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
//import com.veldan.gamebox2d.game.box2d.AbstractJoint
//import com.veldan.gamebox2d.game.box2d.bodies.BTrunk
//import com.veldan.gamebox2d.game.box2d.bodies.BWheel
//import com.veldan.gamebox2d.game.utils.DEGTORAD
//import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
//
//class BGWheel(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {
//
//    override val standartSize = Vector2(206f, 199f)
//
//    // Body
//    private val bTrunk = BTrunk(screenBox2d)
//    private val bWheel = BWheel(screenBox2d)
//
//    // Joint
//    private val jWheel = AbstractJoint<WheelJoint>(screenBox2d)
//
//
//    override fun create(position: Vector2, size: Vector2) {
//        super.create(position, size)
//
//        createB_Trunk()
//        createB_Wheel()
//
//        createJ_Wheel()
//
//    }
//
//    // ---------------------------------------------------
//    // Create Body
//    // ---------------------------------------------------
//
//    private fun createB_Trunk() {
//        createBody(bTrunk, 0f, 104f, 206f, 95f)
//    }
//
//    private fun createB_Wheel() {
//        createBody(bWheel,70f, 0f, 66f, 66f)
//    }
//
//    // ---------------------------------------------------
//    // Create Joint
//    // ---------------------------------------------------
//
//    private fun createJ_Wheel() {
//        createJoint(jWheel, WheelJointDef().apply {
//            bodyA     = bTrunk.body
//            bodyB     = bWheel.body
//
//            localAxisA.set(0f, 1f)
//
//            localAnchorA.set(Vector2(103f, -71f).subCenter((bodyA.userData as AbstractBody).center))
//
//            enableMotor    = true
//            maxMotorTorque = 100f
//            motorSpeed     = -3600f * DEGTORAD
//
//            frequencyHz = 1f
//            dampingRatio = 1f
//        })
//    }
//}