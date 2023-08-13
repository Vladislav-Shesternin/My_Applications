package com.shapovalovd.financecomitet.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val bab = rgba(25, 23, 61, 1f)
    val gri = rgba(83, 255, 131, 1f)
    val pur = rgba(123, 120, 170, 1f)
    val gru = rgba(47, 161, 93, 1f)
    val rud = rgba(200, 26, 36, 1f)

    val _1 = rgba(25, 23, 61, 1f)
    val _2 = rgba(15, 221, 122, 1f)
    val _3 = rgba(210, 13, 155, 1f)
    val _4 = rgba(71, 24, 204, 1f)
    val _5 = rgba(239, 244, 41, 1f)
    val _6 = rgba(195, 29, 29, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}