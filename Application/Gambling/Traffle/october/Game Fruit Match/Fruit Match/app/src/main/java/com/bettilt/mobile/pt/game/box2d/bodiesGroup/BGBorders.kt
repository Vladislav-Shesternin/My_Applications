package com.bettilt.mobile.pt.game.box2d.bodiesGroup

import com.bettilt.mobile.pt.game.box2d.AbstractBodyGroup
import com.bettilt.mobile.pt.game.box2d.BodyId
import com.bettilt.mobile.pt.game.box2d.bodies.BHorizontal
import com.bettilt.mobile.pt.game.box2d.bodies.BVertical
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val standartW = 1561f

    val bTop   = BHorizontal(screenBox2d)
    val bDown  = BHorizontal(screenBox2d)
    val bLeft  = BVertical(screenBox2d)
    val bRight = BVertical(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Borders()

        createHorizontal()
        createVertical()
    }


    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Borders() {
        arrayOf(bTop, bDown, bLeft, bRight).onEach { it.apply {
            id = BodyId.BORDERS
            collisionList.addAll(arrayOf(
                BodyId.Game.FRUIT,
            ))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 799f, 1561f, 50f)
        createBody(bDown, 0f, -50f, 1561f, 50f)
    }

    private fun createVertical() {
        createBody(bLeft, -50f, 0f, 50f, 799f)
        createBody(bRight, 1561f, 0f, 50f, 799f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}