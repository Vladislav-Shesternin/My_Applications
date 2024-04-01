package com.aztec.firevoll.game.util

object Layout {

    object Splash {
        val progress = LayoutData(657f, 46f, 286f, 129f)
    }

    object Game {
        val balancePanel = LayoutData(607f, 10f, 385f, 106f)
        val balanceText  = LayoutData(20f, 15f, 346f, 70f)
        val spin         = LayoutData(1423f, 307f, 123f, 123f)
        val auto         = LayoutData(1447f, 495f, 76f, 76f)
        val slotGroup    = LayoutData(226f, 127f, 1148f, 735f)
        val panel        = LayoutData(1409f, 236f, 152f, 429f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 141f, 141f, vs = 30f)
        val startY = 16f
        val endY   = -5798f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 167f, 167f, vs = 4f)
    }

    object SlotGroup {
        val mask = LayoutData(52f, 25f, 1043f, 685f)
        val slot = LayoutData(37f, 16f, 141f, 6468f, hs = 66f)
        val glow = LayoutData(69f, 28f, 167f, 680f, hs = 40f)
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












