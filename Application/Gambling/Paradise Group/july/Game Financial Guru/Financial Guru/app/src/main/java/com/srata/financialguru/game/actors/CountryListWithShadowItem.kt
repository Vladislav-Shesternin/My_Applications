package com.srata.financialguru.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.srata.financialguru.game.manager.FontTTFManager
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.manager.SpriteManager.GameRegion.GUR
import com.srata.financialguru.game.manager.SpriteManager.GameRegion.RUD
import com.srata.financialguru.game.utils.GameColor
import com.srata.financialguru.game.utils.advanced.AdvancedGroup
import com.srata.financialguru.game.utils.numStr
import java.util.Random

class CountryListWithShadowItem(
     logo: TextureRegion,
     titl: String,
): AdvancedGroup() {

    private val randomPM = Random().nextBoolean()

    private val mORp = if (randomPM) "+%" else "-%"

    private val logoImg = Image(logo)
    private val titlLbl = Label(titl, Label.LabelStyle(FontTTFManager.GilMed.font_28.font, GameColor.blk))
    private val plusmin = Image(if (randomPM) GUR.region else RUD.region)
    private val cudaLbl = Label("$mORp${numStr(0, 9, 1)}.${numStr(0,9,2)}", Label.LabelStyle(FontTTFManager.GilMed.font_28.font, if (randomPM) GameColor.grn else GameColor.red))


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.WITH_TEN.region))
            addActors(logoImg, titlLbl, plusmin, cudaLbl)
            logoImg.setBounds(28f, 41f, 76f, 76f)
            titlLbl.setBounds(126f, 61f, 145f, 34f)
            plusmin.setBounds(317f, 38f, 110f, 84f)
            cudaLbl.setBounds(461f, 62f, 127f, 34f)

        }
    }

}