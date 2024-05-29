package com.goplaytoday.guildofhero.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Slot {
        val slot = LayoutData(0f, 0f, 110f, 110f, vs = 15f)
        val endY = -3102f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 130f, 130f, vs = -5f)
    }

    object SlotGroup {
        val slot = LayoutData(25f, 23f, 110f, 3735f, hs = 97f)
        val glow = LayoutData(15f, 13f, 130f, 630f, hs = 77f)
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












