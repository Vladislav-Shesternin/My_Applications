package com.doradogames.conflictnations.worldw.game.screens

import com.doradogames.conflictnations.worldw.game.GDXGame
import com.doradogames.conflictnations.worldw.game.actors.AFountainPanel
import com.doradogames.conflictnations.worldw.game.actors.panel.APanelSettings
import com.doradogames.conflictnations.worldw.game.utils.*
import com.doradogames.conflictnations.worldw.game.utils.actor.animHidePanelSuspend
import com.doradogames.conflictnations.worldw.game.utils.actor.animShowPanelSuspend
import com.doradogames.conflictnations.worldw.game.utils.actor.enable
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedScreen
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.launch

class SettingsScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelSettings = APanelSettings(this).apply { color.a = 0f }
    private val panelFountain = AFountainPanel(this)

    override fun show() {
        setBackBackground(game.assetsLoader.MAIN.region)
        super.show()
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
            runGDX { panelFountain.animHideParticles() }
            animHidePanelSuspend(panelSettings) { block.invoke() }
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
                addAndFillActor(panelFountain)
            }
            animShowPanelSuspend(panelSettings)
        }
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addAndFillActor(panelSettings)
        panelSettings.x = -WIDTH_UI

        panelSettings.apply {
            backBtn.setOnClickListener { hideScreen { screen.game.navigationManager.back() } }
        }
    }

}