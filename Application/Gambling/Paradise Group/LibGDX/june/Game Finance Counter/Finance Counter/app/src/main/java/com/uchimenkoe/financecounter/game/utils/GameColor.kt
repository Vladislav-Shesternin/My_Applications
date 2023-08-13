package com.uchimenkoe.financecounter.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val bulu = rgba(48, 65, 225, 1f)
    val fugu = rgba(165, 160, 217, 1f)

    val _1 = rgba(0, 0, 0, 1f)
    val _2 = rgba(196, 24, 24, 1f)
    val _3 = rgba(156, 159, 18, 1f)
    val _4 = rgba(11, 173, 37, 1f)
    val _5 = rgba(106, 11, 97, 1f)
    val _6 = bulu

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}