package com.doradogames.conflictnations.worldw.game.screens

import com.doradogames.conflictnations.worldw.game.GDXGame
import com.doradogames.conflictnations.worldw.game.actors.AFountainPanel
import com.doradogames.conflictnations.worldw.game.actors.panel.APanelRules
import com.doradogames.conflictnations.worldw.game.utils.*
import com.doradogames.conflictnations.worldw.game.utils.actor.animHidePanelSuspend
import com.doradogames.conflictnations.worldw.game.utils.actor.animHideSuspend
import com.doradogames.conflictnations.worldw.game.utils.actor.animShowPanelSuspend
import com.doradogames.conflictnations.worldw.game.utils.actor.animShowSuspend
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedScreen
import com.doradogames.conflictnations.worldw.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.launch

class RulesScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelRules    = APanelRules(this).apply { color.a = 0f }
    private val panelFountain = AFountainPanel(this)

    override fun show() {
        setBackBackground(game.assetsLoader.MAIN.region)
        super.show()
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
            runGDX { panelFountain.animHideParticles() }
            animHidePanelSuspend(panelRules) { block.invoke() }
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
                addAndFillActor(panelFountain)
            }
            animShowPanelSuspend(panelRules)
        }
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addAndFillActor(panelRules)
        panelRules.x = -WIDTH_UI

        panelRules.apply {
            backBtn.setOnClickListener { hideScreen { screen.game.navigationManager.back() } }
        }
    }

}