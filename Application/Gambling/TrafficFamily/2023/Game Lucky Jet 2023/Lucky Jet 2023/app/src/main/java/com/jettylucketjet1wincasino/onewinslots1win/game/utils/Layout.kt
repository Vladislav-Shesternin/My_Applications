package com.jettylucketjet1wincasino.onewinslots1win.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val logo = LayoutData(35f,288f,337f,298f)
    }

    object Menu {
        val menu   = LayoutData(43f,204f,359f,545f)
        val button = LayoutData(45f,265f,357f,72f)
    }

    object Game {
        val panel   = LayoutData(33f,847f,372f,46f)
        val jet     = LayoutData(-4f, 225f,343f,297f)

        val itemSize   = Size(71f, 71f)
        val itemMinPos = Vector2(0f, 0f)
        val itemMaxPos = Vector2(375f, 773f)

        val jetMinPos = Vector2(-123f, -55f)
        val jetMaxPos = Vector2(115f, 630f)
    }

    object Result {
        val count   = LayoutData(241f,447f,34f,25f)
        val percent = LayoutData(242f,422f,47f,25f)
        val restart = LayoutData(45f,300f,357f,72f)
        val send    = LayoutData(45f,210f,357f,72f)
    }

    object Panel {
        val count  = LayoutData(0f,0f,96f,46f)
        val time   = LayoutData(254f,0f,118f,46f)
    }

    object Jet {
        val fire = LayoutData(0f,0f,256f,154f)
        val man  = LayoutData(169f,49f,174f,247f)
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












