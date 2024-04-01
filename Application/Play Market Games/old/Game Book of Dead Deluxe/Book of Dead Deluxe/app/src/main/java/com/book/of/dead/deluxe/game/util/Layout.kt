package com.book.of.dead.deluxe.game.util

object Layout {

    object Splash {
        val progress = LayoutData(579f, 27f, 243f, 113f)
    }

    object Game {
        val balancePanel = LayoutData(961f, 48f, 268f, 97f)
        val balanceText  = LayoutData(16f, 8f, 236f, 47f)
        val betPanel     = LayoutData(646f, 22f, 210f, 116f)
        val betText      = LayoutData(29f, 12f, 152f, 91f)
        val plus         = LayoutData(857f, 46f, 53f, 70f)
        val minus        = LayoutData(593f, 46f, 53f, 70f)
        val spin         = LayoutData(230f, 21f, 214f, 118f)
        val slotGroup    = LayoutData(240f, 195f, 1107f, 545f)
        val bonus        = LayoutData(76f, 200f, 58f, 488f)
        val infoButton   = LayoutData(69f, 80f, 56f, 56f)
        val infoPanel    = LayoutData(306f, 229f, 868f, 452f)
        val infoImage    = LayoutData(116f, 55f, 636f, 341f)
    }


    object Slot {
        val slot = LayoutData(0f, 0f, 163f, 163f, vs = 21f)
        val startY = 5f
        val endY   = -8642f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 301.5f, 301.5f, vs = -121f)
    }

    object SlotGroup {
        val slot = LayoutData(35f, 5f, 163f, 9179f, hs = 57f)
        val glow = LayoutData(-30f, -61.5f, 301.5f, 663.5f, hs = -85f)
    }

    object Bonus {
        val bonus = LayoutData(0f, 0f, 58f, 58f, vs = 28f)
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












