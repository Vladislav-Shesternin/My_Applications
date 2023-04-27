package com.invt.nard.app.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.invt.nard.app.game.box2d.AbstractBodyGroup
import com.invt.nard.app.game.box2d.bodies.BHor
import com.invt.nard.app.game.box2d.bodies.BVer
import com.invt.nard.app.game.utils.Size
import com.invt.nard.app.game.utils.addNew
import com.invt.nard.app.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(1641f, 864f)

    private val bTop   = BHor(screenBox2d)
    private val bDown  = BHor(screenBox2d)
    private val bLeft  = BVer(screenBox2d)
    private val bRight = BVer(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createHorizontal()
        createVertical()
    }



    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, position.addNew(Vector2(64f, 831f).toGroupSize), Size(1526f, 33f).toGroupSize)
        createBody(bDown, position.addNew(Vector2(64f, 0f).toGroupSize), Size(1526f, 33f).toGroupSize)
    }

    private fun createVertical() {
        createBody(bLeft, position.addNew(Vector2(0f, 57f).toGroupSize), Size(64f, 750f).toGroupSize)
        createBody(bRight, position.addNew(Vector2(1577f, 57f).toGroupSize), Size(64f, 750f).toGroupSize)
    }


}