package com.vbtb.game.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.vbtb.game.game.actors.Progress
import com.vbtb.game.game.actors.button.AButton
import com.vbtb.game.game.actors.button.AButtonStyle
import com.vbtb.game.game.actors.checkbox.ACheckBox
import com.vbtb.game.game.actors.checkbox.ACheckBoxStyle
import com.vbtb.game.game.manager.FontTTFManager
import com.vbtb.game.game.manager.NavigationManager
import com.vbtb.game.game.manager.SpriteManager
import com.vbtb.game.game.musicUtil
import com.vbtb.game.game.utils.actor.setBounds
import com.vbtb.game.game.utils.advanced.AdvancedGroup
import com.vbtb.game.game.utils.advanced.AdvancedScreen
import kotlinx.coroutines.launch
import com.vbtb.game.game.utils.Layout.Settings as LS

class SettingsScreen: AdvancedScreen() {

    companion object {
        private var isFirstOpen = true

        // 1.0 .. 10.0
        var weight     = 5f
        // 0.1 .. 1.0
        var elasticity = 0.5f
        // 0.1 .. 1.0
        var friction   = 0.5f
    }

    private val panelImage = Image(SpriteManager.GameRegion.PANEL.region)
    private val playButton = AButton(AButtonStyle.btn)
    private val musicCBox  = ACheckBox(ACheckBoxStyle.music)
    private val weightText         = Label("", Label.LabelStyle(FontTTFManager.RobotoFont.font_75.font, Color.WHITE))
    private val weightProgress     = Progress()
    private val elasticityText     = Label("", Label.LabelStyle(FontTTFManager.RobotoFont.font_75.font, Color.WHITE))
    private val elasticityProgress = Progress()
    private val frictionText       = Label("", Label.LabelStyle(FontTTFManager.RobotoFont.font_75.font, Color.WHITE))
    private val frictionProgress   = Progress()

    private var isPauseMusic = false


    override fun show() {
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
        super.show()

        if (isFirstOpen) {
            isFirstOpen = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addPanelImage()
        addPlayButton()
        addMusicCBox()
        addProgress()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addPanelImage() {
        addActor(panelImage)
        panelImage.apply {
            setBounds(LS.panel)
        }
    }

    private fun AdvancedGroup.addPlayButton() {
        addActor(playButton)
        playButton.apply {
            setBounds(LS.play)

            setOnClickListener { NavigationManager.navigate(GameScreen(), SettingsScreen()) }
        }
    }

    private fun AdvancedGroup.addMusicCBox() {
        addActor(musicCBox)
        musicCBox.apply {
            setBounds(LS.music)

            if (isPauseMusic) check() else uncheck()

            setOnCheckListener {
                if (it) {
                    isPauseMusic = true
                    musicUtil.music?.pause()
                } else {
                    isPauseMusic = false
                    musicUtil.music?.play()
                }
            }
        }
    }

    private fun AdvancedGroup.addProgress() {
        addActors(weightText, weightProgress)
        weightText.apply {
            setBounds(LS.weightText)
            setAlignment(Align.center)
        }
        weightProgress.apply {
            setBounds(LS.weightProgress)

            progressPercentFlow.value = weight * 10

            coroutine.launch {
                progressPercentFlow.collect { percent ->
                    weight = if(percent <= 10f) 1f else (0.1f * percent).toInt().toFloat()
                    weightText.setText("$weight")
                }
            }
        }

        addActors(elasticityText, elasticityProgress)
        elasticityText.apply {
            setBounds(LS.elasticityText)
            setAlignment(Align.center)
        }
        elasticityProgress.apply {
            setBounds(LS.elasticityProgress)

            progressPercentFlow.value = elasticity * 100

            coroutine.launch {
                progressPercentFlow.collect { percent ->
                    elasticity = if(percent <= 10f) 0.1f else percent.toInt() / 100f
                    elasticityText.setText("$elasticity")
                }
            }
        }

        addActors(frictionText, frictionProgress)
        frictionText.apply {
            setBounds(LS.frictionText)
            setAlignment(Align.center)
        }
        frictionProgress.apply {
            setBounds(LS.frictionProgress)

            progressPercentFlow.value = friction * 100

            coroutine.launch {
                progressPercentFlow.collect { percent ->
                    friction = if(percent <= 10f) 0.1f else percent.toInt() / 100f
                    frictionText.setText("$friction")
                }
            }
        }

    }

}