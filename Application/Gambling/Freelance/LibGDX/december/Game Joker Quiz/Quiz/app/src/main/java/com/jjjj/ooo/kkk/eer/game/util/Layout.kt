package com.jjjj.ooo.kkk.eer.game.util

object Layout {

    object Splash {
        val progress = LayoutData(43f, 1545f, 338f, 258f)
    }

    object Game {
        val panel     = LayoutData(37f, 371f, 827f, 1436f)
        val checkBox  = LayoutData(37f, 87f, 314f, 182f, hs = 199f)
        val text      = LayoutData(62f, 389f, 776f, 1399f)
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












