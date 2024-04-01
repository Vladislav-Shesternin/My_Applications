package com.riseofgiza.velsolution.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.utils.ScreenUtils
import com.riseofgiza.velsolution.MainActivity
import com.riseofgiza.velsolution.game.screens.SplashScreen
import com.riseofgiza.velsolution.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()
        SplashScreen.NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(BLACK)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}