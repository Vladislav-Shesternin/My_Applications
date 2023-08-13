package com.tsabekaa.finhelper.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.tsabekaa.finhelper.game.manager.FontTTFManager
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.utils.GameColor
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGroup
import java.util.Random

class ABank(img: TextureRegion, name: String): AdvancedGroup() {

    private val image = Image(img)
    private val label = Label(name, Label.LabelStyle(FontTTFManager.BoldFont.font_50.font, GameColor.blue))


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addAndFillActor(Image(SpriteManager.GameRegion.ITEM.region))
        addImage()
        addText()
        addOther()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addImage() {
        addActor(image)
        image.setBounds(96f, 259f, 324f, 324f)
    }

    private fun AdvancedGroup.addText() {
        addActor(label)
        label.setBounds(31f, 182f, 454f, 89f)
        label.setAlignment(Align.center)
    }

    private fun AdvancedGroup.addOther() {

        val ud   = Image(if (Random().nextBoolean()) SpriteManager.GameRegion.UP.region else SpriteManager.GameRegion.DOWN.region)
        val cost = Label((90..500).shuffled().first().toString() + "." + (1..99).shuffled().first() + "$", Label.LabelStyle(FontTTFManager.BoldFont.font_40.font, Color.WHITE))
        val all  = Label(
            (1..20).shuffled().first().toString() + "." + (1..99).shuffled().first() + "$" + " " +
                    "(" + (1..20).shuffled().first().toString() + "." + (1..99).shuffled().first() + "%" + ")"
            , Label.LabelStyle(FontTTFManager.RegularFont.font_40.font, Color.WHITE))

        addActors(ud, cost, all)
        ud.setBounds(79f, 53f, 36f, 116f)
        cost.apply {
            setBounds(159f, 119f, 326f, 65f)
            setAlignment(Align.center)
        }
        all.apply {
            setBounds(159f, 53f, 326f, 65f)
            setAlignment(Align.center)
        }
    }

}