package com.vurda.start.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.vurda.start.MainActivity
import com.vurda.start.game.manager.NavigationManager
import com.vurda.start.game.screens.SplashScreen
import com.vurda.start.game.utils.GameColor
import com.vurda.start.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()



        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.GREEN)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}