package com.ukracc.finproject.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.ukracc.finproject.game.actors.button.AButton
import com.ukracc.finproject.game.actors.button.AButtonStyle
import com.ukracc.finproject.game.actors.progress.AProgress
import com.ukracc.finproject.game.manager.FontTTFManager
import com.ukracc.finproject.game.manager.NavigationManager
import com.ukracc.finproject.game.manager.SpriteManager
import com.ukracc.finproject.game.musicUtil
import com.ukracc.finproject.game.soundUtil
import com.ukracc.finproject.game.utils.advanced.AdvancedGroup
import com.ukracc.finproject.game.utils.advanced.AdvancedScreen
import com.ukracc.finproject.game.utils.runGDX
import kotlinx.coroutines.launch

class SettingsScreen: AdvancedScreen() {

    companion object {
        private var musicPercent = 100
        private var soundPercent = 100
    }

    private val msImage   = Image(SpriteManager.SettingsRegion.MS.region)
    private val musicText = Label("0%", Label.LabelStyle(FontTTFManager.AliceFont.font_50.font, Color.WHITE))
    private val soundText = Label("0%", Label.LabelStyle(FontTTFManager.AliceFont.font_50.font, Color.WHITE))
    private val backBtn   = AButton(AButtonStyle.back)
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
            setBounds(134f, 1390f, 520f, 192f)
        }
    }

    private fun AdvancedGroup.addLabels() {
        addActors(musicText, soundText)
        musicText.apply {
            setBounds(134f, 1251f, 183f, 103f)
            setAlignment(Align.center)
        }
        soundText.apply {
            setBounds(473f, 1251f, 183f, 103f)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedGroup.addBackBtn() {
        addActor(backBtn)
        backBtn.apply {
            setBounds(337f, 105f, 129f, 133f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addProgress() {
        addActors(progressMusic, progressSound)
        progressMusic.apply {
            setBounds(177f, 420f, 89f, 788f)
            progressPercentFlow.value = musicPercent.toFloat()
        }
        progressSound.apply {
            setBounds(514f, 420f, 89f, 788f)
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