package com.karpenkov.budgetgid.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(26f,65f,649f,1178f)
        val privacy = LayoutData(89f,133f,512f,43f)
        val terms   = LayoutData(89f,65f,357f,43f)
        val cBox    = LayoutData(565f,644f,110f,110f)
        val btn     = LayoutData(143f,271f,404f,210f)
    }

    object Converter {
        val panel = LayoutData(41f, 116f, 601f, 991f)
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












