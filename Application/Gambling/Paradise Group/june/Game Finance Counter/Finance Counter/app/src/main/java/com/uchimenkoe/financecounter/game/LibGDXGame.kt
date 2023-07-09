package com.uchimenkoe.financecounter.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.uchimenkoe.financecounter.MainActivity
import com.uchimenkoe.financecounter.game.manager.NavigationManager
import com.uchimenkoe.financecounter.game.screens.SplashScreen
import com.uchimenkoe.financecounter.game.utils.GameColor
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

var calaraka = GameColor.bulu
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(calaraka)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}