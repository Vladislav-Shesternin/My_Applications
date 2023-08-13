package com.foot.ball.quiz.game.util

object Layout {

    object Splash {
        val progress = LayoutData(224f, 79f, 253f, 144f)
    }

    object Game {
        val panel     = LayoutData(88f, 604f, 1744f, 556f)
        val textPanel = LayoutData(135f, 604f, 1649f, 556f)
        val cb        = LayoutData(830f, 416f, 260f, 64f, vs = 124f)
        val text      = LayoutData(0f, 497f, 1920f, 90f, vs = 98f)
    }

    data class LayoutData(
        val x: Float = 0f,
        val y: Float = 0f,
        val w: Float = 0f,
        val h: Float = 0f,
        // horizontal space
        val hs: Float = 0f,
        // vertical space
        val vs: Float = 0f,
    )

}












