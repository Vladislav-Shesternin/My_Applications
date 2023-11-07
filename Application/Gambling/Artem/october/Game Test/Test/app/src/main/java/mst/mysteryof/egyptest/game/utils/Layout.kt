package mst.mysteryof.egyptest.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(301f,0f,477f,272f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 142f, 142f, vs = 17f)
        val endY = -7214f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 153f, 153f, vs = 7f)
    }

    object SlotGroup {
        val slot = LayoutData(67f, 41f, 142f, 7715f, hs = 50f)
        val glow = LayoutData(89f, 72f, 153f, 473f, hs = 39f)
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












