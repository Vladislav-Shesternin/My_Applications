package com.kurs.mon.fin.game.util

object Layout {

    object Exchanger {
        val backAnim   = LayoutData(0f, 0f, 800f, 1599f)
        val panelSize  = Size(635f, 132f)
        val panelStart = Vector2(800f, 834f)
        val panelEnd   = Vector2(82f, 734f)
        val click      = LayoutData(427f, 734f, 290f, 132f)
        val inputSize  = Size(292f, 132f)
        val leftStart  = Vector2(-292f, 1025f)
        val leftEnd    = Vector2(82f, 925f)
        val rightStart = Vector2(800f, 1025f)
        val rightEnd   = Vector2(425f, 925f)
        val currencies = LayoutData(444f, 107f, 231f, 601f)
        val scroll     = LayoutData(456f, 127f, 208f, 561f)
        val name       = LayoutData(444f, 767f, 231f, 65f)
        val left       = LayoutData(100f, 938f, 257f, 89f)
        val right      = LayoutData(443f, 938f, 257f, 89f)
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












