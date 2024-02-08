package com.huge.casine.slyts.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val ylow = rgba(220, 224, 26, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}