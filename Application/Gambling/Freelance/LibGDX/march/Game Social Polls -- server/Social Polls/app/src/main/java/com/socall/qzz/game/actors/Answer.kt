package com.socall.qzz.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.socall.qzz.game.actors.label.spinning.SpinningLabel
import com.socall.qzz.game.manager.FontTTFManager
import com.socall.qzz.game.manager.SpriteManager
import com.socall.qzz.game.utils.actor.enable
import com.socall.qzz.game.utils.advanced.AdvancedGroup

class Answer: AdvancedGroup() {

    private val image = Image(SpriteManager.GameRegion.A_DEF.region)
    val text = SpinningLabel("", Label.LabelStyle(FontTTFManager.Amatic.font_40.font, Color.WHITE))

    var isWin = false

    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(image)
        addText()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addText() {
        addActor(text)
        text.setBounds(14f, 16f, 328f, 63f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    fun def() {
        isWin = false
        enable()
        image.drawable = TextureRegionDrawable(SpriteManager.GameRegion.A_DEF.region)
    }

    fun win() {
        image.drawable = TextureRegionDrawable(SpriteManager.GameRegion.A_WIN.region)
    }

    fun fail() {
        image.drawable = TextureRegionDrawable(SpriteManager.GameRegion.A_FAIL.region)
    }

}