package com.hsr.bkm.mobile.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val background = rgba(32, 25, 66, 1f)
    val blue       = rgba(1, 127, 255, 1f)


    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}