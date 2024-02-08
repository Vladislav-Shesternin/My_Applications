package com.socall.qzz.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.socall.qzz.game.manager.FontTTFManager
import com.socall.qzz.game.manager.SpriteManager
import com.socall.qzz.game.utils.advanced.AdvancedGroup

class Panel: AdvancedGroup() {

    val text = Label("", Label.LabelStyle(FontTTFManager.Amatic.font_50.font, Color.WHITE))


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.PANEL.region))
        addText()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addText() {
        val scrollPane = ScrollPane(text)
        addActor(scrollPane)
        scrollPane.setBounds(21f, 14f, 1025f, 424f)
        text.setAlignment(Align.center)
    }

}