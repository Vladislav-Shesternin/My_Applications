package com.gusarove.digitalexchange.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(36f,0f,831f,1643f)
        val privacy = LayoutData(192f,748f,652f,84f)
        val terms   = LayoutData(40f,883f,433f,86f)
        val cBox    = LayoutData(73f,194f,195f,195f)
        val btn     = LayoutData(402f,0f,465f,322f)
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












