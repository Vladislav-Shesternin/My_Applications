package com.williamsanteractive.jackputpasty.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.williamsanteractive.jackputpasty.game.actors.button.AButton
import com.williamsanteractive.jackputpasty.game.actors.button.AButtonStyle
import com.williamsanteractive.jackputpasty.game.actors.checkbox.ACheckBox
import com.williamsanteractive.jackputpasty.game.actors.checkbox.ACheckBoxStyle
import com.williamsanteractive.jackputpasty.game.manager.NavigationManager
import com.williamsanteractive.jackputpasty.game.manager.SpriteManager
import com.williamsanteractive.jackputpasty.game.musicUtil
import com.williamsanteractive.jackputpasty.game.utils.Layout
import com.williamsanteractive.jackputpasty.game.utils.actor.setBounds
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedGroup
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedScreen

class MenuScreen: AdvancedScreen() {

    companion object {
        private var isFirstOpen  = true
        private var isPauseMusic = false
    }

    private val playBtn  = AButton(AButtonStyle.play)
    private val exitBtn  = AButton(AButtonStyle.exit)
    private val musicBox = ACheckBox(ACheckBoxStyle.music)



    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()

        if (isFirstOpen) {
            isFirstOpen = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addGirl()
        addButtons()
        addMusic()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addGirl() {
        val girl = Image(SpriteManager.SplashRegion.GIRL.region)
        addActor(girl)
        girl.setBounds(Layout.Splash.girl)
    }

    private fun AdvancedGroup.addButtons() {
        addActors(playBtn, exitBtn)

        playBtn.apply {
            setBounds(1221f, 508f, 478f, 221f)
            setOnClickListener { NavigationManager.navigate(GamerScreen(), MenuScreen()) }
        }
        exitBtn.apply {
            setBounds(1221f, 230f, 478f, 221f)
            setOnClickListener { NavigationManager.exit() }
        }
    }

    private fun AdvancedGroup.addMusic() {
        addActor(musicBox)

        musicBox.apply {
            setBounds(38f, 289f, 314f, 365f)

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