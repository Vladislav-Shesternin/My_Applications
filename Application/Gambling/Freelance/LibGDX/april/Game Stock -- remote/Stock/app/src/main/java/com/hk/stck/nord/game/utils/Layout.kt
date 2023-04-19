package com.hk.stck.nord.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Menu {
        val btn = LayoutData(143f, 896f, 413f, 238f, vs = 27f)
    }

    object Game {
        val btn    = LayoutData(235f,20f,229f,132f)
        val detail = LayoutData(80f,493f,539f,925f)
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












