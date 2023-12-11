package lycky.fortune.tiger.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val loader    = LayoutData(17f,321f,1047f,115f)
        val tiger  = LayoutData(38f,236f,1004f,1182f)
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












