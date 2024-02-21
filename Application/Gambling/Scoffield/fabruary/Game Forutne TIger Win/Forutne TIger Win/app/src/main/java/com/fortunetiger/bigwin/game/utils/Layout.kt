package com.fortunetiger.bigwin.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(319f,712f,1282f,176f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 150f, 150f, vs = 10f)
        val endY = -3163f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 237f, 182f, vs = -22f)
    }

    object SlotGroup {
        val slot = LayoutData(44f, 37f, 150f, 3990f, hs = 76f)
        val glow = LayoutData(1f, 21f, 237f, 822f, hs = -11f)
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












