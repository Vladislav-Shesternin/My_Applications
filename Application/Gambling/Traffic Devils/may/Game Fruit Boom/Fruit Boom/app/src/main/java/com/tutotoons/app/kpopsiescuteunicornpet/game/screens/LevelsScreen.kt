package com.tutotoons.app.kpopsiescuteunicornpet.game.screens

import com.tutotoons.app.kpopsiescuteunicornpet.game.GDXGame
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.ATutorials
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel.APanelLevels
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.launch

class LevelsScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelLevels = APanelLevels(this).apply { color.a = 0f }
    private val tutorials   = ATutorials(this).apply { color.a = 0f }

    override fun show() {
        setBackBackground(game.assetsLoader.background_purple.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
                if (game.isTutorialsUtil.isTutorials) addTutorials()
            }
            animShowPanelSuspend(panelLevels)
            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
             if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
            animHidePanelSuspend(panelLevels) { block.invoke() }
        }
    }

    private fun AdvancedStage.addPanel() {
        addAndFillActor(panelLevels)
        panelLevels.y = -HEIGHT_UI

        panelLevels.apply {
            backBtn.setOnClickListener {
                hideScreen { screen.game.navigationManager.back() }
            }
            btnList.take(screen.game.levelUtil.level).onEach { it.unlock() }
            btnList.onEach { btn ->
                btn.setOnClickListener {
                    hideScreen {
                        if (screen.game.isTutorialsUtil.isTutorials) ATutorials.nextStep()
                        screen.game.navigationManager.navigate(GameScreen::class.java.name, LevelsScreen::class.java.name)
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addTutorials() {
        addAndFillActor(tutorials)

        if (game.isTutorialsUtil.isTutorials) {
            when (ATutorials.STEP) {
                ATutorials.Static.Step.MenuGames -> {
                    panelLevels.children.onEach { it.disable() }
                    tutorials.levelsUnlockedBlock = { panelLevels.btnList[0].enable() }
                }
                else -> {}
            }
        }
    }

}