package com.lakalutic.statisticsmanager.game.utils

object Layout {

    val profile = LayoutData(43f, 1434f, 598f, 47f)
    val menu    = LayoutData(0f, 0f, 680f, 130f)



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












