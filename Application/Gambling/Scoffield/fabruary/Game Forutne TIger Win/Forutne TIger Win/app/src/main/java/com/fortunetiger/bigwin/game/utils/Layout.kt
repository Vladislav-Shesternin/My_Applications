package com.fortunetiger.bigwin.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(319f,712f,1282f,176f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 125f, 125f, vs = 12f)
        val endY = -2678f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 259f, 146f, vs = -9f)
    }

    object SlotGroup {
        val slot = LayoutData(0f, 14f, 125f, 3365f, hs = 120f)
        val glow = LayoutData(-68f, -3f, 259f, 694f, hs = -14f)
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












