package com.srata.financialguru.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.srata.financialguru.game.actors.VladikListBezShadowScrollPane
import com.srata.financialguru.game.manager.FontTTFManager
import com.srata.financialguru.game.manager.NavigationManager
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.utils.Layout
import com.srata.financialguru.game.utils.actor.animInvisible
import com.srata.financialguru.game.utils.actor.animSuspendVisible
import com.srata.financialguru.game.utils.actor.setBounds
import com.srata.financialguru.game.utils.actor.setOnClickListener
import com.srata.financialguru.game.utils.advanced.AdvancedGroup
import com.srata.financialguru.game.utils.advanced.AdvancedScreen
import com.srata.financialguru.game.utils.numStr
import com.srata.financialguru.game.utils.runGDX
import kotlinx.coroutines.launch

class MoyBalansScreen: AdvancedScreen() {

    private val golovaImg = Image(SpriteManager.GameRegion.MAY_BAL.region)
    private val balImg    = Image(SpriteManager.GameRegion.BAL.region)
    private val balLbl    = Label("$${numStr(10,99, 1)},000", Label.LabelStyle(FontTTFManager.GilMed.font_70.font, Color.WHITE))
    private val yorValImg = Image(SpriteManager.GameRegion.VASI_VALUTKI.region)
    private val vladListB = VladikListBezShadowScrollPane()



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addGolova()
                addBal()
                addVasVal()
                addVladList()
            }
            golovaImg.animSuspendVisible(0.41f)
            balImg.animSuspendVisible(0.41f)
            balLbl.animSuspendVisible(0.41f)
            yorValImg.animSuspendVisible(0.41f)
            vladListB.animSuspendVisible(0.41f)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addGolova() {
        addActor(golovaImg)
        golovaImg.apply {
            setBounds(Layout.golova)
            animInvisible()
        }
        val nazad = Actor()
        addActor(nazad)
        nazad.apply {
            setBounds(11f, 1335f, 84f, 84f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addBal() {
        addActor(balImg)
        balImg.apply {
            setBounds(31f, 1100f, 626f, 192f)
            animInvisible()
        }
        addActor(balLbl)
        balLbl.apply {
            setBounds(60f, 1128f, 438f, 86f)
            animInvisible()
        }
    }

    private fun AdvancedGroup.addVasVal() {
        addActor(yorValImg)
        yorValImg.apply {
            setBounds(32f, 1000f, 222f, 57f)
            animInvisible()
        }
    }

    private fun AdvancedGroup.addVladList() {
        addActor(vladListB)
        vladListB.apply {
            setBounds(31f, 40f, 626f, 938f)
            animInvisible()
        }
    }


}