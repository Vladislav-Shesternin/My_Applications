package com.mesga.moolahit.game.util

import com.badlogic.gdx.math.Vector2

object Layout {

    object Splash {
        val progress = LayoutData(909f, 0f, 371f, 207f)
    }

    object Game {
        val balancePanel = LayoutData(23.5f, 12f, 270f, 101f)
        val balanceText  = LayoutData(29.5f, 6.5f, 212f, 42f)
        val betPanel     = LayoutData(976f, 22.5f, 168f, 74f)
        val betText      = LayoutData(6f, 9.5f, 155f, 54f)
        val plus         = LayoutData(1158.5f, 29.5f, 36f, 59.5f)
        val minus        = LayoutData(926f, 29.5f, 36f, 59.5f)
        val spin         = LayoutData(645f, 6f, 182f, 108f)
        val slotGroup    = LayoutData(293f, 124f, 919f, 471f)
    }

    object Slot {
        val slot = LayoutData(0f, 0f, 143f, 143f, vs = 10f)
        val startY = 7f
        val endY   = -8102f
    }

    object Glow {
        val glow = LayoutData(0f, 0f, 191f, 162f, vs = -9f)
    }

    object SlotGroup {
        val mask = LayoutData(4f, 4f, 911f, 463f)
        val slot = LayoutData(29f, 7f, 143f, 8558f, hs = 94f)
        val glow = LayoutData(9f, 2f, 191f, 468f, hs = 46f)
        val s    = LayoutData(218f, 54f, 18f, 363f, hs = 215f)
    }

    object S {
        val s = LayoutData(0f, 0f, 18f, 18f, vs = 51f)
    }

    object Bonus {
        val bonusStart = Vector2(5f, 720f)
        val bonusEndX  = 277f
        val bonusSize  = Size(18f, 18f)

        val balanceSensor = LayoutData(150f, 67f, 18f, 18f)

        val duploSize = Size(14f, 14f)
        val duploList = listOf<Vector2>(
            Vector2(45f, 534f),
            Vector2(101f, 509f),
            Vector2(143f, 532f),
            Vector2(195f, 495f),
            Vector2(238f, 539f),
            Vector2(59f, 465f),
            Vector2(121f, 454f),
            Vector2(162f, 433f),
            Vector2(209f, 414f),
            Vector2(238f, 440f),
            Vector2(19f, 421f),
            Vector2(52f, 396f),
            Vector2(115f, 385f),
            Vector2(176f, 346f),
            Vector2(26f, 353f),
            Vector2(45f, 325f),
            Vector2(79f, 346f),
            Vector2(136f, 339f),
            Vector2(12f, 279f),
            Vector2(108f, 272f),
            Vector2(162f, 296f),
            Vector2(219f, 303f),
            Vector2(73f, 240f),
            Vector2(162f, 233f),
            Vector2(209f, 261f),
            Vector2(252f, 247f),
            Vector2(38f, 189f),
            Vector2(122f, 196f),
            Vector2(212f, 187f),
            Vector2(100f, 139f),
            Vector2(181f, 132f),
        )
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












