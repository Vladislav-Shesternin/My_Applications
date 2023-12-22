package atest.btest.lbjttest.game.box2d.bodiesGroup

import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.box2d.bodies.BHorizontal
import atest.btest.lbjttest.game.box2d.bodies.BVertical
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen

class BGBorders(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val standartW = 700f

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
            collisionList.addAll(arrayOf(
                BodyId.Game.DYNAMIC,
            ))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 700f, 1400f, 10f)
        createBody(bDown, 0f, -10f, 1400f, 10f)
    }

    private fun createVertical() {
        createBody(bLeft, -10f, 0f, 10f, 700f)
        createBody(bRight, 1400f, 0f, 10f, 700f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}