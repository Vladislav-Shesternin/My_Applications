package com.tmesfo.frtunes.game.utils

import com.badlogic.gdx.graphics.Color

object Color {

    val WHITE_2 = rgba(255, 249, 226, 1)
    val BLACK_2 = rgba(33, 31, 32, 1);



    fun rgba(r: Int, g: Int, b: Int, a: Int = 1) = Color(r / 255f, g / 255f, b / 255f, a.toFloat())

}