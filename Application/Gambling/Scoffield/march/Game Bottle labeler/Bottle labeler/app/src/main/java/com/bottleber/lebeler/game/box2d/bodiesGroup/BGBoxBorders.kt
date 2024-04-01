package com.bottleber.lebeler.game.box2d.bodiesGroup

import com.bottleber.lebeler.game.box2d.AbstractBodyGroup
import com.bottleber.lebeler.game.box2d.BodyId
import com.bottleber.lebeler.game.box2d.bodies.BBoxHorizontal
import com.bottleber.lebeler.game.box2d.bodies.BBoxVertical
import com.bottleber.lebeler.game.box2d.bodies.BHorizontal
import com.bottleber.lebeler.game.box2d.bodies.BVertical
import com.bottleber.lebeler.game.utils.advanced.AdvancedBox2dScreen

class BGBoxBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val standartW = 1593f

    val bTop   = BBoxHorizontal(screenBox2d)
    val bDown  = BBoxHorizontal(screenBox2d)
    val bLeft  = BBoxVertical(screenBox2d)
    val bRight = BBoxVertical(screenBox2d)

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
        arrayOf(bTop, bLeft, bRight).onEach { it.apply {
            id = BodyId.PRICEL_BOX
            collisionList.addAll(arrayOf(
                BodyId.PRICEL, BodyId.SEPARATOR
            ))
        } }

        bDown.id = BodyId.PRICEL_BOX_DOWN
        bDown.collisionList.addAll(arrayOf(
            BodyId.PRICEL, BodyId.BOTTLE, BodyId.SEPARATOR
        ))
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 753f, 1593f, 10f)
        createBody(bDown, 0f, 0f, 1593f, 10f)
    }

    private fun createVertical() {
        createBody(bLeft, 0f, 0f, 10f, 763f)
        createBody(bRight, 1583f, 0f, 10f, 763f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}