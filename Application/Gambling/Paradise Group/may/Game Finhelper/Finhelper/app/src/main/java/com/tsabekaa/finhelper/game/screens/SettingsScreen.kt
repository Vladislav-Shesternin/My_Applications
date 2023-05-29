package com.tsabekaa.finhelper.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.tsabekaa.finhelper.game.actors.button.AButton
import com.tsabekaa.finhelper.game.actors.button.AButtonStyle
import com.tsabekaa.finhelper.game.actors.progress.AProgress
import com.tsabekaa.finhelper.game.manager.FontTTFManager
import com.tsabekaa.finhelper.game.manager.NavigationManager
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.musicUtil
import com.tsabekaa.finhelper.game.soundUtil
import com.tsabekaa.finhelper.game.utils.actor.setOnClickListener
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGroup
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedScreen
import com.tsabekaa.finhelper.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen: AdvancedScreen() {

    companion object {
        private var musicPercent = 100
        private var soundPercent = 100
    }

    private val msImage   = Image(SpriteManager.GameRegion.MUZIKA_SAUNDA.region)
    private val musicText = Label("0%", Label.LabelStyle(FontTTFManager.BoldFont.font_50.font, Color.WHITE))
    private val soundText = Label("0%", Label.LabelStyle(FontTTFManager.BoldFont.font_50.font, Color.WHITE))
    private val backBtn   = Image(SpriteManager.GameRegion.BEKAPPA.region)
    private val progressMusic = AProgress()
    private val progressSound = AProgress()

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addMS()
        addLabels()
        addBackBtn()
        addProgress()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addMS() {
        addActor(msImage)
        msImage.apply {
            setBounds(34f, 525f, 104f, 465f)
        }
    }

    private fun AdvancedGroup.addLabels() {
        addActors(musicText, soundText)
        musicText.apply {
            setBounds(439f, 838f, 147f, 78f)
            setAlignment(Align.center)
        }
        soundText.apply {
            setBounds(439f, 525f, 147f, 78f)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedGroup.addBackBtn() {
        addActor(backBtn)
        backBtn.apply {
            setBounds(210f, 113f, 202f, 202f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addProgress() {
        addActors(progressMusic, progressSound)
        progressMusic.apply {
            setBounds(36f, 764f, 550f, 65f)
            progressPercentFlow.value = musicPercent.toFloat()
        }
        progressSound.apply {
            setBounds(36f, 451f, 550f, 65f)
            progressPercentFlow.value = soundPercent.toFloat()
        }

        coroutine.launch {
            launch { progressMusic.progressPercentFlow.collect { percent -> runGDX {
                musicText.setText("${percent.toInt()}%")
                musicUtil.volumeLevelFlow.value = percent
                musicPercent = percent.toInt()
            } } }
            launch { progressSound.progressPercentFlow.collect { percent -> runGDX {
                soundText.setText("${percent.toInt()}%")
                soundUtil.volumeLevelPercent = percent
                soundPercent = percent.toInt()
            } } }
        }

    }

}