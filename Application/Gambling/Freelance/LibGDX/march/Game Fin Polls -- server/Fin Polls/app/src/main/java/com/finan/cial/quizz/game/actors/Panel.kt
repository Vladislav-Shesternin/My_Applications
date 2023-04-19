package com.finan.cial.quizz.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.finan.cial.quizz.game.manager.FontTTFManager
import com.finan.cial.quizz.game.manager.SpriteManager
import com.finan.cial.quizz.game.utils.advanced.AdvancedGroup

class Panel: AdvancedGroup() {

    private val text = Label("", Label.LabelStyle(FontTTFManager.Jura.font_60.font, Color.BLACK))


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.PNL.region))
        addText()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addText() {
        val scrollPane = ScrollPane(text)
        addActor(scrollPane)
        scrollPane.setBounds(23f, 20f, 749f, 874f)
        text.setAlignment(Align.center)
        text.wrap = true
    }

    fun update(str: String) {
        text.addAction(Actions.sequence(
            Actions.fadeOut(0.3f),
            Actions.run { text.setText(str) },
            Actions.fadeIn(0.3f)
        ))
    }

}