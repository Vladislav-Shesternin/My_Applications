package com.veldan.base.box2d.game.box2d.bodiesGroup//package com.veldan.gamebox2d.game.box2d.bodiesGroup
//
//import com.badlogic.gdx.math.Vector2
//import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
//import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
//import com.veldan.gamebox2d.game.box2d.AbstractBody
//import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
//import com.veldan.gamebox2d.game.box2d.AbstractJoint
//import com.veldan.gamebox2d.game.box2d.bodies.BChain
//import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
//
//class BGChain(override val screenBox2d: AdvancedBox2dScreen, val color: BChain.Color): AbstractBodyGroup() {
//
//    override val standartSize = Vector2(12f, 181f)
//
//    // Body
//
//    val bChainList = List(10) { BChain(screenBox2d, color) }
//
//
//    override fun create(position: Vector2, size: Vector2) {
//        super.create(position, size)
//
//        createB_Chain()
//
//        createJ_Revolute()
//    }
//
//    // ---------------------------------------------------
//    // Create Body
//    // ---------------------------------------------------
//
//    private fun createB_Chain() {
//        bChainList.onEach { bChain ->
//            createBody(bChain, 0f, 0f, 12f, 31f)
//        }
//    }
//
//    // ---------------------------------------------------
//    // Create Joint
//    // ---------------------------------------------------
//
//    private fun createJ_Revolute() {
//        bChainList.onEachIndexed { index, bChain ->
//            if (index < bChainList.lastIndex) {
//                createJoint(AbstractJoint<RevoluteJoint>(screenBox2d), RevoluteJointDef().apply {
//                    bodyA = bChain.body
//                    bodyB = bChainList[index + 1].body
//
//                    localAnchorA.set(Vector2(6f, 3f).subCenter((bodyA.userData as AbstractBody).center))
//                    localAnchorB.set(Vector2(6f, 28f).subCenter((bodyB.userData as AbstractBody).center))
//                })
//            }
//        }
//    }
//
//}