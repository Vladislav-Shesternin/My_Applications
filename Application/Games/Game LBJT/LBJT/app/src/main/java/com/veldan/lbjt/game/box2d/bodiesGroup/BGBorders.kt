package com.veldan.lbjt.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.veldan.lbjt.game.box2d.AbstractBodyGroup
import com.veldan.lbjt.game.box2d.BodyId
import com.veldan.lbjt.game.box2d.bodies.BHorizontal
import com.veldan.lbjt.game.box2d.bodies.BVertical
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.runGDX

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val standartW = 700f

    val bTop   = BHorizontal(screenBox2d)
    val bDown  = BHorizontal(screenBox2d)
    val bLeft  = BVertical(screenBox2d)
    val bRight = BVertical(screenBox2d)

    override fun requestToCreate(position: Vector2, size: Vector2, block: () -> Unit) {
        super.requestToCreate(position, size, block)

        initB_Borders()

        createHorizontal()
        createVertical()

        finishCreate(block)
    }


    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Borders() {
        arrayOf(bTop, bDown, bLeft, bRight).onEach { it.apply {
            id = BodyId.BORDERS
            collisionList.addAll(arrayOf(
                BodyId.Menu.BUTTON,
                BodyId.Settings.VOLUME,
                BodyId.Settings.LANGUAGE,
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

    private fun createVertical() {
        createBody(bLeft, -10f, 0f, 10f, 1400f)
        createBody(bRight, 700f, 0f, 10f, 1400f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}