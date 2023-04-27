package com.prog.zka.mac.game.util

object Layout {

    object Minutes {
        val ygada = LayoutData(118f,61f,205f,77f)
        val stran = LayoutData(272f,169f,206f,77f)
        val pofla = LayoutData(418f,277f,259f,77f)
        val flags = LayoutData(74f,446f,602f,295f)
        val b1    = LayoutData(134f,859f,482f,157f)
        val b2    = LayoutData(134f,1058f,482f,157f)
        val b3    = LayoutData(134f,1257f,482f,157f)
    }

    object Geometrical {
        val circle = LayoutData(-87f, -67f,924f,924f)
        val flag   = LayoutData(125f, 145f,500f,500f)

        val btnSize = Size(481f, 181f)
        val btnPos  = listOf<Vector2>(
            Vector2(5f, 832f),
            Vector2(263f, 1004f),
            Vector2(5f, 1176f),
            Vector2(263f, 1348f),
        )
        val btnStartPos  = listOf<Vector2>(
            Vector2(-481f, 832f),
            Vector2(750f, 1004f),
            Vector2(-481f, 1176f),
            Vector2(750f, 1348f),
        )
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












