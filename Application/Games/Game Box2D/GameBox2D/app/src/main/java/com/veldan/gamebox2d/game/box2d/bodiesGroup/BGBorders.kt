package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.bodies.BHorizontal
import com.veldan.gamebox2d.game.box2d.bodies.BVertical
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.addNew
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(1400f, 700f)

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
        createBody(bTop, position.addNew(Vector2(0f, 685f).toGroupSize), Size(1400f, 15f).toGroupSize)
        createBody(bDown, position.addNew(Vector2(0f, 0f).toGroupSize), Size(1400f, 15f).toGroupSize)
    }

    private fun createVertical() {
        createBody(bLeft, position.addNew(Vector2(0f, 15f).toGroupSize), Size(15f, 670f).toGroupSize)
        createBody(bRight, position.addNew(Vector2(1385f, 15f).toGroupSize), Size(15f, 670f).toGroupSize)
    }


}