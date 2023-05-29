package com.vbtb.game.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.vbtb.game.game.box2d.AbstractBodyGroup
import com.vbtb.game.game.box2d.bodies.BHorizontal
import com.vbtb.game.game.box2d.bodies.BVertical
import com.vbtb.game.game.utils.Size
import com.vbtb.game.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(745f, 1485f)

    private val bTop   = BHorizontal(screenBox2d)
    private val bDown  = BHorizontal(screenBox2d)
    private val bLeft  = BVertical(screenBox2d)
    private val bRight = BVertical(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createHorizontal()
        createVertical()
    }



    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 1485f, 745f, 50f)
        createBody(bDown, 0f, -50f, 745f, 50f)
    }

    private fun createVertical() {
        createBody(bLeft, -50f, 0f, 50f, 1485f)
        createBody(bRight, 745f, 0f, 50f, 1485f)
    }


}