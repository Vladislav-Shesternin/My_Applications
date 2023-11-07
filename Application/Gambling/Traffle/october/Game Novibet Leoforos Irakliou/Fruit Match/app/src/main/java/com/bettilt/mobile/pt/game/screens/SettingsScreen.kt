package com.bettilt.mobile.pt.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.bettilt.mobile.pt.game.LibGDXGame
import com.bettilt.mobile.pt.game.actors.button.AButton
import com.bettilt.mobile.pt.game.actors.button.AButtonText
import com.bettilt.mobile.pt.game.actors.progress.AProgress
import com.bettilt.mobile.pt.game.manager.AudioManager
import com.bettilt.mobile.pt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.bettilt.mobile.pt.game.utils.actor.animHide
import com.bettilt.mobile.pt.game.utils.actor.animShow
import com.bettilt.mobile.pt.game.utils.actor.setOnClickListener
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedMouseScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedStage
import com.bettilt.mobile.pt.game.utils.font.FontParameter
import com.bettilt.mobile.pt.game.utils.region
import com.bettilt.mobile.pt.game.utils.runGDX
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    companion object {
        private var musicPercent = -1f
        private var soundPercent = -1f
    }

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val font59    = fontGeneratorKadwa_Bold.generateFont(parameter.setSize(59))
    private val font35    = fontGeneratorKadwa_Bold.generateFont(parameter.setSize(35))

    private val backBtn       = AButtonText(this, "Back", AButton.Type.DEFAULT, Label.LabelStyle(font35, Color.BLACK))
    private val musicProgress = AProgress(this)
    private val soundProgress = AProgress(this)
    private val musicLbl      = Label("Music:", Label.LabelStyle(font59, Color.BLACK))
    private val soundLbl      = Label("Sound:", Label.LabelStyle(font59, Color.BLACK))



    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.spriteUtil.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBackBtn()
        addProgressLbl()
        addProgress()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgressLbl() {
        addActors(musicLbl, soundLbl)
        musicLbl.setBounds(179f, 610f, 417f, 92f)
        soundLbl.setBounds(179f, 364f, 417f, 92f)
    }

    private fun AdvancedStage.addProgress() {
        addActors(musicProgress, soundProgress)
        musicProgress.apply {
            setBounds(144f, 533f, 1274f, 77f)
            if (musicPercent == -1f) musicPercent = AudioManager.volumeLevelPercent
            setProgressPercent(musicPercent)
        }
        soundProgress.apply {
            setBounds(144f, 287f, 1274f, 77f)
            if (soundPercent == -1f) soundPercent = AudioManager.volumeLevelPercent
            setProgressPercent(soundPercent)
        }

        coroutine?.launch {
            launch {
                musicProgress.progressPercentFlow.collect {
                    musicPercent = it
                    runGDX {
                        musicLbl.setText("Music: ${it.toInt()}%")
                        game.musicUtil.volumeLevelFlow.value = it
                    }
                }
            }
            launch {
                soundProgress.progressPercentFlow.collect {
                    soundPercent = it
                    runGDX {
                        soundLbl.setText("Sound: ${it.toInt()}%")
                        game.soundUtil.volumeLevel = it / 100
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addBackBtn() {
        addActor(backBtn)
        backBtn.apply {
            setBounds(604f, 96f, 353f, 87f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }
    }

}