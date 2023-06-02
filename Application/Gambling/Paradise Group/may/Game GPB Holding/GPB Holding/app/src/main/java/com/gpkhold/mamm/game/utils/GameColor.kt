package com.gpkhold.mamm.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val dark  = rgba(47, 79, 127, 1f)
    val light = rgba(30, 126, 242, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}