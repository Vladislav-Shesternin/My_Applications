package com.uchimenkoe.financecounter.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(26f,587f,793f,952f)
        val privacy = LayoutData(26f,1027f,786f,51f)
        val terms   = LayoutData(26f,929f,786f,51f)
        val cBox    = LayoutData(26f,587f,100f,100f)
        val btn     = LayoutData(136f,171f,572f,197f)
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












