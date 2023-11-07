package pro.ultimate.saver.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Loader {
        val progress = LayoutData(292f,16f,233f,89f)
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












