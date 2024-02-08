package com.jumping.cubuletus.game.util

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val panel    = LayoutData(495f, 19f, 211f, 52f)
        val progress = LayoutData(561f, 31f, 79f, 29f)
    }

    object Game {
        val miniCoin  = LayoutData(559f, 26f, 38f, 38f)
        val coinLabel = LayoutData(606f, 31f, 79f, 29f)
        val up        = LayoutData(1038f, 18f, 74f, 74f)

        val frame = LayoutData(-10f, 115f, 1220f, 570f)
        val coub  = LayoutData(573f, 265f, 54f, 54f)
        val batut = LayoutData(214f, 125f, 82f, 49f, hs = 263f)
        val coin  = LayoutData(0f, 332f, 56f, 56f)

        val coinEnd = 1154f
    }

    object Coub {
        val colors = LayoutData(4f, 45f, 5f, 5f, hs = 36f, vs = 36f)
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












