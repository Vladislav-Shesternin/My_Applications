package com.tutotoons.app.kpopsiescuteunicornpet.game.screens

import com.tutotoons.app.kpopsiescuteunicornpet.game.GDXGame
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.ATutorials
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel.APanelBonus
import com.tutotoons.app.kpopsiescuteunicornpet.game.actors.panel.APanelMenu
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.actor.*
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedStage
import com.tutotoons.app.kpopsiescuteunicornpet.util.log
import kotlinx.coroutines.launch

class BonusScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelBonus = APanelBonus(this).apply { color.a = 0f }
    private val tutorials  = ATutorials(this).apply { color.a = 0f }

    // Field
    private val winBonusList = listOf(100, 500, 1000, 2500, 5000, 7575, 10_000)

    override fun show() {
        game.musicUtil.apply { music = roulette.apply {
            isLooping = true
            coff      = 1f
        } }

        setBackBackground(game.assetsLoader.background_purple.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
                if (game.isTutorialsUtil.isTutorials) addTutorials()
            }
            animShowPanelSuspend(panelBonus)
            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    override fun hideScreen(block: Block) {
        coroutine?.launch {
            if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
            animHidePanelSuspend(panelBonus) {
                game.musicUtil.apply { music = main.apply {
                    isLooping = true
                    coff      = 0.15f
                } }

                block.invoke()
            }
        }
    }

    // Actors ------------------------------------------------------------------------

    private fun AdvancedStage.addPanel() {
        addActor(panelBonus)
        panelBonus.setSize(width, height)
        panelBonus.y = -HEIGHT_UI

        panelBonus.apply {
            goBtn.setOnClickListener {
                if (game.isTutorialsUtil.isTutorials) {
                    ATutorials.nextStep()
                    tutorials.animHide(TIME_ANIM_SCREEN_ALPHA)
                }
                goBtn.disable()
                goCircles {
                    listOf(backCircleImg, frontCircleImg, goBtn).onEach { it.animHide(0.2f) }
                    val win = winBonusList.random()
                    winBonusLbl.label.setText("+${win.toFruitRecordFormat()}")
                    screen.game.recordUtil.apply { update(record + win) }
                    runGDX { fruitRecordLbl.setText(screen.game.recordUtil.record.toFruitRecordFormat()) }
                    animWin { hideScreen { game.navigationManager.back() } }
                }
            }
            backBtn.setOnClickListener { hideScreen { game.navigationManager.back() } }
        }

    }

    private fun AdvancedStage.addTutorials() {
        addAndFillActor(tutorials)

        if (game.isTutorialsUtil.isTutorials) {
            when (ATutorials.STEP) {
                ATutorials.Static.Step.BonusRecords -> {
                    panelBonus.apply {
                        children.onEach { it.disable() }
                        tutorials.bonusGoBlock = { goBtn.enable() }
                    }
                }
                else -> {}
            }
        }
    }

}