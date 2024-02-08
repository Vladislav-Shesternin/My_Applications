package com.thndure.giude.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(1067f,0f,355f,168f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 160f, 160f, vs = 59f)
        val endY = -8067f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 203f, 203f, vs = 16f)
    }

    object SlotGroup {
        val slot = LayoutData(15f, 36f, 160f, 8701f, hs = 65f)
        val glow = LayoutData(-6f, 15f, 203f, 641f, hs = 22f)
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












