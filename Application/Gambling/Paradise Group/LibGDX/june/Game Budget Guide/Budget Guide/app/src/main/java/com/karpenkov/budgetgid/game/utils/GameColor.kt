package com.karpenkov.budgetgid.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val purpil = rgba(135, 63, 140, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}