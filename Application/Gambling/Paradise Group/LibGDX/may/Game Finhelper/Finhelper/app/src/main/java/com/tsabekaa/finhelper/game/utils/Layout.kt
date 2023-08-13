package com.tsabekaa.finhelper.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {
    object Privacy {
        val panel   = LayoutData(0f,441f,623f,677f)
        val privacy = LayoutData(61f,554f,500f,86f)
        val terms   = LayoutData(61f,441f,500f,86f)
        val cBox    = LayoutData(14f,728f,69f,69f)
        val btn     = LayoutData(61f,126f,520f,156f)
    }

    object Menu {
        val btn = LayoutData(51f, 756f, 520f, 156f, vs = 23f)
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












