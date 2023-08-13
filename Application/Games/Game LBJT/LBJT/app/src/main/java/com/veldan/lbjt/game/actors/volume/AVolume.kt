package com.veldan.lbjt.game.actors.volume

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.lbjt.R
import com.veldan.lbjt.game.actors.label.AutoLanguageSpinningLabel
import com.veldan.lbjt.game.actors.label.SpinningLabel
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.actor.setBounds
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.runGDX

class AVolume(
    override val screen: AdvancedScreen,
    val type: Type
): AdvancedGroup() {

    override var standartW = 149f

    private val themeUtil   by lazy { screen.game.themeUtil }
    private val fontTTFUtil by lazy { screen.game.fontTTFUtil }

    private val nameLbl       by lazy { AutoLanguageSpinningLabel(screen, type.nameResId, Label.LabelStyle(fontTTFUtil.fontInterMedium.font_30.font, GameColor.textGreen)) }
    private val percentLbl    = Label("0%", Label.LabelStyle(fontTTFUtil.fontInterBlack.font_25.font, GameColor.textGreen))
    private val backgroundImg by lazy { Image(getBackgroundRegionByType(type)) }

    override fun addActorsOnGroup() {
        addBackgroundImg()
        addNameLbl()
        addPercentLbl()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addBackgroundImg() {
        addActor(backgroundImg)
        standardizer.scope { backgroundImg.setBounds(Vector2(0f, 0f).toStandart, Vector2(149f, 102f).toStandart) }
    }

    private fun addNameLbl() {
        addActor(nameLbl)
        nameLbl.apply {
            standardizer.scope { setBounds(Vector2(10f, 51f).toStandart, Vector2(129f, 41f).toStandart) }
        }
    }

    private fun addPercentLbl() {
        addActor(percentLbl)
        percentLbl.apply {
            standardizer.scope { setBounds(Vector2(10f, 10f).toStandart, Vector2(129f, 41f).toStandart) }
            setAlignment(Align.center)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun updatePercent(percent: Int) {
        runGDX { percentLbl.setText("$percent%") }
    }

    fun getBackgroundRegionByType(type: Type) = when(type) {
        Type.QUIET -> themeUtil.trc.VOLUME_QUIET
        Type.LOUDER -> themeUtil.trc.VOLUME_LOUDER
    }

    enum class Type(val nameResId: Int, ) {
        QUIET(R.string.quiet), LOUDER(R.string.louder),
    }

}