package com.littleman.andball.game.util

object Layout {

    object Splash {
        val progress = LayoutData(1237f, 0f, 163f, 107f)
    }

    object Game {
        val pStart = LayoutData(524f, 378f, 96f, 136f)
        val bStart = LayoutData(700f, 950f, 42f, 42f)
        val panel  = LayoutData(584f, 0f, 231f, 103f)
        val tablo  = LayoutData(661f, 17f, 78f, 55f)

    }

    data class LSize(val w: Float, val h: Float)

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












