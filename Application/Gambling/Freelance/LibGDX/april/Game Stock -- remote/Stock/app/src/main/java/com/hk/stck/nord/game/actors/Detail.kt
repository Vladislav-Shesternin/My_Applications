package com.hk.stck.nord.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.hk.stck.nord.game.actors.button.AButton
import com.hk.stck.nord.game.actors.button.AButtonStyle
import com.hk.stck.nord.game.actors.label.spinning.SpinningLabel
import com.hk.stck.nord.game.manager.FontTTFManager
import com.hk.stck.nord.game.manager.SpriteManager
import com.hk.stck.nord.game.utils.actor.disable
import com.hk.stck.nord.game.utils.advanced.AdvancedGroup
import com.hk.stck.nord.game.utils.runGDX
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class Detail: AdvancedGroup() {

    private val title         = SpinningLabel("Loading", Label.LabelStyle(FontTTFManager.AlegreyaSansSC_ExtraBold.font_65.font, Color.WHITE))
    private val currency      = SpinningLabel("Loading", Label.LabelStyle(FontTTFManager.AlegreyaSansSC_Bold.font_60.font, Color.WHITE))
    private val displaySymbol = SpinningLabel("Loading", Label.LabelStyle(FontTTFManager.AlegreyaSansSC_Bold.font_60.font, Color.WHITE))
    private val figi          = SpinningLabel("Loading", Label.LabelStyle(FontTTFManager.AlegreyaSansSC_Bold.font_60.font, Color.WHITE))
    private val mic           = SpinningLabel("Loading", Label.LabelStyle(FontTTFManager.AlegreyaSansSC_Bold.font_60.font, Color.WHITE))
    private val symbol        = SpinningLabel("Loading", Label.LabelStyle(FontTTFManager.AlegreyaSansSC_Bold.font_60.font, Color.WHITE))
    private val type          = SpinningLabel("Loading", Label.LabelStyle(FontTTFManager.AlegreyaSansSC_Bold.font_60.font, Color.WHITE))


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        disable()
        addAndFillActor(Image(SpriteManager.GameRegion.STOCK.region))
        addAcatars()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------


    private fun addAcatars() {
        addActors(title, currency, displaySymbol, figi, mic, symbol, type)
        title.setBounds(17f, 797f, 506f, 78f)
        currency.setBounds(267f, 662f, 260f, 72f)
        displaySymbol.setBounds(267f, 548f, 260f, 72f)
        figi.setBounds(267f, 434f, 260f, 72f)
        mic.setBounds(267f, 320f, 260f, 72f)
        symbol.setBounds(267f, 206f, 260f, 72f)
        type.setBounds(267f, 92f, 260f, 72f)
    }

    fun update(ptitle: String? = null, pcurrency: String? = null, pdisplaySymbol: String? = null, pfigi: String? = null, pmic: String? = null, psymbol: String? = null, ptype: String? = null) {
        title.setText(ptitle ?: "Loading")
        currency.setText(pcurrency ?: "Loading")
        displaySymbol.setText(pdisplaySymbol ?: "Loading")
        figi.setText(pfigi ?: "Loading")
        mic.setText(pmic ?: "Loading")
        symbol.setText(psymbol ?: "Loading")
        type.setText(ptype ?: "Loading")
    }

}