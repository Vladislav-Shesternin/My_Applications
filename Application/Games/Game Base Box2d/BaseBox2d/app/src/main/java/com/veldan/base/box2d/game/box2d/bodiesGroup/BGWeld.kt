package com.veldan.base.box2d.game.box2d.bodiesGroup//package com.veldan.gamebox2d.game.box2d.bodiesGroup
//
//import com.badlogic.gdx.math.Vector2
//import com.badlogic.gdx.physics.box2d.joints.WeldJoint
//import com.badlogic.gdx.physics.box2d.joints.WeldJointDef
//import com.veldan.gamebox2d.game.box2d.AbstractBody
//import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
//import com.veldan.gamebox2d.game.box2d.AbstractJoint
//import com.veldan.gamebox2d.game.box2d.bodies.BWheel
//import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
//
//class BGWeld(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {
//
//    override val standartSize = Vector2(259f, 120f)
//
//    // Body
//    private val bWheel = BWheel(screenBox2d)
//    private val bWheel2 = BWheel(screenBox2d)
//
//    // Joint
//    private val jWeld = AbstractJoint<WeldJoint>(screenBox2d)
//
//
//    override fun create(position: Vector2, size: Vector2) {
//        super.create(position, size)
//
//        createB_Wheel()
//
//        createJ_Weld()
//
//    }
//
//    // ---------------------------------------------------
//    // Create Body
//    // ---------------------------------------------------
//
//    private fun createB_Wheel() {
//        createBody(bWheel, 0f, 0f, 120f, 120f)
//        createBody(bWheel2, 139f, 0f, 120f, 120f)
//    }
//
//    // ---------------------------------------------------
//    // Create Joint
//    // ---------------------------------------------------
//
//    private fun createJ_Weld() {
//        createJoint(jWeld, WeldJointDef().apply {
//            bodyA     = bWheel.body
//            bodyB     = bWheel2.body
//
//            localAnchorA.set(Vector2(139f, 59f).subCenter((bodyA.userData as AbstractBody).center))
//            localAnchorB.set(Vector2(0f, 59f).subCenter((bodyB.userData as AbstractBody).center))
//
//            frequencyHz = 2f
//            dampingRatio = 1f
//        })
//    }
//}