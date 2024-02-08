package com.hgrt.wrld.game.util

object Layout {

    object Splash {
        val progress = LayoutData(586f, 567f, 227f, 133f)
    }

    object Game {
        val balancePanel = LayoutData(30f, 514f, 290f, 171f)
        val balanceText  = LayoutData(30f, 12f, 227f, 133f)
        val betPanel     = LayoutData(59f, 217f, 231f, 267f)
        val betCheckBox  = LayoutData(151f, 45f, 31f, 31f, vs = 17f)
        val spin         = LayoutData(101f, 30f, 148f, 148f)
        val slotGroup    = LayoutData(337f, 16f, 1018f, 669f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 139f, 139f, vs = 38f)
        val startY = 58f
        val endY   = -8260.5f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 228f, 210f, vs = -33f)
    }

    object SlotGroup {
        val mask = LayoutData(39f, 35f, 940f, 598f)
        val slot = LayoutData(20f, 58f, 139f, 8812f, hs = 13f)
        val glow = LayoutData(9f, 54f, 228f, 564f, hs = -76f)
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












