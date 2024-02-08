package com.wlfe.astiener.game.util

object Layout {

    object Splash {
        val progress = LayoutData(1183f, 0f, 217f, 98f)
    }

    object Game {
        val balancePanel = LayoutData(1147f, 23f, 46f, 43f)
        val balanceText  = LayoutData(884f, 4f, 246f, 73f)
        val betPanel     = LayoutData(179f, 30f, 27f, 27f)
        val betText      = LayoutData(40f, 10f, 127f, 73f)
        val plus         = LayoutData(292f, 19f, 53f, 53f)
        val minus        = LayoutData(230f, 19f, 53f, 53f)
        val spin         = LayoutData(534f, -12f, 287f, 138f)
        val slotGroup    = LayoutData(23f, 104f, 977f, 580f)
        val great        = LayoutData(1103f, 180f, 221f, 221f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 166f, 166f, vs = 25f)
        val startY = 35f
        val endY   = -5714f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 233f, 233f, vs = -42f)
    }

    object SlotGroup {
        val slot = LayoutData(14f, 35f, 166f, 6278f, hs = 30f)
        val glow = LayoutData(-19f, -18f, 233f, 615f, hs = -37f)
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












