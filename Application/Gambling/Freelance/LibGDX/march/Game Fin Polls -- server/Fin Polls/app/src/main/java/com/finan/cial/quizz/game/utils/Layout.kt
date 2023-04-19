package com.finan.cial.quizz.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

//    object Splash {
//        val progress = LayoutData(1016f, 0f, 184f, 147f)
//    }

    object Menu {
        val btn = LayoutData(182f, 769f, 567f, 199f, vs = 53f)
    }

    object Game {
        val panel = LayoutData(64f,719f,803f,922f)
        val vidpo = LayoutData(19f,485f,893f,141f, vs = 50f)
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












