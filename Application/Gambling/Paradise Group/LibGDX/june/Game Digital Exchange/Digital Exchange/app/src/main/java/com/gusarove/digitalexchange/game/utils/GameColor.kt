package com.gusarove.digitalexchange.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val falsek = rgba(218, 70, 70, 1f)
    val truesk = rgba(70, 218, 94, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}