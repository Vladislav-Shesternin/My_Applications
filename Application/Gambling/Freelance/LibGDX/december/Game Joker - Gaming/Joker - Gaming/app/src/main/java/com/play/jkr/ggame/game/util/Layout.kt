package com.play.jkr.ggame.game.util

import com.badlogic.gdx.math.Vector2

object Layout {

    object Game {
        val size = Vector2(107f, 153f)
        val list = listOf(
            Vector2(42f, 466f),
            Vector2(42f, 219f),
            Vector2(193f, 619f),
            Vector2(193f, 66f),
            Vector2(344f, 543f),
            Vector2(344f, 142f),
            Vector2(495f, 620f),
            Vector2(452f, 343f),
            Vector2(495f, 65f),
            Vector2(646f, 544f),
            Vector2(646f, 141f),
            Vector2(797f, 620f),
            Vector2(840f, 343f),
            Vector2(797f, 65f),
            Vector2(948f, 543f),
            Vector2(948f, 142f),
            Vector2(1099f, 619f),
            Vector2(1099f, 66f),
            Vector2(1250f, 466f),
            Vector2(1250f, 219f),
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
    )

}












