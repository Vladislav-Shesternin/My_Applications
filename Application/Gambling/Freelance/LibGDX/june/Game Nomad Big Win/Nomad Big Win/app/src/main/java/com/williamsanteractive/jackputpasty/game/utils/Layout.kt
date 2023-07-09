package com.williamsanteractive.jackputpasty.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val girl = LayoutData(224f,0f,1295f,679f)
    }

    object Levels {
        val scroll = LayoutData(0f,70f,1320f,502f)
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












