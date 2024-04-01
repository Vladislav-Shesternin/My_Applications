package com.egyptian.rebirth.gremmy.util

object Layout {

    object Splash {
        val progress = LayoutData(1173f, 126f, 288f, 180f)
    }

    object Game {
        val balancePanel = LayoutData(169f, 34f, 352f, 96f)
        val balanceText  = LayoutData(44f, 19f, 256f, 66f)
        val betPanel     = LayoutData(1184f, 37f, 262f, 84f)
        val betText      = LayoutData(30f, 17f, 205f, 58f)
        val plus         = LayoutData(1457f, 51f, 57f, 57f)
        val minus        = LayoutData(1116f, 51f, 57f, 57f)
        val spin         = LayoutData(771f, 24f, 178f, 110f)
        val slotGroup    = LayoutData(233f, 157f, 1133f, 737f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 123f, 123f, vs = 30f)
        val startY = 11f
        val endY   = -9781f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 165f, 165f, vs = -12f)
    }

    object SlotGroup {
        val mask = LayoutData(116f, 56f, 913f, 604f)
        val slot = LayoutData(23f, 11f, 123f, 10374f, hs = 26f)
        val glow = LayoutData(118f, 46f, 165f, 624f, hs = -16f)
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












