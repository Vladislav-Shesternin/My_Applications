package com.funsagon.hexagon.game.box2d.bodiesGroup

import com.funsagon.hexagon.game.box2d.AbstractBodyGroup
import com.funsagon.hexagon.game.box2d.BodyId
import com.funsagon.hexagon.game.box2d.bodies.BHorizontal
import com.funsagon.hexagon.game.box2d.bodies.BVertical
import com.funsagon.hexagon.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val standartW = 1920f

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
        arrayOf(bTop, bLeft, bRight).onEach { it.apply {
            id = BodyId.BORDERS
            collisionList.addAll(arrayOf(
                BodyId.FunWorld.HEXAGON,
            ))
        } }

        bDown.id = BodyId.DOWN
        bDown.collisionList.add(BodyId.FunWorld.HEXAGON)
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 1080f, 1920f, 10f)
        createBody(bDown, 0f, -10f, 1920f, 10f)
    }

    private fun createVertical() {
        createBody(bLeft, -10f, 0f, 10f, 1080f)
        createBody(bRight, 1920f, 0f, 10f, 1080f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}