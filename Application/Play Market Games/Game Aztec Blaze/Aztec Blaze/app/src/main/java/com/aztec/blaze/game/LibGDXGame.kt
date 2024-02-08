package com.aztec.blaze.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.utils.ScreenUtils
import com.aztec.blaze.MainActivity
import com.aztec.blaze.game.manager.NavigationManager
import com.aztec.blaze.game.screens.SplashkaScreen
import com.aztec.blaze.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashkaScreen())
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