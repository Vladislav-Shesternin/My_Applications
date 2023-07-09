package com.dankom.financialtracker.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.dankom.financialtracker.game.manager.FontTTFManager
import com.dankom.financialtracker.game.utils.GameColor
import com.dankom.financialtracker.game.utils.advanced.AdvancedGroup
import kotlin.random.Random

class ItemListGroup(
    val logo: TextureRegion,
    val nami: String,
): AdvancedGroup() {

    private val dateList = listOf("января","февраля","марта","апреля","мая","июня","июля","августа","сентября","октября")
    private val typeList = listOf("Передача","Подписка","Банковский перевод","Покупка","Приобретение","Аквизиция","Трансфер","Транзакция")

    private val pm get() = if(Random.nextBoolean()) "+" else "-"

    private val logoImg = Image(logo)
    private val nameLbl = Label(nami, Label.LabelStyle(FontTTFManager.PoppinsSemiBold.font_27.font, GameColor.blue))
    private val dateLbl = Label("${number(1, 31, 1)} ${dateList.shuffled().first()}", Label.LabelStyle(FontTTFManager.GilroySemibold.fontP_21.font, GameColor.blue))
    private val typeLbl = Label(typeList.shuffled().first(), Label.LabelStyle(FontTTFManager.GilroySemibold.fontP_21.font, GameColor.blue))
    private val costLbl = Label("$pm$${number(1,9,3)}.${number(0,9,2)}", Label.LabelStyle(FontTTFManager.PoppinsMedium.font_28.font, GameColor.light))
    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActors(logoImg,nameLbl,dateLbl,typeLbl,costLbl)
            logoImg.setBounds(0f, 0f, 85f, 80f)
            nameLbl.setBounds(108f, 39f, 293f, 41f)
            dateLbl.setBounds(108f, 1f, 123f, 32f)
            typeLbl.setBounds(249f, 1f, 313f, 32f)
            costLbl.setBounds(419f, 35f, 140f, 42f)

            typeLbl.setAlignment(Align.right)
            costLbl.setAlignment(Align.right)
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

}