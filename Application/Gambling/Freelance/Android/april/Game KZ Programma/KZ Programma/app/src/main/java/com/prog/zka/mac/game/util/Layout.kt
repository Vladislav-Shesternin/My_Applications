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












