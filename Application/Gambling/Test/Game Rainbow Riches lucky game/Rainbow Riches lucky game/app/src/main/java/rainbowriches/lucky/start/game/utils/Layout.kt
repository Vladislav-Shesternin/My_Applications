package rainbowriches.lucky.start.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val leprechaun = LayoutData(103f,608f,874f,1011f)
        val loading    = LayoutData(44f,535f,992f,152f)
    }

    object Buttons {
        val btns = listOf(
            Vector2(72f, 357f),
            Vector2(414f, 357f),
            Vector2(756f, 357f),
        )
    }

    data class LayoutData(
        var x: Float = 0f,
        var y: Float = 0f,
        var w: Float = 0f,
        var h: Float = 0f,
        // horizontal space
        val hs: Float = 0f,
        // vertical space
        val vs: Float = 0f,
    ) {

        fun position() = Vector2(x, y)
        fun size() = Vector2(w, h)

    }

}












