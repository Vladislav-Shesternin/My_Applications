package com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.bodiesGroup

import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.AbstractBodyGroup
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.BodyId
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.bodies.BHorizontal
import com.tutotoons.app.kpopsiescuteunicornpet.game.box2d.bodies.BVertical
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.WIDTH_UI
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val standartW = WIDTH_UI

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
            collisionList.addAll(
                BodyId.ItemId.entries.map { e -> e.name }
            )
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 906f, 1605f, 40f)
        createBody(bDown, 0f, 0f, 1605f, 40f)
    }

    private fun createVertical() {
        createBody(bLeft, 0f, 0f, 40f, 946f)
        createBody(bRight, 1565f, 0f, 40f, 946f)
    }

}