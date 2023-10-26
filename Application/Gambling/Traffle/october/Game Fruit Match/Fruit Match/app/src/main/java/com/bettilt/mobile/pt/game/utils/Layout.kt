package com.bettilt.mobile.pt.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(1280f,0f,281f,189f)
        val girl     = LayoutData(49f,0f,316f,482f)
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












