package com.jungle.jumping.bird.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val button   = LayoutData(607f, 286f, 228f, 228f)
        val progress = LayoutData(647f, 351f, 147f, 98f)
    }

    object Menu {
        val record = LayoutData(473f, 79f, 497f, 105f)
    }

    object Game {
        val borderTop    = LayoutData(0f, 761f, 370f, 39f)
        val borderBottom = LayoutData(0f, 0f, 370f, 39f)


        val bird   = LayoutData(128f, 580f, 114f, 80f)
        val record = LayoutData(538f, 320f, 366f, 159f)
    }

    object BG_Balk {
        val balkSize = Size(90f, 1055f)
        val doorSize = Size(90f, 332f)
        val balkTopY = 1387f
        val doorY    = 1055f
    }

    object BalkGenerator {
        val balkSize = Size(90f, 2442f)
        val startX = 1443f
        val minY = -1000f
        val maxY = -637f
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












