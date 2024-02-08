package aer.com.gamesas.mobile.slot.game.utils

import com.badlogic.gdx.math.Vector2

object Layout {


    object Menu {
        val play = LayoutData(138f,801f,433f,202f)
        val exit = LayoutData(138f,537f,433f,202f)
    }

    object Plinko {
        val rect = LayoutData(154f, 304f, 80f, 43f, hs = 80f)
        val circList = listOf(
            LayoutData(234f, 1064f, 80f, 80f),
            LayoutData(394f, 1064f, 80f, 80f),

            LayoutData(154f, 904f, 80f, 80f),
            LayoutData(314f, 904f, 80f, 80f),
            LayoutData(474f, 904f, 80f, 80f),

            LayoutData(74f, 744f, 80f, 80f),
            LayoutData(234f, 744f, 80f, 80f),
            LayoutData(394f, 744f, 80f, 80f),
            LayoutData(554f, 744f, 80f, 80f),

            LayoutData(154f, 584f, 80f, 80f),
            LayoutData(314f, 584f, 80f, 80f),
            LayoutData(474f, 584f, 80f, 80f),

            LayoutData(74f, 424f, 80f, 80f),
            LayoutData(234f, 424f, 80f, 80f),
            LayoutData(394f, 424f, 80f, 80f),
            LayoutData(554f, 424f, 80f, 80f),
        )
    }

    data class LayoutData(
        val x: Float = 0f,
        val y: Float = 0f,
        val w: Float = 0f,
        val h: Float = 0f,
        // horizontal space
        val hs: Float = 0f,
        // vertical space
        val vs: Float = 0f,
    ) {

        fun position() = Vector2(x, y)
        fun size() = Size(w, h)

    }

}












