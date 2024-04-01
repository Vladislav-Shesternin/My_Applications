package sinet.startup.indrive.game.util

object Layout {

    object Splash {
        val progress = LayoutData(562f, 291f, 277f, 119f)
    }

    object Game {
        val balancePanel = LayoutData(55f, 502f, 403f, 166f)
        val balanceText  = LayoutData(75f, 13f, 315f, 31f)
        val betPanel     = LayoutData(74f, 296f, 365f, 109f)
        val betMask      = LayoutData(49f, 23f, 268f, 37f)
        val betText      = LayoutData(2f, 0f, 263f, 31f)
        val plus         = LayoutData(318f, 15f, 40f, 66f)
        val minus        = LayoutData(11f, 19f, 38f, 62f)
        val spin         = LayoutData(173f, 64f, 168f, 168f)
        val slotGroup    = LayoutData(536f, 79f, 829f, 588f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 112f, 112f, vs = 18f)
        val startY = 57.5f
        val endY   = -7352.5f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 169f, 168f, vs = -39f)
    }

    object SlotGroup {
        val mask = LayoutData(34f, 13f, 759.5f, 471.5f)
        val slot = LayoutData(58f, 57.5f, 112f, 7782f, hs = 21f)
        val glow = LayoutData(64f, 42f, 168f, 429f, hs = -35f)
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












