package com.invt.nard.app.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.invt.nard.app.game.LibGDXGame
import com.invt.nard.app.game.actors.button.AButton
import com.invt.nard.app.game.actors.button.AButtonStyle
import com.invt.nard.app.game.actors.checkbox.ACheckBox
import com.invt.nard.app.game.actors.checkbox.ACheckBoxGroup
import com.invt.nard.app.game.actors.checkbox.ACheckBoxStyle
import com.invt.nard.app.game.manager.FontTTFManager
import com.invt.nard.app.game.manager.NavigationManager
import com.invt.nard.app.game.manager.SpriteManager
import com.invt.nard.app.game.musicUtil
import com.invt.nard.app.game.utils.actor.setBounds
import com.invt.nard.app.game.utils.advanced.AdvancedGroup
import com.invt.nard.app.game.utils.advanced.AdvancedScreen
import com.invt.nard.app.game.utils.asset.MusicUtil
import com.invt.nard.app.game.utils.runGDX
import com.invt.nard.app.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.invt.nard.app.game.utils.Layout.Profile as LP

class ProfileScreen: AdvancedScreen() {
    companion object {
        var indexBall = 0
            private set

        private var isMusic = true
        private var isOnce  = true
    }

    // Actors
    private val ballsImage  = Image(SpriteManager.GameRegion.BALLS.region)
    private val currentList = List(8) { ACheckBox(ACheckBoxStyle.current) }
    private val recoLabel   = Label("", Label.LabelStyle(FontTTFManager.SemiBold.font_50.font, Color.WHITE))
    private val playButton  = AButton(AButtonStyle.play)
    private val musicBox    = ACheckBox(ACheckBoxStyle.music)

    // Fields
    private val boxGroup = ACheckBoxGroup()

    override fun show() {
        super.show()
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)

        if (isOnce) {
            isOnce = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }
        if (isMusic) musicBox.uncheck() else musicBox.check()

    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addBalls()
        addCurrentList()
        addReco()
        addPlay()
        addMusic()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedGroup.addBalls() {
        addActor(ballsImage)
        ballsImage.apply {
            setBounds(LP.balls)
        }
    }
    private fun AdvancedGroup.addReco() {
        addActor(recoLabel)
        recoLabel.apply {
            setBounds(LP.reco)
        }

        coroutine.launch {
            LibGDXGame.recordFlow.collect {
                runGDX { recoLabel.setText(it) }
            }
        }
    }
    private fun AdvancedGroup.addCurrentList() {
        var nx = LP.box.x
        var ny = LP.box.y

        currentList.onEachIndexed { index, box ->
            addActor(box)
            box.checkBoxGroup = boxGroup

            with(LP.box) {
                box.setBounds(nx, ny, w, h)
                nx += w + hs

                if (index.inc() == 4) {
                    nx = x
                    ny -= vs + h
                }
            }

            box.setOnCheckListener {
                if (it) {
                    indexBall = index
                    log("i = $indexBall")
                }
            }

        }

        currentList.first().check()
    }

    private fun AdvancedGroup.addPlay() {
        addActor(playButton)
        playButton.apply {
            setBounds(LP.play)
            setOnClickListener { NavigationManager.navigate(GameScreen(), ProfileScreen()) }
        }
    }

    private fun AdvancedGroup.addMusic() {
        addActor(musicBox)
        musicBox.apply {
            setBounds(LP.music)
            setOnCheckListener {
                isMusic = it
                if (isMusic) {
                    isMusic = false
                    musicUtil.music?.pause()
                } else {
                    isMusic = true
                    musicUtil.music?.play()
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

}