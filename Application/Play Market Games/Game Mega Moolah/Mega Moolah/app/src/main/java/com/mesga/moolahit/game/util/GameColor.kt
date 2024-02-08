package com.mesga.moolahit.game.util

import com.badlogic.gdx.graphics.Color

object GameColor {

    val background = rgba(218, 205, 151, 1)
    val green      = rgba(122, 170, 11, 1)



    fun rgba(r: Int, g: Int, b: Int, a: Int = 1) = Color(r / 255f, g / 255f, b / 255f, a.toFloat())

}