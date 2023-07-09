package com.bango.weld.androit.game

import com.bango.weld.androit.MainActivity
import com.bango.weld.androit.game.manager.NavigationManager
import com.bango.weld.androit.game.screens.SplashScreen
import com.bango.weld.androit.game.utils.advanced.AdvancedGame
import com.bango.weld.androit.game.utils.asset.MusicUtil
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.bango.weld.androit.game.utils.GameColor

lateinit var game     : LibGDXGame private set
lateinit var musicUtil: MusicUtil private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set


    override fun create() {
        game         = this
        assetManager = AssetManager()
        musicUtil    = MusicUtil()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(GameColor.purple)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        musicUtil.dispose()
    }

}