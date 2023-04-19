package com.hsr.bkm.mobile.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Game {
        val panel   = LayoutData(0f,0f,700f,120f)
        val favorit = LayoutData(154f,15f,94f,89f)
        val home    = LayoutData(453f,17f,94f,84f)
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












