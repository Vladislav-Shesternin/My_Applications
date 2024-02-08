package com.cosmo3v1.cosmoinc.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.cosmo3v1.cosmoinc.MainActivity
import com.cosmo3v1.cosmoinc.game.manager.NavigationManager
import com.cosmo3v1.cosmoinc.game.screens.SplashScreen
import com.cosmo3v1.cosmoinc.game.util.MusicUtil
import com.cosmo3v1.cosmoinc.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        MusicUtil.dispose()
    }

}