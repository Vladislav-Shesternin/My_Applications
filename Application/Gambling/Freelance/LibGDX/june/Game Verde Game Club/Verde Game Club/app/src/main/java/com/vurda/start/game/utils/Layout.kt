package com.vurda.start.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Game {
        val start   = LayoutData(1610f, -180f,120f, 170f)
        val win     = LayoutData(404f, 275f,893f, 350f)
        val listPos = listOf(
            Vector2(62f+151, 480f+100),
            Vector2(227f+151, 480f+100),
            Vector2(392f+151, 480f+100),
            Vector2(557f+151, 480f+100),
            Vector2(722f+151, 480f+100),
            Vector2(887f+151, 480f+100),
            Vector2(1052f+151, 480f+100),
            Vector2(1217f+151, 480f+100),

            Vector2(62f+151, 265f+100),
            Vector2(227f+151, 265f+100),
            Vector2(392f+151, 265f+100),
            Vector2(557f+151, 265f+100),
            Vector2(722f+151, 265f+100),
            Vector2(887f+151, 265f+100),
            Vector2(1052f+151, 265f+100),
            Vector2(1217f+151, 265f+100),

            Vector2(62f+151, 50f+100),
            Vector2(227f+151, 50f+100),
            Vector2(392f+151, 50f+100),
            Vector2(557f+151, 50f+100),
            Vector2(722f+151, 50f+100),
            Vector2(887f+151, 50f+100),
            Vector2(1052f+151, 50f+100),
            Vector2(1217f+151, 50f+100),
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












