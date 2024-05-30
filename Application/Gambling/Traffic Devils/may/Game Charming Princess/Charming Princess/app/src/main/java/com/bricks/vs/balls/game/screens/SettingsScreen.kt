package com.bricks.vs.balls.game.screens

import com.bricks.vs.balls.game.GDXGame
import com.bricks.vs.balls.game.actors.ATutorials
import com.bricks.vs.balls.game.actors.panel.APanelSettings
import com.bricks.vs.balls.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.bricks.vs.balls.game.utils.actor.*
import com.bricks.vs.balls.game.utils.advanced.AdvancedScreen
import com.bricks.vs.balls.game.utils.advanced.AdvancedStage
import com.bricks.vs.balls.game.utils.region
import com.bricks.vs.balls.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelSettings = APanelSettings(this).apply { color.a = 0f }
    private val tutorials     = ATutorials(this).apply { color.a = 0f }

    override fun show() {
        setBackBackground(game.assetsLoader.loader.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine?.launch {
            runGDX {
                addPanel()
                if (game.isTutorialsUtil.isTutorials) addTutorials()
            }
            animShowPanelSuspend(panelSettings)
            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    private fun AdvancedStage.addPanel() {
        addActor(panelSettings)
        panelSettings.apply {
            setBounds(182f, HEIGHT, 812f, 430f)

            hideBlock = {
                coroutine?.launch {
                    if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
                    animHidePanelSuspend(panelSettings, it)
                }
            }

            var musicCounter = 0
            var soundCounter = 0

            coroutine?.launch {
                launch {
                    musicProgress.progressPercentFlow.collect {
                        musicCounter++
                        if (musicCounter == 2) {
                            soundProgress.enable()
                            tutorials.toSettingsSound()
                        }
                    }
                }
                launch {
                    soundProgress.progressPercentFlow.collect {
                        soundCounter++
                        if (soundCounter == 2) {
                            back.enable()
                            tutorials.toSettingsBack()
                        }
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addTutorials() {
        addAndFillActor(tutorials)
    }

}