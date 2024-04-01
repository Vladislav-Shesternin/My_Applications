package com.aztec.blaze.game.util

object Layout {

    object Splash {
        val progress = LayoutData(550f, 0f, 300f, 106f)
    }

    object Game {
        val balancePanel = LayoutData(982f, 343f, 328f, 123f)
        val balanceText  = LayoutData(17f, 9f, 300f, 106f)
        val betPanel     = LayoutData(1045f, 237f, 201f, 75f)
        val betText      = LayoutData(17f, 9f, 167f, 55f)
        val plus         = LayoutData(1255f, 246f, 55f, 55f)
        val minus        = LayoutData(981f, 246f, 55f, 55f)
        val spin         = LayoutData(1063f, 41f, 165f, 165f)
        val slotGroup    = LayoutData(32f, 16f, 835f, 667f)
        val animGroup    = LayoutData(1129f, 488f, 63f, 182f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 114f, 114f, vs = 13f)
        val startY = 13f
        val endY   = -1892f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 185f, 185f, vs = -58f)
    }

    object SlotGroup {
        val mask = LayoutData(8f, 9f, 818f, 649f)
        val slot = LayoutData(45f, 13f, 114f, 2527f, hs = 40f)
        val glow = LayoutData(17f, -13f, 185f, 693f, hs = -31f)
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












