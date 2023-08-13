package com.plugoya.rosgpb.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(16f,490f,731f,1053f)
        val privacy = LayoutData(69f,880f,625f,39f)
        val terms   = LayoutData(153f,782f,456f,53f)
        val cBox    = LayoutData(653f,490f,78f,72f)
        val btn     = LayoutData(0f,0f,763f,435f)
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












