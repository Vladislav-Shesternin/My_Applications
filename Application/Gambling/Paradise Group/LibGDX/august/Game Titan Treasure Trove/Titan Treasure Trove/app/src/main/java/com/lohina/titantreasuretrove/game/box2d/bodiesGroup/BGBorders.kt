//package com.lohina.titantreasuretrove.game.box2d.bodiesGroup
//
//import com.badlogic.gdx.math.Vector2
//import com.lohina.titantreasuretrove.game.box2d.AbstractBodyGroup
//import com.lohina.titantreasuretrove.game.box2d.BodyId
//import com.lohina.titantreasuretrove.game.box2d.BodyId.Game.ITEM
//import com.lohina.titantreasuretrove.game.box2d.BodyId.Game.ZEUS
//import com.lohina.titantreasuretrove.game.box2d.bodies.BHorizontal
//import com.lohina.titantreasuretrove.game.box2d.bodies.BVertical
//import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedBox2dScreen
//
//class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {
//
//    override val standartW = 1772f
//
//    val bTop   = BHorizontal(screenBox2d)
//    val bDown  = BHorizontal(screenBox2d)
//    val bLeft  = BVertical(screenBox2d)
//    val bRight = BVertical(screenBox2d)
//
//    override fun requestToCreate(position: Vector2, size: Vector2, block: () -> Unit) {
//        super.requestToCreate(position, size, block)
//
//        initB_Borders()
//
//        createHorizontal()
//        createVertical()
//
//        finishCreate(block)
//    }
//
//
//    // ---------------------------------------------------
//    // Init
//    // ---------------------------------------------------
//
//    private fun initB_Borders() {
//        arrayOf(bTop, bDown, bLeft, bRight).onEach { it.apply {
//            id = BodyId.BORDERS
//            collisionList.addAll(arrayOf(ZEUS, ITEM))
//        } }
//    }
//
//    // ---------------------------------------------------
//    // Create Body
//    // ---------------------------------------------------
//
//    private fun createHorizontal() {
//        createBody(bTop, 0f, 747f, 1772f, 40f)
//        createBody(bDown, 0f, 0f, 1772f, 40f)
//    }
//
//    private fun createVertical() {
//        createBody(bLeft, 0f, 0f, 40f, 787f)
//        createBody(bRight, 1722f, 0f, 40f, 787f)
//    }
//
//    // ---------------------------------------------------
//    // Logic
//    // ---------------------------------------------------
//
//
//}