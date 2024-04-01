package com.gen.bettermeditatio.game.util

object Layout {

    object Splash {
        val progress = LayoutData(176f, 81f, 244f, 140f)
    }

    object Game {
        val balancePanel = LayoutData(488f, 0f, 425f, 70f)
        val balanceText  = LayoutData(20f, 0f, 385f, 60f)
        val spin         = LayoutData(1238f, 0f, 162f, 113f)
        val slotGroup    = LayoutData(244f, 147f, 912f, 405f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 222f, 222f, vs = 137f)
        val startY = 91f
        val endY   = -3140f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 233f, 233f)
    }

    object SlotGroup {
        val slot = LayoutData(23f, 91f, 222f, 3453f, hs = 91f)
        val glow = LayoutData(-77f, 60f, 1048f, 286f)
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












