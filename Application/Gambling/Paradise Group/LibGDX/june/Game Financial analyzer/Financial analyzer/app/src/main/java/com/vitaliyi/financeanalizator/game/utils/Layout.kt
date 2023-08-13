package com.vitaliyi.financeanalizator.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(16f,763f,645f,220f)
        val conucyP = LayoutData(188f,89f,335f,51f)
        val terms   = LayoutData(227f,36f,257f,51f)
        val bix     = LayoutData(16f,393f,36f,36f)
        val nbt     = LayoutData(16f,210f,645f,111f)
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












