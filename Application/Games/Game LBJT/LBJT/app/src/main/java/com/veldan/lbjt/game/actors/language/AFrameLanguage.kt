package com.veldan.lbjt.game.actors.language

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.R
import com.veldan.lbjt.game.actors.label.AutoLanguageSpinningLabel
import com.veldan.lbjt.game.actors.label.SpinningLabel
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.LanguageUtil
import com.veldan.lbjt.game.utils.actor.setBounds
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import kotlinx.coroutines.launch

class AFrameLanguage(override val screen: AdvancedScreen): AdvancedGroup() {

    override var standartW = 314f

    private val themeUtil   by lazy { screen.game.themeUtil }
    private val fontTTFUtil by lazy { screen.game.fontTTFUtil }

    private val frameImg    by lazy { Image(themeUtil.trc.FRAME_LANGUAGE) }
    private val languageLbl by lazy { AutoLanguageSpinningLabel(screen, if (LanguageUtil.localeFlow.value.language == "uk") R.string.ukrainian else R.string.english, Label.LabelStyle(fontTTFUtil.fontInterExtraBold.font_50.font, GameColor.textGreen)) }

    override fun addActorsOnGroup() {
        addFrameImg()
        addLanguageLbl()

        asyncCollectLocaleAndUpdateResId()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addFrameImg() {
        addActor(frameImg)
        standardizer.scope { frameImg.setBounds(Vector2(0f, 0f).toStandart, Vector2(314f, 102f).toStandart) }
    }

    private fun addLanguageLbl() {
        addActor(languageLbl)
        languageLbl.apply {
            standardizer.scope { setBounds(Vector2(10f, 10f).toStandart, Vector2(294f, 82f).toStandart) }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCollectLocaleAndUpdateResId() {
        coroutine?.launch {
            LanguageUtil.localeFlow.collect {
                when(it.language) {
                    "uk" -> R.string.ukrainian
                    else -> R.string.english
                }.also { resId -> languageLbl.setResId(resId) }
            }
        }
    }

}