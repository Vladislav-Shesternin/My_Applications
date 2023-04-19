package com.zet.vest.app.game.util

import java.text.DecimalFormat

object CryptoUtil {

    val cryptoList = mutableListOf<Crypto>()

    class Crypto {
        var id    : Int    = -1
        var name  : String = "--"
        var symbol: String = "--"
        var price : Double = 0.0
        var logo  : String = "--"
    }
}