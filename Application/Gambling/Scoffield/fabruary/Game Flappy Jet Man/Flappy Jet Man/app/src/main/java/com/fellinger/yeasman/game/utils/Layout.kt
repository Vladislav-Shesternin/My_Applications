package com.fellinger.yeasman.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Menu {
        val play   = LayoutData(770f, 440f, 380f, 201f)
        val record = LayoutData(536f, 146f, 391f, 120f)
        val count  = LayoutData(977f, 132f, 676f, 180f)
    }

    object Game {
        val borderTop    = LayoutData(0f, 1040f, 370f, 39f)
        val borderBottom = LayoutData(0f, 0f, 370f, 39f)
        val balk         = LayoutData(1361f, -681f, 90f, 2442f)


        val jet    = LayoutData(0f, 442f, 225f, 195f)
        val man    = LayoutData(110f, 475f, 114f, 162f)
        val record = LayoutData(1644f, 0f, 276f, 124f)
    }

    object JetAnim {
        val fire = LayoutData(0f, 0f, 168f, 101f)
        val man  = LayoutData(111f, 32f, 114f, 162f)
    }

    object BG_Balk {
        val balkSize = Size(90f, 1055f)
        val doorSize = Size(90f, 332f)
        val balkTopY = 1387f
        val doorY    = 1055f
    }

    object BalkGenerator {
        val balkSize = Size(90f, 2442f)
        val startX = 1920f
        val minY = -962f
        val maxY = -402f
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












