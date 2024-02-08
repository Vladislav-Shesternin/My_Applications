package com.huge.casine.slyts.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.huge.casine.slyts.game.screens.SplashScreen
import com.huge.casine.slyts.MainActivity
import com.huge.casine.slyts.game.manager.NavigationManager
import com.huge.casine.slyts.game.utils.advanced.AdvancedGame

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