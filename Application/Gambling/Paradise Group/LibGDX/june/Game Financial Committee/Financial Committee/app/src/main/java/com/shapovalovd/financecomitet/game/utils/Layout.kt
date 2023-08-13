package com.shapovalovd.financecomitet.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(3f,318f,670f,1082f)
        val privacy = LayoutData(62f,963f,553f,50f)
        val terms   = LayoutData(139f,857f,398f,50f)
        val cBox    = LayoutData(292f,181f,93f,75f)
        val btn     = LayoutData(95f,615f,487f,129f)
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












