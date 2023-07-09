package com.golovkoe.cryptosafe.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.golovkoe.cryptosafe.game.manager.FontTTFManager
import com.golovkoe.cryptosafe.game.manager.SpriteManager
import com.golovkoe.cryptosafe.game.utils.GameColor
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedGroup
import java.util.Random

class BitCoin: AdvancedGroup() {

    private val names = listOf(
        "StellarCoin",
        "Etherex",
        "RippleNet",
        "LiteGold",
        "NeoDime",
        "DashLink",
        "BitGem",
        "VeriTrade",
        "NanoPay",
        "CardanoToken",
        "MonarchCoin",
        "Tronexus",
        "IotaCash",
        "ZephyrCoin",
        "AlgoMint",
        "Solara",
        "ByteWave",
        "Safecoin",
    )
    private val koins = listOf(
        "BTC",
        "USDT",
        "ETH",
        "BNB",
        "XRP",
        "ADA",
        "DOGE",
        "LEO",
        "UNI",
        "XLM",
        "ICP",
        "TON",
        "FGC",
        "RPL",
        "EOS",
        "QNT",
        "APE",
        "GRT",
    )

    private val flag = Random().nextBoolean()

    private val icon = Image(SpriteManager.ListRegion.BITOK.regionList.shuffled().first())
    private val name = Label(names.shuffled().first(), Label.LabelStyle(FontTTFManager.Medium.font_26.font, GameColor.darkBlue))
    private val koin = Label(koins.shuffled().first(), Label.LabelStyle(FontTTFManager.Regular.font_22.font, GameColor.darkGray))
    private val cost = Label("$" + if (Random().nextBoolean()) maloe() else velik(), Label.LabelStyle(FontTTFManager.Medium.font_26.font, GameColor.darkBlue)).apply { setAlignment(Align.right) }
    private val perc = Label("${number(0, 30, 1)}, ${number(0, 90, 1)}%", Label.LabelStyle(FontTTFManager.Regular.font_22.font, (if (flag) GameColor.up else GameColor.dw))).apply { setAlignment(Align.right) }


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActors(icon, name, koin, cost, perc)
            icon.setBounds(0f, 4.5f, 68f, 68f)
            name.setBounds(98f, 38f, 273f, 38f)
            koin.setBounds(98f, 0f, 273f, 31f)
            cost.setBounds(413f, 38f, 225f, 38f)
            perc.setBounds(436f, 0f, 177f, 31f)

            val pad_ros = Image(if (flag) SpriteManager.GameRegion.ROS.region else SpriteManager.GameRegion.PAD.region)
            addActor(pad_ros)
            pad_ros.setBounds(613f, 0f, 30f, 30f)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun number(min: Int, max: Int, count: Int): Long {
        var numStr = ""
        repeat(count) { numStr += (min..max).shuffled().first() }
        return numStr.toLong()
    }

    private fun maloe() = number(0, 1000, 1).toString() + "." + number(0, 99, 1)
    private fun velik() = number(1, 50, 1).toString() + "." + number(100, 999, 1) + "," + number(0, 99, 1)
}