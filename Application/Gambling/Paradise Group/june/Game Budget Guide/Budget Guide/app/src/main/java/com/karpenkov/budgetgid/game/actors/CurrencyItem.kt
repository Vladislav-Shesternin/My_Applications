package com.karpenkov.budgetgid.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.karpenkov.budgetgid.game.manager.FontTTFManager
import com.karpenkov.budgetgid.game.manager.NavigationManager
import com.karpenkov.budgetgid.game.manager.SpriteManager
import com.karpenkov.budgetgid.game.utils.actor.setOnClickListener
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGroup
import com.karpenkov.budgetgid.game.utils.runGDX
import kotlinx.coroutines.launch

class CurrencyItem() : AdvancedGroup() {

    private val flagImage  = Image()
    private val valutaText = Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_73.font, Color.WHITE))



    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActirs()
        }
    }

    private fun addActirs() {
        addActors(flagImage, valutaText)
        flagImage.apply {
            setBounds(34f, 93f, 175f, 134f)
        }
        valutaText.apply {
            setBounds(0f, 0f, 243f, 93f)
            setAlignment(Align.center)
        }


    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun setItem(flag: TextureRegion, valuta: String) {
        flagImage.drawable = TextureRegionDrawable(flag)
        valutaText.setText(valuta)
    }

}