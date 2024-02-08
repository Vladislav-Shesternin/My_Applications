package com.aztec.firevoll.game.util

import com.badlogic.gdx.graphics.Color

object GameColor {

    val red = rgba(212, 49, 11, 1)
    val background = rgba(217, 142, 76, 1)



    fun rgba(r: Int, g: Int, b: Int, a: Int = 1) = Color(r / 255f, g / 255f, b / 255f, a.toFloat())

}