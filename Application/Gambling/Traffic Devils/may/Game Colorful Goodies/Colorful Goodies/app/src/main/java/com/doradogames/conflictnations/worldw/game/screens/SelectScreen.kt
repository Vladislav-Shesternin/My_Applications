package com.doradogames.conflictnations.worldw.game.screens

import com.doradogames.conflictnations.worldw.game.GDXGame
import com.doradogames.conflictnations.worldw.game.actors.AFountainPanel
import com.doradogames.conflictnations.worldw.game.actors.panel.APanelSelect
import com.doradogames.conflictnations.worldw.game.utils.*
import com.doradogames.conflictnations.worldw.game.utils.actor.animHidePanelSuspend
import com.doradogames.conflictnations.worldw.game.utils.actor.animShowPanelSuspend
import com.doradogames.conflictnations.worldw.game.utils.actor.setOnClickListener
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedScreen
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.launch

class SelectScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelSelect   = APanelSelect(this).apply { color.a = 0f }
    private val panelFountain = AFountainPanel(this)

    override fun show() {
        setBackBackground(game.assetsLoader.MAIN.region)
        super.show()
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
            runGDX { panelFountain.animHideParticles() }
            animHidePanelSuspend(panelSelect) { block.invoke() }
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
                addAndFillActor(panelFountain)
            }
            animShowPanelSuspend(panelSelect)
        }
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addAndFillActor(panelSelect)
        panelSelect.x = -WIDTH_UI

        panelSelect.apply {
            backBtn.setOnClickListener { hideScreen { screen.game.navigationManager.back() } }
            goImg.setOnClickListener(game.soundUtil) { hideScreen { screen.game.navigationManager.navigate(GameScreen::class.java.name, SelectScreen::class.java.name) } }
        }
    }

}