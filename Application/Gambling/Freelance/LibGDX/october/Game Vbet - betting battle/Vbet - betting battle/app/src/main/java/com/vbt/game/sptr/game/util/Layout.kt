package com.vbt.game.sptr.game.util

object Layout {

    object Splash {
        val progress = LayoutData(237f, 112f, 226f, 140f)
    }

    object Game {
        val balancePanel = LayoutData(119f, 1269f, 462f, 92f)
        val balanceText  = LayoutData(6f, 6f, 450f, 80f)
        val betPanel     = LayoutData(200f, 286f, 300f, 92f)
        val betText      = LayoutData(40f, 6f, 220f, 80f)
        val plus         = LayoutData(535f, 292f, 80f, 80f)
        val minus        = LayoutData(85f, 292f, 80f, 80f)
        val spin         = LayoutData(265f, 53f, 171f, 171f)
        val spinText     = LayoutData(20f, 45f, 129f, 80f)
        val slotGroup    = LayoutData(38f, 417f, 625f, 813f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 170f, 170f, vs = 19f)
        val startY = 33f
        val endY   = -10596f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 202f, 202f, vs = -12f)
    }

    object SlotGroup {
        val mask = LayoutData(5f, 5f, 615f, 803f)
        val slot = LayoutData(16f, 33f, 170f, 11366f, hs = 37f)
        val glow = LayoutData(5f, 22f, 202f, 770f, hs = 5f)
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












