package com.tutotoons.app.kpopsiescuteunicornpet.game.screens

import com.tutotoons.app.kpopsiescuteunicornpet.game.GDXGame
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.ATutorials
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel.APanelRules
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.launch

class RulesScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelRules = APanelRules(this).apply { color.a = 0f }
    private val tutorials  = ATutorials(this).apply { color.a = 0f }

    override fun show() {
        setBackBackground(game.assetsLoader.background_purple.region)
        super.show()
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
            if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
            animHidePanelSuspend(panelRules) { block.invoke() }
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
                if (game.isTutorialsUtil.isTutorials) addTutorials()
            }
            animShowPanelSuspend(panelRules)
            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    private fun AdvancedStage.addPanel() {
        addActor(panelRules)
        panelRules.setBounds(0f, -HEIGHT_UI, WIDTH_UI, HEIGHT_UI)

        panelRules.apply {
            backBtn.setOnClickListener {
                hideScreen {
                    if (screen.game.isTutorialsUtil.isTutorials) ATutorials.nextStep()
                    screen.game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addTutorials() {
        addAndFillActor(tutorials)

        if (game.isTutorialsUtil.isTutorials) {
            when (ATutorials.STEP) {
                ATutorials.Static.Step.RulesNext -> {
                    panelRules.children.onEach { it.disable() }
                    tutorials.rulesBackBlock = { panelRules.backBtn.enable() }
                }
                else -> {}
            }
        }
    }


}