package com.fortunetiger.carnival.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.fortunetiger.carnival.game.utils.advanced.AdvancedGroup
import com.fortunetiger.carnival.game.utils.advanced.AdvancedScreen
import com.fortunetiger.carnival.game.utils.font.FontParameter

class ASharPanel(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(48)
    private val font          = screen.fontGeneratorBlackHanSans.generateFont(fontParameter)

    private val itemImg  = Image()
    private val countLbl = Label("0", Label.LabelStyle(font, Color.WHITE))

    var counter = 0
        private set

    override fun addActorsOnGroup() {
        addItemImage()
        addCountLabel()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addItemImage() {
        addActor(itemImg)
        itemImg.setBounds(0f, 6f, 34f, 51f)
    }

    private fun addCountLabel() {
        addActor(countLbl)
        countLbl.setBounds(52f, 0f, 70f, 60f)
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

    fun boom() {
        if (counter > 0) {
            counter--
            countLbl.setText(counter)
        }

    }

}