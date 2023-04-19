package com.vi.bt.online.game.util

object Layout {

    object Splash {
        val progress = LayoutData(551f, 17f, 296f, 91f)
    }

    object Game {
        val tablo  = LayoutData(552f, 370f, 296f, 92f)
        val person = LayoutData(604f, 14f, 192f, 262f)
        val ball   = LayoutData(0f, 700f, 52f, 52f)
        val basket = LayoutData(702f, 251f, 95f, 20f)
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
    )

}












