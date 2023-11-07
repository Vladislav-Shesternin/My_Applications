package com.veldan.lbjt.game.actors.scroll.tutorial.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.github.tommyettinger.textra.Font
import com.github.tommyettinger.textra.TypingLabel
import com.veldan.lbjt.game.actors.scroll.VerticalGroup
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.advanced.AdvancedGroup
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.util.log

class ALongQuote_TypingLabel(
    override val screen : AdvancedScreen,
    textQuote: String,
    font    : Font,
): AdvancedGroup() {

    private val lineImg    = Image(screen.drawerUtil.getRegion(Color.BLACK))
    private val quoteLbl   = TypingLabel(textQuote, font)

    override fun addActorsOnGroup() {
        addQuoteLbl()
        addLineImg()
    }

    private fun addQuoteLbl() {
        addActor(quoteLbl)
        quoteLbl.apply {
            x      = 14f
            wrap   = true
            width  = this@ALongQuote_TypingLabel.width - 14
            height = prefHeight
        }

        height = quoteLbl.height
    }

    private fun addLineImg() {
        addActor(lineImg)
        lineImg.setBounds(0f, 0f, 6f, quoteLbl.height)
    }

}