package com.hgrt.wrld.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.utils.ScreenUtils
import com.hgrt.wrld.MainActivity
import com.hgrt.wrld.game.manager.NavigationManager
import com.hgrt.wrld.game.screens.SplashScreen
import com.hgrt.wrld.game.screens.musicUtil
import com.hgrt.wrld.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(BLACK)
        super.render()
    }

    override fun dispose() {
        musicUtil?.dispose()
        super.dispose()
        assetManager.dispose()
    }

}