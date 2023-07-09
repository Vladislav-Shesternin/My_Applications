package com.bango.weld.androit.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val purple = rgba(41, 0, 63, 1f)
    val green  = rgba(31, 233, 75, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}