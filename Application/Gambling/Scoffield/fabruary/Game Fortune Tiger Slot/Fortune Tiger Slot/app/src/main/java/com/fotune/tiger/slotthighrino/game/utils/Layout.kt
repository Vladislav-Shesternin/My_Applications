package com.fotune.tiger.slotthighrino.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Slot {
        val slot = LayoutData(0f, 0f, 132f, 132f, vs = 44f)
        val endY = -1568f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 143f, 143f, vs = 33f)
    }

    object SlotGroup {
        val slot = LayoutData(77f, 16f, 132f, 2068f, hs = 116f)
        val glow = LayoutData(71f, 11f, 143f, 495f, hs = 105f)
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












