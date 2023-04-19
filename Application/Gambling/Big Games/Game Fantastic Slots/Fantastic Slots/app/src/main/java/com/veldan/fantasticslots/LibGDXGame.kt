package com.veldan.fantasticslots

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.fantasticslots.advanced.AdvancedGame
import com.veldan.fantasticslots.manager.NavigationManager
import com.veldan.fantasticslots.screens.splash.SplashScreen

lateinit var game: LibGDXGame private set
lateinit var assetManager: AssetManager private set

class LibGDXGame : AdvancedGame() {


    private val color = Color.BLACK



    override fun create() {
        game = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(color)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
       // MusicUtil.dispose()
    }

}