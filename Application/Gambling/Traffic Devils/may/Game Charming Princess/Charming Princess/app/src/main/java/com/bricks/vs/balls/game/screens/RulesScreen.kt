package com.bricks.vs.balls.game.screens

import com.bricks.vs.balls.game.GDXGame
import com.bricks.vs.balls.game.actors.ATutorials
import com.bricks.vs.balls.game.actors.panel.APanelRules
import com.bricks.vs.balls.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.bricks.vs.balls.game.utils.actor.animHidePanelSuspend
import com.bricks.vs.balls.game.utils.actor.animHideSuspend
import com.bricks.vs.balls.game.utils.actor.animShowPanelSuspend
import com.bricks.vs.balls.game.utils.actor.animShowSuspend
import com.bricks.vs.balls.game.utils.advanced.AdvancedScreen
import com.bricks.vs.balls.game.utils.advanced.AdvancedStage
import com.bricks.vs.balls.game.utils.region
import com.bricks.vs.balls.game.utils.runGDX
import kotlinx.coroutines.launch

class RulesScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelRules = APanelRules(this).apply { color.a = 0f }
    private val tutorials  = ATutorials(this).apply { color.a = 0f }

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
            animShowPanelSuspend(panelRules)
            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    private fun AdvancedStage.addPanel() {
        addActor(panelRules)
        panelRules.apply {
            setBounds(182f, HEIGHT, 812f, 430f)

            hideBlock = {
                coroutine?.launch {
                    if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
                    animHidePanelSuspend(panelRules, it)
                }
            }
        }
    }

    private fun AdvancedStage.addTutorials() {
        addAndFillActor(tutorials)
    }


}