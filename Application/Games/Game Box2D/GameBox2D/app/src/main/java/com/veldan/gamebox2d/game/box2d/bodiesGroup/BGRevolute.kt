//package com.veldan.gamebox2d.game.box2d.bodiesGroup
//
//import com.badlogic.gdx.math.Vector2
//import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
//import com.badlogic.gdx.scenes.scene2d.ui.Image
//import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
//import com.veldan.gamebox2d.game.manager.SpriteManager
//import com.veldan.gamebox2d.game.utils.Size
//import com.veldan.gamebox2d.game.utils.addNew
//import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
//
//class BGRevolute(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {
//
//    private val bRev1 = BRev1(screenBox2d)
//    private val bRev2 = BRev2(screenBox2d)
//
//    private val image = Image(SpriteManager.GameRegion.REV_POINT.region)
//
//
//    override fun create(position: Vector2) {
//        super.create(position)
//        // body
//        createB_Rev1()
//        createB_Rev2()
//
//        addPoint()
//
//        // joint
//        createJ_Revolute()
//    }
//
//    // ------------------------------------------------------------------------
//    // Add Actor
//    // ------------------------------------------------------------------------
//
//    private fun addPoint() {
//        screenBox2d.stageUI.addActor(image)
//
//        val pos = position.addNew(95f, 191f)
//        image.setBounds(pos.x, pos.y,10f,10f)
//    }
//
//    // ------------------------------------------------------------------------
//    // Create Body
//    // ------------------------------------------------------------------------
//
//    private fun createB_Rev1() {
//

//        createBody( new  )

//        bRev1.also { b ->
//            b.create(position.addNew(73f, 0f), Size(54f, 218f))
//        }
//    }
//
//    private fun createB_Rev2() {
//        bRev2.also { b ->
//            b.create(position.addNew(0f, 193f), Size(200f, 6f))
//        }
//    }
//
//    // ------------------------------------------------------------------------
//    // Create Joint
//    // ------------------------------------------------------------------------
//
//    private fun createJ_Revolute() {
//        RevoluteJointDef().apply {
//            bodyA = bRev1.body
//            bodyB = bRev2.body
//
//            val pos1 = screenBox2d.sizeConverterUIToBox.getSize(27f, 196f)
//            val pos2 = screenBox2d.sizeConverterUIToBox.getSize(100f, 3f)
//
//            localAnchorA.set(
//                pos1.x - bRev1.center.x,
//                pos1.y - bRev1.center.y,
//            )
//            localAnchorB.set(
//                pos2.x - bRev2.center.x,
//                pos2.y - bRev2.center.y,
//            )
//

//            createJoint(  new  )


//            world.createJoint(this)
//        }
//    }
//
//    // ------------------------------------------------------------------------
//    // Logic
//    // ------------------------------------------------------------------------
//
//}