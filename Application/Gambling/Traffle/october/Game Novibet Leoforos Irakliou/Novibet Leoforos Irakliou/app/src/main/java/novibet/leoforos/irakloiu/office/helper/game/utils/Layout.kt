package novibet.leoforos.irakloiu.office.helper.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(649f,26f,153f,85f)
        val title    = LayoutData(76f,497f,450f,339f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 140f, 140f, vs = 137f)
        val endY = -10518f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 331f, 299f, vs = -23f)
    }

    object SlotGroup {
        val slot = LayoutData(70f, 8f, 140f, 11220f, hs = 180f)
        val glow = LayoutData(-26f, -23f, 331f, 851f, hs = -11f)
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












