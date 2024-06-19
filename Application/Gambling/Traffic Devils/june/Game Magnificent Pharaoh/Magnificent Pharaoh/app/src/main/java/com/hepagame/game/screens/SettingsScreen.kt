package com.hepagame.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.hepagame.game.LibGDXGame
import com.hepagame.game.actors.AProgress
import com.hepagame.game.utils.TIME_ANIM
import com.hepagame.game.utils.actor.animHide
import com.hepagame.game.utils.actor.animShow
import com.hepagame.game.utils.actor.setOnClickListener
import com.hepagame.game.utils.advanced.AdvancedScreen
import com.hepagame.game.utils.advanced.AdvancedStage
import com.hepagame.game.utils.font.FontParameter
import com.hepagame.game.utils.region
import com.hepagame.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen(override val game: LibGDXGame) : AdvancedScreen() {
    private val paramsFont = FontParameter().setCharacters("Settings").setSize(84)
    private val font       = fontGenerator_Kings.generateFont(paramsFont)

    private val label = Label("Settings", Label.LabelStyle(font, Color.WHITE))

    private val settings = Image(game.assasinAll.LAPATE)
    private val murlo = Image(game.assasinAll.MURLO).apply { this.touchable = Touchable.disabled }
    private val back = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.assasinLoader.PAGRAN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(407f, 229f, 732f, 628f) })
        addPanel()
        addActor(murlo.apply { setBounds(650f, 418f, 245f, 365f) })


        addActor(back)
        back.apply {
            setBounds(646f, 252f, 234f, 76f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }
        addActor(label)
        label.apply {
            setBounds(426f, 37f, 675f,119f)
            setAlignment(Align.center)
        }
        addTreasures()

    }

    private fun AdvancedStage.addPanel() {
        val progressMusic = AProgress(this@SettingsScreen)
        val progressSound = AProgress(this@SettingsScreen)

        addActors(progressMusic, progressSound)

        progressMusic.apply {
            setBounds(493f, 644f, 561f, 127f)
            progressPercentFlow.value = screen.game.musicUtil.volumeLevelFlow.value
            coroutine?.launch { progressPercentFlow.collect { runGDX { screen.game.musicUtil.volumeLevelFlow.value = it } } }
        }
        progressSound.apply {
            setBounds(493f, 432f, 561f, 127f)
            progressPercentFlow.value = screen.game.soundUtil.volumeLevel
            coroutine?.launch { progressPercentFlow.collect { runGDX { screen.game.soundUtil.volumeLevel = it } } }
        }
    }

    private fun AdvancedStage.addTreasures() {
        val left  = Image(game.assasinAll.SS)
        val right = Image(game.assasinAll.DD)

        addActors(left, right)
        left.apply {
            setBounds(57f, 75f, 320f, 309f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                        Actions.moveBy(-20f, 0f, 1f),
                        Actions.moveBy(50f, 0f, 2f),
                        Actions.moveBy(-30f, 0f, 1.5f),
                    )))
        }
        right.apply {
            setBounds(1149f, 52f, 347f, 353f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                        Actions.moveBy(20f, 0f, 1f),
                        Actions.moveBy(-50f, 0f, 2f),
                        Actions.moveBy(30f, 0f, 1.5f),
                    )))
        }
    }

}