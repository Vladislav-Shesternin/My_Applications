package com.tigerfortune.jogo.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.tigerfortune.jogo.game.utils.advanced.AdvancedGroup
import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen
import com.tigerfortune.jogo.game.utils.font.FontParameter

class AItemPanel(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(29)
    private val font          = screen.fontGeneratorAngry.generateFont(fontParameter)

    private val itemImg  = Image()
    private val countLbl = Label("0", Label.LabelStyle(font, Color.WHITE))

    var counter = 0
        private set

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.panel))
        addItemImage()
        addCountLabel()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addItemImage() {
        addActor(itemImg)
        itemImg.setBounds(28f, 62f, 60f, 60f)
    }

    private fun addCountLabel() {
        addActor(countLbl)
        countLbl.setBounds(10f, 15f, 97f, 22f)
        countLbl.setAlignment(Align.center)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(count: Int, region: TextureRegion) {
        counter = count
        countLbl.setText(count)
        itemImg.drawable = TextureRegionDrawable(region)
    }

    fun caught() {
        if (counter > 0) {
            counter--
            countLbl.setText(counter)
        }

    }

}