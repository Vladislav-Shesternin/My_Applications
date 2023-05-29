package com.hello.piramidka.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(22f,459f,757f,845f)
        val privacy = LayoutData(79f,818f,643f,47f)
        val terms   = LayoutData(67f,694f,666f,54f)
        val cBox    = LayoutData(692f,480f,67f,67f)
        val btn     = LayoutData(160f,73f,479f,274f)
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












