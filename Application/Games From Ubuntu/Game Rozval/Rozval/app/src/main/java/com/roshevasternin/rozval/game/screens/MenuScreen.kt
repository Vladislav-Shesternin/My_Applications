package com.roshevasternin.rozval.game.screens

import com.roshevasternin.rozval.game.GDXGame
import com.roshevasternin.rozval.game.actors.panel.APanelMenu
import com.roshevasternin.rozval.game.utils.Block
import com.roshevasternin.rozval.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.roshevasternin.rozval.game.utils.actor.*
import com.roshevasternin.rozval.game.utils.advanced.AdvancedScreen
import com.roshevasternin.rozval.game.utils.advanced.AdvancedStage
import com.roshevasternin.rozval.game.utils.runGDX
import kotlinx.coroutines.launch

class MenuScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelMenu = APanelMenu(this).apply { color.a = 0f }
//    private val tutorials = ATutorials(this).apply { color.a = 0f }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
//                if (game.isTutorialsUtil.isTutorials) addTutorials()
            }
            panelMenu.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
//            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
//            if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
            panelMenu.animHideSuspend(TIME_ANIM_SCREEN_ALPHA) { block.invoke() }
        }
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addAndFillActor(panelMenu)

        panelMenu.apply {
            mainPanel.setOnClickListener {
                hideScreen { game.navigationManager.exit() }
            }
        }

    }

//    private fun AdvancedStage.addTutorials() {
//        addActor(tutorials)
//        tutorials.setSize(WIDTH_UI, HEIGHT_UI)
//
//        if (game.isTutorialsUtil.isTutorials) {
//            when (ATutorials.STEP) {
//                ATutorials.Static.Step.MenuRules -> {
//                    panelMenu.children.onEach { it.disable() }
//                    panelMenu.btnList[1].enable()
//                }
//                ATutorials.Static.Step.MenuSettings -> {
//                    panelMenu.children.onEach { it.disable() }
//                    panelMenu.btnList[2].enable()
//                }
//                ATutorials.Static.Step.MenuGames -> {
//                    panelMenu.children.onEach { it.disable() }
//                    panelMenu.btnList[0].enable()
//                }
//                ATutorials.Static.Step.MenuFruitRecord -> {
//                    panelMenu.apply {
//                        children.onEach { it.disable() }
//                        tutorials.menuBonusBlock = { panelMenu.bonusImg.enable() }
//                    }
//                }
//                else -> {}
//            }
//        }
//    }

}