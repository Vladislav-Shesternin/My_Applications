package com.csmttt.medus.play.game.util

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(637f, 0f, 527f, 226f)
        val ledy     = LayoutData(101f, 0f, 1599f, 430f)
    }

    object Game {
        val right     = LayoutData(1649f, 31f, 95f, 95f)
        val left      = LayoutData(56f, 31f, 95f, 95f)
        val leftArea  = LayoutData(0f, 0f, 237f, 180f)
        val rightArea = LayoutData(1563f, 0f, 237f, 180f)

        val tarelka               = LayoutData(781f, 0f, 237f, 81f)
        val meduzaBigSize         = Size(159f, 159f)
        val meduzaBigPositionList = listOf(
            Vector2(347f, 516f),
            Vector2(820f, 321f),
            Vector2(1293f, 516f),
        )
        val meduzaMiniSize         = Size(71f, 71f)
        val meduzaMiniPositionList = listOf(
            Vector2(191f, 453f),
            Vector2(564f, 453f),
            Vector2(1164f, 453f),
            Vector2(1537f, 453f),
            Vector2(164f, 164f),
            Vector2(864f, 164f),
            Vector2(1565f, 164f),
        )

        val mechRight = LayoutData(1229f, 271f, 148f, 113f)
        val mechLeft  = LayoutData(423f, 271f, 148f, 113f)
        val sikira    = LayoutData(851f, 541f, 97f, 256f)

        val elementStart = 29f
        val elementEnd   = 1690f
        val elementY     = 825f
        val elementSize  = Size(54f, 54f)

        val infoPanel  = LayoutData(153f, 65f, 1234f, 671f)
        val infoButton = LayoutData(28f, 347f, 106f, 106f)
    }

    object InfoPanel {
        val text = LayoutData(116f, 360f, 156f, 47f, hs = 127f, vs = 253f)
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












