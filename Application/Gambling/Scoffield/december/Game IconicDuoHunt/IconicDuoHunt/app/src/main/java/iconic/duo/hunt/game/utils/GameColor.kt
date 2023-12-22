package iconic.duo.hunt.game.utils

import com.badlogic.gdx.graphics.Color

object GameColor {

    val background = rgba(150, 4, 4, 1f)
    val green      = rgba(62, 216, 87, 1f)

    fun rgba(r: Int, g: Int, b: Int, a: Float = 1f) = Color(r / 255f, g / 255f, b / 255f, a)

}