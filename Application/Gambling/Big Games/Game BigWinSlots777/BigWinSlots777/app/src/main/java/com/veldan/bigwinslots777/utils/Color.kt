package com.veldan.bigwinslots777.utils

import com.badlogic.gdx.graphics.Color

object Color {

    val DARK_BLUE = rgba(3, 101, 117, 1)
    val GREEN     = rgba(27, 212, 113, 1)
    val RED       = rgba(223, 9, 9, 1)



    fun rgba(r: Int, g: Int, b: Int, a: Int = 1) = Color(r / 255f, g / 255f, b / 255f, a.toFloat())

}