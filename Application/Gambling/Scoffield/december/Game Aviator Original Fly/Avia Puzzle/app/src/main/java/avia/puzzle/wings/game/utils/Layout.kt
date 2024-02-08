package avia.puzzle.wings.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val aviator = LayoutData(0f,274f,600f,608f)
        val loading = LayoutData(17f,274f,564f,49f)
    }

    object Buttons {
        val btns = listOf(
            Vector2(157f, 696f),
            Vector2(157f, 562f),
            Vector2(157f, 428f),
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












