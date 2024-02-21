package com.rcks.scaloi.game.util

object Layout {

    object Splash {
        val progress = LayoutData(1436f, 767f, 457f, 313f)
    }

    object Game {
        val balancePanel = LayoutData(864f, 49f, 725f, 123f)
        val box          = LayoutData(872f, 183f, 207f, 123f, hs = 44f)
        val knopa        = LayoutData(1634f, 49f, 246f, 246f)
        val slotGroup    = LayoutData(94f, 49f, 725f, 983f)

        val svetchenie = LayoutData(1079f, 298f, 698f, 698f)
        val santa      = LayoutData(1172f, 580f, 480f, 160f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 143f, 143f, vs = 54f)
        val startY = 26f
        val endY   = -18_689f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 214f, 214f, vs = -17f)
    }

    object SlotGroup {
        val mask = LayoutData(10f, 10f, 705f, 963f)
        val slot = LayoutData(66f, 26f, 143f, 19_646f, hs = 82f)
        val glow = LayoutData(36f, 5f, 214f, 1002f, hs = 11f)
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












