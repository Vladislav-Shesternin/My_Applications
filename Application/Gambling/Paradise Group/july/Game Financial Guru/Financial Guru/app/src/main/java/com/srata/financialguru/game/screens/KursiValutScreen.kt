package com.srata.financialguru.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.srata.financialguru.game.actors.ProstoScrollPane
import com.srata.financialguru.game.manager.NavigationManager
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.utils.Layout
import com.srata.financialguru.game.utils.actor.animInvisible
import com.srata.financialguru.game.utils.actor.animSuspendVisible
import com.srata.financialguru.game.utils.actor.setBounds
import com.srata.financialguru.game.utils.actor.setOnClickListener
import com.srata.financialguru.game.utils.advanced.AdvancedGroup
import com.srata.financialguru.game.utils.advanced.AdvancedScreen
import com.srata.financialguru.game.utils.runGDX
import kotlinx.coroutines.launch

class KursiValutScreen: AdvancedScreen() {

    private val golovaImg = Image(SpriteManager.GameRegion.KURSI_VALUTI.region)
    private val prostoPSP = ProstoScrollPane()



    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addGolova()
                addProstoPSP()
            }
            golovaImg.animSuspendVisible(0.41f)
            prostoPSP.animSuspendVisible(0.41f)
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

    private fun AdvancedGroup.addProstoPSP() {
        addActor(prostoPSP)
        prostoPSP.apply {
            setBounds(31f, 74f, 626f, 1257f)
            animInvisible()
        }
    }


}