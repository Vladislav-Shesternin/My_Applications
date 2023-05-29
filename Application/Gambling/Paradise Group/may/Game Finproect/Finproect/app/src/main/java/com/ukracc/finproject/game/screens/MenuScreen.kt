package com.ukracc.finproject.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.ukracc.finproject.game.manager.NavigationManager
import com.ukracc.finproject.game.manager.SpriteManager
import com.ukracc.finproject.game.musicUtil
import com.ukracc.finproject.game.utils.actor.disable
import com.ukracc.finproject.game.utils.actor.setOnClickListener
import com.ukracc.finproject.game.utils.advanced.AdvancedGroup
import com.ukracc.finproject.game.utils.advanced.AdvancedScreen

class MenuScreen: AdvancedScreen() {
    companion object {
        private var isFirstOpen = true
    }

    private val btnsImage   = Image(SpriteManager.GameRegion.BTNS.region)
    private val listBtn     = Actor()
    private val settingsBtn = Actor()

    override fun show() {
        if (isFirstOpen) {
            isFirstOpen = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }

        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addBtns()

    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addBtns() {
        addActor(btnsImage)
        btnsImage.apply {
            setBounds(94f, 539f, 615f, 402f)
            disable()
        }

        addActors(listBtn, settingsBtn)
        listBtn.apply {
            setBounds(94f, 790f, 615f, 151f)
            setOnClickListener { NavigationManager.navigate(ListScreen(), MenuScreen()) }
        }
        settingsBtn.apply {
            setBounds(94f, 539f, 615f, 151f)
            setOnClickListener { NavigationManager.navigate(SettingsScreen(), MenuScreen()) }
        }
    }

}