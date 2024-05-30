package com.bricks.vs.balls.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.bricks.vs.balls.game.GDXGame
import com.bricks.vs.balls.game.actors.ATutorials
import com.bricks.vs.balls.game.actors.panel.APanelLevels
import com.bricks.vs.balls.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.bricks.vs.balls.game.utils.actor.animHideSuspend
import com.bricks.vs.balls.game.utils.actor.animShowSuspend
import com.bricks.vs.balls.game.utils.advanced.AdvancedScreen
import com.bricks.vs.balls.game.utils.advanced.AdvancedStage
import com.bricks.vs.balls.game.utils.region
import com.bricks.vs.balls.game.utils.runGDX
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LevelsScreen(override val game: GDXGame): AdvancedScreen() {

    private val panelLevels = APanelLevels(this).apply { color.a = 0f }
    private val tutorials   = ATutorials(this).apply { color.a = 0f }

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
            animShowPanelLevelsSuspend()
            if (game.isTutorialsUtil.isTutorials) tutorials.animShowSuspend(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    private fun AdvancedStage.addPanel() {
        addActor(panelLevels)
        panelLevels.apply {
            setBounds(48f, -801f, 1431f, 801f)

            hideBlock = {
                coroutine?.launch {
                    if (game.isTutorialsUtil.isTutorials) tutorials.animHideSuspend(TIME_ANIM_SCREEN_ALPHA)
                    animHidePanelLevelsSuspend(it)
                }
            }
        }
    }

    private fun AdvancedStage.addTutorials() {
        addAndFillActor(tutorials)
    }

    // Anim
    private suspend fun animShowPanelLevelsSuspend() = suspendCoroutine<Unit> { continuation ->
        panelLevels.addAction(Actions.sequence(
            Actions.parallel(
                Actions.fadeIn(0.3f),
                Actions.moveTo(48f, 49f, 0.5f, Interpolation.sineOut)
            ),
            Actions.run { continuation.resume(Unit) }
        ))
    }

    private suspend fun animHidePanelLevelsSuspend(block: () -> Unit) = suspendCoroutine<Unit> { continuation ->
        panelLevels.addAction(Actions.sequence(
            Actions.parallel(
                Actions.fadeOut(0.5f),
                Actions.moveTo(48f, -801f, 0.3f, Interpolation.sineIn)
            ),
            Actions.run { block() }
        ))
    }

}