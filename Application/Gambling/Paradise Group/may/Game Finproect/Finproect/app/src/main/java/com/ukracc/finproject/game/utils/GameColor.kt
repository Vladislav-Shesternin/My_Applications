package com.ukracc.finproject.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

   // val wit = rgba(251, 251, 249, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}