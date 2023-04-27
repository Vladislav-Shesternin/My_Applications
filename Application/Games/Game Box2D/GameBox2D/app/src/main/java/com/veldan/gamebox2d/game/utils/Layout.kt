package com.veldan.gamebox2d.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(615f,63f,170f,100f)
    }

    object Game {

        val btns = listOf(
            LayoutData(20f,460f,100f,100f),
            LayoutData(140f,580f,100f,100f),
            LayoutData(1280f,140f,100f,100f),
            LayoutData(1160f,20f,100f,100f),
        )

        val orbSize = Size(72f,72f)
        val orbPos  = Vector2(533f, 388f)
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












