package com.gen.bettermeditatio.game.util

import com.badlogic.gdx.graphics.Color

object GameColor {

    val background = rgba(3, 218, 197, 1)



    fun rgba(r: Int, g: Int, b: Int, a: Int = 1) = Color(r / 255f, g / 255f, b / 255f, a.toFloat())

}