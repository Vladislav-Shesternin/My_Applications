package com.hellhot.competition.game.util

object Layout {

    object Splash {
        val progress = LayoutData(280f, 8f, 373f, 215f)
    }

    object Game {
        val right = LayoutData(1696f, 100f, 104f, 150f)
        val left  = LayoutData(0f, 100f, 104f, 150f)
        val leftArea  = LayoutData(0f, 0f, 245f, 288f)
        val rightArea = LayoutData(1555f, 0f, 245f, 288f)
        val tare       = LayoutData(800f, 2f, 201f, 53f)
        val scullLeft  = LayoutData(177f, 306f, 91f, 92f)
        val scullRight = LayoutData(1533f, 306f, 91f, 92f)

        val duploList = listOf(
            LayoutData(192f, 627f, 60f, 60f, hs = 166f),
            LayoutData(103f, 500f, 60f, 60f, hs = 247f),
            LayoutData(-14f, 373f, 60f, 60f, hs = 382f),
            LayoutData(393f, 246f, 60f, 60f, hs = 258f),
            LayoutData(185f, 120f, 60f, 60f, hs = 397f),
        )

        val bonus1  = LayoutData(8f, 753f, 48f, 48f)
        val bonus2  = LayoutData(8f, 692f, 48f, 48f)
        val bonus1Label = LayoutData(65f, 759f, 112f, 35f)
        val bonus2Label = LayoutData(65f, 698f, 112f, 35f)
        val bonusStart = 50f
        val bonusEnd   = 1700f
        val bonusY     = 810f
        val bonusSize  = Size(48f, 48f)
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












