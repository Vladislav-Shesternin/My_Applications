package com.playin.paganis.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.playin.paganis.game.screens.SplashScreen
import com.playin.paganis.MainActivity
import com.playin.paganis.game.manager.NavigationManager
import com.playin.paganis.game.utils.advanced.AdvancedGame

lateinit var game     : LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.CLEAR)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}