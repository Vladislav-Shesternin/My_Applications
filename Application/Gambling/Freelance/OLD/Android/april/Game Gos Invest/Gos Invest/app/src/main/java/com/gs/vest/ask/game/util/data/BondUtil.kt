package com.gs.vest.ask.game.util.data

import androidx.annotation.DrawableRes
import com.gs.vest.ask.R

object BondUtil {

    val bondList = mutableListOf<Bond>(
        Bond("Лукойл", R.drawable.a1),
        Bond("X5 Retail Group", R.drawable.a2),
        Bond("Сургутнефтегаз", R.drawable.a3),
        Bond("Магнит", R.drawable.a4),
        Bond("Татнефть", R.drawable.a5),
        Bond("Мегаполис", R.drawable.a6),
        Bond("НЛМК", R.drawable.a7),
        Bond("Evraz", R.drawable.a8),
        Bond("Новатэк", R.drawable.a9),
        Bond("UC Rusal", R.drawable.a10),
        Bond("VEON", R.drawable.a11),
        Bond("НОРНИКЕЛЬ", R.drawable.a12),
        Bond("СИБУР", R.drawable.a13),
        Bond("Северсталь", R.drawable.a14),
        Bond("МТС", R.drawable.a15),
        Bond("ММК", R.drawable.a16),
        Bond("УГМК", R.drawable.a17),
        Bond("Мегафон", R.drawable.a18),
        Bond("СУЭК", R.drawable.a19),
        Bond("Новый Поток", R.drawable.a20),
    ).shuffled()

    data class Bond(
        val name   : String,
        @DrawableRes
        val logo   : Int,
    ) {
        var cost   : Long   = 0L
        var percent: Double = 0.0
        var amount : Int    = 0
        val profit : Long   get() = (((cost * amount) / 100) * percent).toLong()

        init {
            generateCost()
            generatePercent()
        }

        fun generateCost() {
            cost = (500..3000).shuffled().first().toLong()
        }

        fun generatePercent() {
            percent = (5..20).shuffled().first().toDouble() + ((1..99).shuffled().first() / 100.0)
        }


    }
}