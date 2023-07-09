package com.vurda.fina.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val loader = rgba(39, 50, 56, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}