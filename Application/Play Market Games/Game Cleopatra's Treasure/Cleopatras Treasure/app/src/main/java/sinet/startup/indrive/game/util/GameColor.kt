package sinet.startup.indrive.game.util

import com.badlogic.gdx.graphics.Color

object GameColor {

//    val gold = rgba(170, 90, 27, 1);


    fun rgba(r: Int, g: Int, b: Int, a: Int = 1) = Color(r / 255f, g / 255f, b / 255f, a.toFloat())

}