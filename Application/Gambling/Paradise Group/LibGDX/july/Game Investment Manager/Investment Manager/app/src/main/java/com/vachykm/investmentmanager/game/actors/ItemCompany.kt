package com.vachykm.investmentmanager.game.actors

 import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
 import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
 import com.badlogic.gdx.utils.Align
import com.vachykm.investmentmanager.game.actors.label.spinning.SpinningLabel
import com.vachykm.investmentmanager.game.manager.FontTTFManager
 import com.vachykm.investmentmanager.game.manager.SpriteManager.GameRegion.GREENKA
import com.vachykm.investmentmanager.game.manager.SpriteManager.GameRegion.REDKA
 import com.vachykm.investmentmanager.game.utils.GameColor
 import com.vachykm.investmentmanager.game.utils.actor.animHide
 import com.vachykm.investmentmanager.game.utils.actor.animShow
 import com.vachykm.investmentmanager.game.utils.advanced.AdvancedGroup
 import com.vachykm.investmentmanager.game.utils.numStr
 import java.util.Random

class ItemCompany(
    val logo: TextureRegion,
    val titl: String,
): AdvancedGroup() {

    private val randomPM = Random().nextBoolean()

    private val mORp = if (randomPM) "+%" else "-%"

    private val logoImg = Image(logo)
    private val titlLbl = Label(titl, Label.LabelStyle(FontTTFManager.GilSemBold.font_25.font, Color.BLACK))
    private val plusmin = Image(if (randomPM) GREENKA.region else REDKA.region)
    private val cousLbl = Label("$${numStr(50, 999, 1)}.${numStr(0,9,2)}", Label.LabelStyle(FontTTFManager.GilSemBold.font_28.font, Color.BLACK))
    private val cudaLbl = Label("$mORp${numStr(0, 20, 1)}.${numStr(0,99,1)}", Label.LabelStyle(FontTTFManager.GilMed.font_25.font, if (randomPM) GameColor.green else GameColor.red))


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActors(logoImg, titlLbl, plusmin, cousLbl, cudaLbl)
            logoImg.setBounds(22f, 28f, 67f, 67f)
            titlLbl.setBounds(109f, 40f, 207f, 43f)
            plusmin.setBounds(322f, 20f, 98f, 75f)
            cousLbl.setBounds(446f, 68f, 137f, 35f)
            cudaLbl.setBounds(461f, 27f, 122f, 30f)

            cousLbl.setAlignment(Align.right)
            cudaLbl.setAlignment(Align.right)
        }
    }

    fun updateCousCuda() {
        cousLbl.clearActions()
        cudaLbl.clearActions()

        cousLbl.animHide(0.3f)
        cudaLbl.animHide(0.4f) {
            cousLbl.setText("$${numStr(50, 999, 1)}.${numStr(0, 9, 2)}")
            cudaLbl.setText("$mORp${numStr(0, 20, 1)}.${numStr(0,99,1)}")

            cousLbl.animShow(0.3f)
            cudaLbl.animShow(0.4f)
        }
    }

}