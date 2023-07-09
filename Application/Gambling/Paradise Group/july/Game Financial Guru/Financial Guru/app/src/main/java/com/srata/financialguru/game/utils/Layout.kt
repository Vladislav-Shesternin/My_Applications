package com.srata.financialguru.game.utils

object Layout {

    val golova = LayoutData(0f, 1338f, 690f, 77f)



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

      //  fun position() = Vector2(x, y)
      //  fun size() = Size(w, h)

    }

}












