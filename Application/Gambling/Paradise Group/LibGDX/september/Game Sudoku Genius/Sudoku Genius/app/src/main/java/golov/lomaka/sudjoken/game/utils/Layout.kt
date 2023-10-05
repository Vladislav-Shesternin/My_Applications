package golov.lomaka.sudjoken.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Position {
        val list = listOf(
            Vector2(177f, 303f),
            Vector2(243f, 303f),
            Vector2(308f, 303f),
            Vector2(375f, 303f),
            Vector2(439f, 303f),
            Vector2(177f, 230f),
            Vector2(241f, 230f),
            Vector2(308f, 230f),
            Vector2(375f, 230f),
        )
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












