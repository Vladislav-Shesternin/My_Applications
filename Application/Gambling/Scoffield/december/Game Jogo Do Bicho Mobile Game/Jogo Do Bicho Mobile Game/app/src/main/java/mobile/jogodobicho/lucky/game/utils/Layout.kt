package mobile.jogodobicho.lucky.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Loading {
        val bab     = LayoutData(-6f,1134f,1094f,785f)
        val loading = LayoutData(124f,269f,785f,157f)
        val progres = LayoutData(132f,301f,186f,76f)
        val bull    = LayoutData(0f,0f,1080f,1141f)
        val play    = LayoutData(405f,1083f,269f,269f)
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












