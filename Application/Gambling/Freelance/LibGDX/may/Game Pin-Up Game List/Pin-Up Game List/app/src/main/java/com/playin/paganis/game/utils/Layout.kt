package com.playin.paganis.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object List {
        val names      = LayoutData(69f,500f,260f,1036f)
        val scrollSize = Size(800f, 277f)
        val scrollP    = Vector2(0f, 1118f)
        val scrollL    = Vector2(0f, 641f)
        val scrollN    = Vector2(0f, 164f)
        val slotW      = 246f
        val slotH      = 276f
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












