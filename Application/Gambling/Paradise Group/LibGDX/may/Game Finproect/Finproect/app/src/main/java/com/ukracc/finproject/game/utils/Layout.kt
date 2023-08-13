package com.ukracc.finproject.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash { val icon = LayoutData(146f,903f,512f,512f) }
    object Privacy {
        val panel   = LayoutData(14f,537f,775f,895f)
        val privacy = LayoutData(63f,913f,678f,83f)
        val terms   = LayoutData(63f,809f,678f,83f)
        val cBox    = LayoutData(353f,425f,97f,97f)
        val btn     = LayoutData(94f,143f,615f,151f)
    }

    object List {
        val topSepar = LayoutData(74f,1757f,656f,15f)
        val botSepar = LayoutData(74f,33f,656f,15f)
        val scroll   = LayoutData(72f,48f,660f,1709f)
    }

    object Detail {
        val icon   = LayoutData(72f, 1345f, 660f, 320f)
        val scroll = LayoutData(19f, 54f, 766f, 1242f)
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












