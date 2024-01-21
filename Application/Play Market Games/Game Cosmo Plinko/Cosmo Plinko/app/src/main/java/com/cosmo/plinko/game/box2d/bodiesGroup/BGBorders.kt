package com.cosmo.plinko.game.box2d.bodiesGroup

import com.cosmo.plinko.game.box2d.AbstractBodyGroup
import com.cosmo.plinko.game.box2d.BodyId
import com.cosmo.plinko.game.box2d.bodies.BHorizontal
import com.cosmo.plinko.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val standartW = 700f

    val bTop   = BHorizontal(screenBox2d)
    val bDown  = BHorizontal(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Borders()

        createHorizontal()
    }


    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Borders() {
        arrayOf(bTop, bDown).onEach { it.apply {
            id = BodyId.BORDERS
            collisionList.addAll(arrayOf(

            ))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 1400f, 700f, 10f)
        createBody(bDown, 0f, -10f, 700f, 10f)
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}