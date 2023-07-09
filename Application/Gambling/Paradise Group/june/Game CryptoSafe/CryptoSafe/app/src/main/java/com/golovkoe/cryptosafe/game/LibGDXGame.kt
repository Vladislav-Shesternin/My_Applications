package com.golovkoe.cryptosafe.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.golovkoe.cryptosafe.MainActivity
import com.golovkoe.cryptosafe.game.manager.NavigationManager
import com.golovkoe.cryptosafe.game.screens.SplashScreen
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

var colorsha = Color.CLEAR
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(colorsha)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}