package com.mariam.cleverfinancier.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val whitik = rgba(250, 250, 250, 1f)
    val grayba = rgba(125, 136, 149, 1f)
    val blacka = rgba(48, 56, 65, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}