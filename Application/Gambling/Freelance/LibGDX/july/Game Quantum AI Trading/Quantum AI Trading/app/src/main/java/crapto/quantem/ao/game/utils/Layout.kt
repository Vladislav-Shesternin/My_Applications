package crapto.quantem.ao.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(16f,675f,645f,220f)
        val privacy = LayoutData(110f,48f,206f,66f)
        val terms   = LayoutData(347f,48f,252f,66f)
        val cBox    = LayoutData(16f,393f,36f,36f)
        val btn     = LayoutData(16f,210f,645f,111f)
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
        fun size() = Size(w, h)

    }

}












