package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.bodies.BHorizontal
import com.veldan.gamebox2d.game.box2d.bodies.BVertical
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val standartW = 1400f

    val bTop   = BHorizontal(screenBox2d)
    val bDown  = BHorizontal(screenBox2d)
    val bLeft  = BVertical(screenBox2d)
    val bRight = BVertical(screenBox2d)


    override fun create(position: Vector2, size: Vector2) {
        super.create(position, size)

        createHorizontal()
        createVertical()
    }



    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 685f, 1400f, 15f)
        createBody(bDown, 0f, 0f, 1400f, 15f)
    }

    private fun createVertical() {
        createBody(bLeft, 0f, 15f, 15f, 670f)
        createBody(bRight, 1385f, 15f, 15f, 670f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}