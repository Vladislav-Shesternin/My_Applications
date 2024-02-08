package com.zet.vest.app.game.util

object Layout {

    object Balance {
        val balanceTitle = LayoutData(104f,83f,492f,166f)
        val balance      = LayoutData(30f,249f,639f,104f)
        val trading      = LayoutData(38f,467f,624f,161f)
        val portfolio    = LayoutData(38f,742f,624f,126f)
        val empty        = LayoutData(241f,982f,217f,111f)
    }

    object Cryptocurrencies {
        val title      = LayoutData(74f, 39f, 552f, 86f)
        val separator  = LayoutData(53f, 187f, 594f, 8f)
        val panel      = LayoutData(53f, 199f, 594f, 1301f)
        val cryptoItem = LayoutData(0f, 0f, 594f, 87f, vs = 8f)
    }

    object Trading {
        val symbol     = LayoutData(29f, 54f, 252f, 58f)
        val name       = LayoutData(29f, 148f, 639f, 104f)
        val price      = LayoutData(30f, 288f, 639f, 104f)
        val chartsView = LayoutData(20f, 428f, 660f, 700f)
        val sell       = LayoutData(62f, 1217f, 256f, 128f)
        val buy        = LayoutData(382f, 1217f, 256f, 128f)
    }



    object CryptoItem {
        val separator = LayoutData(0f, 83f, 594f, 8f)
        val arrow     = LayoutData(577f, 23f, 17f, 30f)
        val logo      = LayoutData(0f, 0f, 75f, 75f)
        val name      = LayoutData(92f, 13f, 468f, 50f)
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
    ) {
        fun position() = Vector2(x, y)
        fun size() = Size(w, h)
    }

}












