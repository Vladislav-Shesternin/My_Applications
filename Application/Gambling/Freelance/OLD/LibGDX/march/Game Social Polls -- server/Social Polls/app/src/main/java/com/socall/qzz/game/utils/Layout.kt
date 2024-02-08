package com.socall.qzz.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(1016f, 0f, 184f, 147f)
    }

    object Menu {
        val quiz  = LayoutData(470f, 331f, 259f, 259f)
        val exit  = LayoutData(499f, 85f, 201f, 201f)
        val music = LayoutData(1059f, 37f, 100f, 100f)
    }

    object Game {
        val panel = LayoutData(67f,194f,1066f,452f)
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












