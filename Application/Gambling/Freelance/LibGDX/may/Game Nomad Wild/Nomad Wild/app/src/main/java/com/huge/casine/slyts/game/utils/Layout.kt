package com.huge.casine.slyts.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Baraban {
        val items = LayoutData(0f,296f,115f,115f, hs = 33f, vs = 33f)
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












