package com.dankom.financialtracker.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.dankom.financialtracker.MainActivity
import com.dankom.financialtracker.game.manager.NavigationManager
import com.dankom.financialtracker.game.screens.SplashScreen
import com.dankom.financialtracker.game.utils.GameColor
import com.dankom.financialtracker.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

var colorneucherus = GameColor.background
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(colorneucherus)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}