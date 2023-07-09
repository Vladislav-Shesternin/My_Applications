package com.supertest.card.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Menu {
        val buttons = LayoutData(477f,470f,447f,184f, vs = 28f)
        val btnX    = 88f
        val scroll  = LayoutData(590f,58f,753f,591f)
    }

    object Game {
        val start   = LayoutData(1400f, -180f,120f, 170f)
        val win     = LayoutData(253f, 175f,893f, 350f)
        val listPos = listOf(
            Vector2(62f, 480f),
            Vector2(227f, 480f),
            Vector2(392f, 480f),
            Vector2(557f, 480f),
            Vector2(722f, 480f),
            Vector2(887f, 480f),
            Vector2(1052f, 480f),
            Vector2(1217f, 480f),

            Vector2(62f, 265f),
            Vector2(227f, 265f),
            Vector2(392f, 265f),
            Vector2(557f, 265f),
            Vector2(722f, 265f),
            Vector2(887f, 265f),
            Vector2(1052f, 265f),
            Vector2(1217f, 265f),

            Vector2(62f, 50f),
            Vector2(227f, 50f),
            Vector2(392f, 50f),
            Vector2(557f, 50f),
            Vector2(722f, 50f),
            Vector2(887f, 50f),
            Vector2(1052f, 50f),
            Vector2(1217f, 50f),
        )
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
    ) {

        fun position() = Vector2(x, y)
        fun size() = Size(w, h)

    }

}












