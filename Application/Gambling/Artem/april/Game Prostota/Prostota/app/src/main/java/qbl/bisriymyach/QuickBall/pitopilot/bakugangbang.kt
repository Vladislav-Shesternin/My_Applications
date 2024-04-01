package qbl.bisriymyach.QuickBall.pitopilot

import qbl.bisriymyach.QuickBall.hotvils.Corona
import qbl.bisriymyach.QuickBall.hotvils.Omred
import qbl.bisriymyach.QuickBall.harizma
import qbl.bisriymyach.QuickBall.hotvils.Brat
import qbl.bisriymyach.QuickBall.Ebufren

class bakugangbang(override val screenBox2d: Ebufren) : Corona() {

    override val standartW = 1080f

    private val bTop   = harizma(screenBox2d)
    private val bDown  = harizma(screenBox2d)
    private val bLeft  = Brat(screenBox2d)
    private val bRight = Brat(screenBox2d)

    override fun mackro(x: Float, y: Float, w: Float, h: Float) {
        super.mackro(x, y, w, h)

        initB_Borders()

        createHorizontal()
        createVertical()
    }


    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Borders() {
        arrayOf(bTop, bDown, bLeft, bRight).onEach { it.apply {
            id = Omred.STATIC
            collisionList.addAll(arrayOf(
                Omred.BALL,
            ))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createHorizontal() {
        createBody(bTop, 0f, 1920f, 1080f, 20f)
        createBody(bDown, 0f, -20f, 1080f, 20f)
    }

    private fun createVertical() {
        createBody(bLeft, -20f, 0f, 20f, 1920f)
        createBody(bRight, 1080f, 0f, 20f, 1920f)
    }

}