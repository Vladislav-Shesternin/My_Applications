package fortunetiger.you.luck.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val loa     = LayoutData(44f,-37f,999f,1542f)
        val loading = LayoutData(61f,1250f,958f,62f)
        val progres = LayoutData(885f,1259f,109f,41f)
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












