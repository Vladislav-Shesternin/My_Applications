package com.veldan.gamebox2d.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val background = rgba(14, 0, 102, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}