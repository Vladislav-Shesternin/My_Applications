package com.veldan.gamebox2d.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(541f,0f,318f,187f)
    }

    object Progress {
        val progressLength = 587f
        val size = Vector2(616f, 16f)
        val back = Vector2(0f, 6f)
        val leverSize = Vector2(29f, 29f)
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
        fun size() = Vector2(w, h)

    }

}












