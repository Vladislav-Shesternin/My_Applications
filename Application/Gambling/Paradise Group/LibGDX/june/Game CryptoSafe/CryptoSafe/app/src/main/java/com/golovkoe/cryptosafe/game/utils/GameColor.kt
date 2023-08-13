package com.golovkoe.cryptosafe.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val tial = rgba(124, 242, 250, 1f)
    val gren = rgba(160, 255, 115, 1f)
    val darkBlue = rgba(30, 31, 75, 1f)
    val darkGray = rgba(120, 122, 141, 1f)
    val up = rgba(71, 102, 249, 1f)
    val dw = rgba(255, 64, 59, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}