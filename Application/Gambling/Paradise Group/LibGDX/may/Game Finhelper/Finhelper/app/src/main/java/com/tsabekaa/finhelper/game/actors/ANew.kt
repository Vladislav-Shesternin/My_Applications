package com.tsabekaa.finhelper.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.tsabekaa.finhelper.game.manager.FontTTFManager
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGroup

class ANew(img: TextureRegion, text: String): AdvancedGroup() {

    private val newImage = Image(img)
    private val newLabel = Label(text, Label.LabelStyle(FontTTFManager.BoldFont.font_20.font, Color.WHITE))


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.NEWS.region))
        addImage()
        addText()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addImage() {
        addActor(newImage)
        newImage.setBounds(456f, 12f, 111f, 111f)
    }

    private fun AdvancedGroup.addText() {
        addActor(newLabel)
        newLabel.setBounds(8f, 12f, 438f, 111f)
        newLabel.wrap = true
    }

}