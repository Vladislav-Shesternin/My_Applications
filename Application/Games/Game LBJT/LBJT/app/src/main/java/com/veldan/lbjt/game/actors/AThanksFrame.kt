package com.veldan.lbjt.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.R
import com.veldan.lbjt.game.actors.label.ASpinningLabel
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.font.FontParameter
import com.veldan.lbjt.game.utils.font.FontParameter.CharType

class AThanksFrame(override val screen: AdvancedScreen): AdvancedGroup() {

    override var standartW = 271f

    private val themeUtil   = screen.game.themeUtil

    private val parameter = FontParameter()

    private val frameImg      = Image(themeUtil.assets.THANKS_FRAME)
    private val giveThanksLbl = ASpinningLabel(screen, screen.game.languageUtil.getStringResource(R.string.give_thanks), Label.LabelStyle(screen.fontGeneratorInter_ExtraBold.generateFont(parameter.setCharacters(CharType.LATIN_CYRILLIC).setSize(35)), GameColor.textGreen))

    override fun addActorsOnGroup() {
        addFrameImg()
        addGiveThanksLbl()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addFrameImg() {
        addActor(frameImg)
        frameImg.setBoundsStandart(0f,0f,271f,72f)
    }

    private fun addGiveThanksLbl() {
        addActor(giveThanksLbl)
        giveThanksLbl.setBoundsStandart(10f,5f,251f,62f)
    }

}