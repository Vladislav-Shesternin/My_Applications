//package aer.com.gamesas.mobile.slot.game.box2d.bodiesGroup
//
//import aer.com.gamesas.mobile.slot.game.box2d.AbstractBodyGroup
//import aer.com.gamesas.mobile.slot.game.box2d.bodies.BHor
//import aer.com.gamesas.mobile.slot.game.box2d.bodies.BVer
//import aer.com.gamesas.mobile.slot.game.utils.Size
//import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedBox2dScreen
//import com.badlogic.gdx.math.Vector2
//
//class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {
//
//    override val fromSize = Size(701f, 1401f)
//
//    private val bTop   = BHor(screenBox2d)
//    private val bLeft  = BVer(screenBox2d)
//    private val bRight = BVer(screenBox2d)
//
//
//    override fun create(position: Vector2, size: Size) {
//        super.create(position, size)
//
//        createHorizontal()
//        createVertical()
//    }
//
//
//
//    // ---------------------------------------------------
//    // Create Body
//    // ---------------------------------------------------
//
//    private fun createHorizontal() {
//        createBody(bTop, 0f, 1388f, 701f, 27f)
//    }
//
//    private fun createVertical() {
//        createBody(bLeft, -13f, 0f, 27f, 1401f)
//        createBody(bRight, 687f, 0f, 27f, 1401f)
//    }
//
//
//}