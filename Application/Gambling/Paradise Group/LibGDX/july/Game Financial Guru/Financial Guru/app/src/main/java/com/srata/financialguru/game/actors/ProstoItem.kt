package com.srata.financialguru.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.srata.financialguru.game.manager.FontTTFManager
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.manager.SpriteManager.GameRegion.GUR
import com.srata.financialguru.game.manager.SpriteManager.GameRegion.RUD
import com.srata.financialguru.game.utils.GameColor
import com.srata.financialguru.game.utils.advanced.AdvancedGroup
import com.srata.financialguru.game.utils.numStr
import java.util.Random

class ProstoItem(
     logo: TextureRegion,
     titl: String,
): AdvancedGroup() {

    private val logoImg = Image(logo)
    private val titlLbl = Label(titl, Label.LabelStyle(FontTTFManager.GilMed.font_28.font, GameColor.blk))
    private val cudaLbl = Label("${numStr(1, 9, 2)},${numStr(0, 9, 2)}", Label.LabelStyle(FontTTFManager.GilMed.font_28.font, GameColor.tut))


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActors(logoImg, titlLbl, cudaLbl)
            logoImg.setBounds(24f, 31f, 76f, 76f)
            titlLbl.setBounds(122f, 52f, 209f, 34f)
            cudaLbl.setBounds(462f, 52f, 149f, 34f)

            cudaLbl.setAlignment(Align.right)
        }
    }

}