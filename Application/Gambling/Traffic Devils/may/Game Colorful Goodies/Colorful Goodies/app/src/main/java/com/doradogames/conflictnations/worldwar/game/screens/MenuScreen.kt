package com.doradogames.conflictnations.worldwar.game.screens

import com.doradogames.conflictnations.worldwar.game.GDXGame
import com.doradogames.conflictnations.worldwar.game.actors.AFountainPanel
import com.doradogames.conflictnations.worldwar.game.actors.panel.APanelMenu
import com.doradogames.conflictnations.worldwar.game.utils.*
import com.doradogames.conflictnations.worldwar.game.utils.actor.*
import com.doradogames.conflictnations.worldwar.game.utils.advanced.AdvancedScreen
import com.doradogames.conflictnations.worldwar.game.utils.advanced.AdvancedStage
import com.doradogames.conflictnations.worldwar.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MenuScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelMenu     = APanelMenu(this).apply { color.a = 0f }
    private val panelFountain = AFountainPanel(this)

    override fun show() {
        setBackBackground(game.assetsLoader.MAIN.region)
        super.show()
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
            runGDX { panelFountain.animHideParticles() }
            animHidePanelSuspend(panelMenu) { block.invoke() }
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
                addAndFillActor(panelFountain)
            }
            animShowPanelSuspend(panelMenu)
        }
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addAndFillActor(panelMenu)
        panelMenu.x = -WIDTH_UI

        panelMenu.apply {
            rulesBtn.setOnClickListener { hideScreen { game.navigationManager.navigate(RulesScreen::class.java.name, MenuScreen::class.java.name) } }
            playBtn.setOnClickListener { hideScreen { game.navigationManager.navigate(SelectScreen::class.java.name, MenuScreen::class.java.name) } }
            settBtn.setOnClickListener { hideScreen { game.navigationManager.navigate(SettingsScreen::class.java.name, MenuScreen::class.java.name) } }
            exitBtn.setOnClickListener { hideScreen { game.navigationManager.exit() } }
        }

    }

}