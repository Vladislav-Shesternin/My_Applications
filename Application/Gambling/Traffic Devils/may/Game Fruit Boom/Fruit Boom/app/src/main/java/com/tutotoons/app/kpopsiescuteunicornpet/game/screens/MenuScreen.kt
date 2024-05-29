package com.tutotoons.app.kpopsiescuteunicornpet.game.screens

import com.tutotoons.app.kpopsiescuteunicornpet.game.GDXGame
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.ATutorials
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel.APanelMenu
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedStage
import com.tutotoons.app.kpopsiescuteunicornpet.util.log
import kotlinx.coroutines.launch

class MenuScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelMenu = APanelMenu(this).apply { color.a = 0f }
    private val tutorials = ATutorials(this).apply { color.a = 0f }

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
            animShowPanelSuspend(panelMenu)
            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
            if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
            animHidePanelSuspend(panelMenu) { block.invoke() }
        }
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActor(panelMenu)
        panelMenu.setBounds(0f, -HEIGHT_UI, WIDTH_UI, HEIGHT_UI)

        val screenList = listOf(
            LevelsScreen::class.java.name,
            RulesScreen::class.java.name,
            SettingsScreen::class.java.name,
        )

        panelMenu.apply {
            btnList.onEachIndexed { index, btn ->
                btn.setOnClickListener(game.soundUtil) {
                    hideScreen {
                        if (game.isTutorialsUtil.isTutorials) ATutorials.nextStep()
                        game.navigationManager.navigate(screenList[index], MenuScreen::class.java.name)
                    }
                }
            }
            bonusImg.setOnClickListener(game.soundUtil) {
                APanelMenu.isBonus = false
                bonusImg.disable()
                hideScreen {
                    if (game.isTutorialsUtil.isTutorials) ATutorials.nextStep()
                    game.navigationManager.navigate(BonusScreen::class.java.name, MenuScreen::class.java.name)
                }
            }
            quitBtn.setOnClickListener {
                hideScreen { game.navigationManager.exit() }
            }
            superGameBtn.setOnClickListener {
                APanelMenu.isSuperGame = false
                superGameBtn.disable()
                hideScreen { game.navigationManager.navigate(SuperGameScreen::class.java.name, MenuScreen::class.java.name) }
            }
            bigGameBtn.setOnClickListener {
                APanelMenu.isBigGame = false
                bigGameBtn.disable()
                hideScreen { game.navigationManager.navigate(BigGameScreen::class.java.name, MenuScreen::class.java.name) }
            }
        }

    }

    private fun AdvancedStage.addTutorials() {
        addActor(tutorials)
        tutorials.setSize(WIDTH_UI, HEIGHT_UI)

        if (game.isTutorialsUtil.isTutorials) {
            when (ATutorials.STEP) {
                ATutorials.Static.Step.MenuRules -> {
                    panelMenu.children.onEach { it.disable() }
                    panelMenu.btnList[1].enable()
                }
                ATutorials.Static.Step.MenuSettings -> {
                    panelMenu.children.onEach { it.disable() }
                    panelMenu.btnList[2].enable()
                }
                ATutorials.Static.Step.MenuGames -> {
                    panelMenu.children.onEach { it.disable() }
                    panelMenu.btnList[0].enable()
                }
                ATutorials.Static.Step.MenuFruitRecord -> {
                    panelMenu.apply {
                        children.onEach { it.disable() }
                        tutorials.menuBonusBlock = { panelMenu.bonusImg.enable() }
                    }
                }
                else -> {}
            }
        }
    }

}