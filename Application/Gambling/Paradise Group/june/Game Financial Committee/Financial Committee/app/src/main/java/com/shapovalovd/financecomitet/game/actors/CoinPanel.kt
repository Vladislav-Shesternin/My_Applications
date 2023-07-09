package com.shapovalovd.financecomitet.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.shapovalovd.financecomitet.game.manager.FontTTFManager
import com.shapovalovd.financecomitet.game.manager.SpriteManager
import com.shapovalovd.financecomitet.game.utils.GameColor
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedGroup
import java.util.Random

class CoinPanel(val iconR: TextureRegion, val namek: String): AdvancedGroup() {

    private val flag = Random().nextBoolean()

    private val icon = Image(iconR)
    private val name = Label("$namek/USD", Label.LabelStyle(FontTTFManager.PopSemiBold.font_27.font, Color.WHITE))
    private val date = Label("${day()}-${mon()}-23", Label.LabelStyle(FontTTFManager.PopMedium.font_22.font, GameColor.pur))
    private val pers = Label((if (flag) "-" else "+") + "0." + numbers(6), Label.LabelStyle(FontTTFManager.PopMedium.font_20.font, (if (flag) GameColor.rud else GameColor.gru))).apply { setAlignment(Align.right) }
    private val cost = Label("$${numbers((3..6).shuffled().first())}", Label.LabelStyle(FontTTFManager.PopMedium.font_20.font, Color.WHITE)).apply { setAlignment(Align.right) }


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.CRYPTO_ITEM.region))
            addActors(icon, name, date, pers, cost)
            icon.setBounds(34f, 43f, 60f, 60f)
            name.setBounds(111f, 74f, 128f, 33f)
            date.setBounds(111f, 39f, 128f, 33f)
            pers.setBounds(468f, 79f, 111f, 31f)
            cost.setBounds(484f, 40f, 93f, 31f)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun r() = (0..9).shuffled().first()

    private fun day() = (1..31).shuffled().first()
    private fun mon() = (1..12).shuffled().first()

    private fun numbers(countr: Int): String {
        var str = ""
        repeat(countr) {
            str += r().toString()
        }
        return str
    }

}