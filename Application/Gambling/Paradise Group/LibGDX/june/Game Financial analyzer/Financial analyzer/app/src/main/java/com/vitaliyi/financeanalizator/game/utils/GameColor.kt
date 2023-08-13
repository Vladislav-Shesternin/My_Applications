package com.vitaliyi.financeanalizator.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val babkun = rgba(40, 43, 51, 1f)
    val white  = rgba(148, 149, 153, 1f)
    val pink   = rgba(241, 3, 174, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}