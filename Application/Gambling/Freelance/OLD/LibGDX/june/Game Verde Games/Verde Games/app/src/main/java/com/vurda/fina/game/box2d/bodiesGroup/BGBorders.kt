package com.vurda.fina.game.box2d.bodiesGroup

import com.vurda.fina.game.box2d.AbstractBodyGroup
import com.vurda.fina.game.box2d.bodies.BHor
import com.vurda.fina.game.box2d.bodies.BVer
import com.vurda.fina.game.utils.Size
import com.vurda.fina.game.utils.advanced.AdvancedBox2dScreen
import com.badlogic.gdx.math.Vector2

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(1555f, 800f)

    private val bTop   = BHor(screenBox2d)
    val bDown  = BHor(screenBox2d)
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
        createBody(bTop, 0f, 800f, 1555f, 40f)
        createBody(bDown, 0f, -40f, 1555f, 40f)
    }

    private fun createVertical() {
        createBody(bLeft, -40f, 0f, 40f, 800f)
        createBody(bRight, 1555f, 0f, 40f, 800f)
    }


}