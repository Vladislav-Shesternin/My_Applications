package com.dankom.financialtracker.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val background = rgba(42, 55, 133, 1f)
    val blue       = rgba(43, 57, 134, 1f)
    val light      = rgba(3, 169, 244, 1f)
    val whiti      = rgba(248, 251, 255, 1f)
    val gray       = rgba(139, 152, 177, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}