package com.avietor.onlaneslets.game.screens

import com.avietor.onlaneslets.game.actors.button.AButtonStyle
import com.avietor.onlaneslets.game.actors.button.AButtonText
import com.avietor.onlaneslets.game.actors.checkbox.ACheckBox
import com.avietor.onlaneslets.game.actors.checkbox.ACheckBoxStyle
import com.avietor.onlaneslets.game.manager.FontTTFManager
import com.avietor.onlaneslets.game.manager.NavigationManager
import com.avietor.onlaneslets.game.manager.SpriteManager
import com.avietor.onlaneslets.game.musicUtil
import com.avietor.onlaneslets.game.utils.actor.setBounds
import com.avietor.onlaneslets.game.utils.actor.setOnClickListener
import com.avietor.onlaneslets.game.utils.advanced.AdvancedGroup
import com.avietor.onlaneslets.game.utils.advanced.AdvancedScreen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label

class MenuScreen: AdvancedScreen() {

    companion object {
        private var isFirstOpen = true
        private var isPauseMusic = false
    }

    private val playBtn  = AButtonText("Play", AButtonStyle.default, Label.LabelStyle(FontTTFManager.RegularFont.font_90.font, Color.WHITE))
    private val exitBtn  = AButtonText("Exit", AButtonStyle.default, Label.LabelStyle(FontTTFManager.RegularFont.font_90.font, Color.WHITE))
    private val musicBox = ACheckBox(ACheckBoxStyle.music)

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARAKUDA.region)
        super.show()

        if (isFirstOpen) {
            isFirstOpen = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addButtons()
        addMusic()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addButtons() {
        addActors(playBtn, exitBtn)

        playBtn.apply {
            setBounds(118f, 779f, 465f, 180f)
            setOnClickListener { NavigationManager.navigate(LevelsScreen(), MenuScreen()) }
        }
        exitBtn.apply {
            setBounds(118f, 566f, 465f, 180f)
            setOnClickListener { NavigationManager.exit() }
        }
    }

    private fun AdvancedGroup.addMusic() {
        addActor(musicBox)

        musicBox.apply {
            setBounds(251f, 333f, 200f, 200f)

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

}