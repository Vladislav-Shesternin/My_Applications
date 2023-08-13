package com.karpenkov.budgetgid.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.karpenkov.budgetgid.game.actors.scroll.VerticalGroup
import com.karpenkov.budgetgid.game.manager.FontTTFManager
import com.karpenkov.budgetgid.game.manager.SpriteManager
import com.karpenkov.budgetgid.game.screens.Currency
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGroup

class CItem(
    val currency: Currency
) : AdvancedGroup() {

    private val flagImage    = Image(currency.flagRegion)
    private val valutaLabel  = Label(currency.valuta, Label.LabelStyle(FontTTFManager.RegularraFont.font_50.font, Color.WHITE))
    private val countryLabel = Label(currency.country, Label.LabelStyle(FontTTFManager.RegularraFont.font_50.font, Color.WHITE))

    var block: (Currency) -> Unit = {}
    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.LIST_PANEL.region))
            addActirs()
        }

        addListener(object : ClickListener() { override fun clicked(event: InputEvent?, x: Float, y: Float) { block(currency) } })
    }

    private fun addActirs() {
        addActors(flagImage, valutaLabel, countryLabel)
        flagImage.setBounds(20f, 26f, 175f, 134f)
        valutaLabel.setBounds(215f, 93f, 223f, 79f)
        countryLabel.setBounds(215f, 14f, 400f, 79f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}