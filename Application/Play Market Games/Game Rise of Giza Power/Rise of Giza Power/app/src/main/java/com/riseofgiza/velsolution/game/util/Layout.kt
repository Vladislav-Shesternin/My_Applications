package com.riseofgiza.velsolution.game.util

object Layout {

    object Splash {
        val progress = LayoutData(230f, 0f, 277f, 177f)
    }

    object Game {
        val balancePanel = LayoutData(100f, 1162f, 498f, 156f)
        val balanceText  = LayoutData(43f, 45f, 386f, 84f)
        val betPanel     = LayoutData(234f, 348f, 230f, 72f)
        val betText      = LayoutData(26f, 25f, 178f, 38f)
        val plus         = LayoutData(504f, 334f, 100f, 100f)
        val minus        = LayoutData(87f, 334f, 100f, 100f)
        val spin         = LayoutData(230f, 39f, 239f, 239f)
        val spinText     = LayoutData(39f, 78f, 156f, 82f)
        val slotGroup    = LayoutData(-9f, 433f, 718f, 668f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 95f, 95f, vs = 22f)
        val startY = 54f
        val endY   = -4278f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 148f, 152f, vs = -35f)
    }

    object SlotGroup {
        val mask = LayoutData(108f, 106f, 481f, 438f)
        val slot = LayoutData(47.5f, 54f, 95f, 4662f, hs = 48f)
        val glow = LayoutData(130f, 117f, 148f, 386f, hs = -5f)
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












