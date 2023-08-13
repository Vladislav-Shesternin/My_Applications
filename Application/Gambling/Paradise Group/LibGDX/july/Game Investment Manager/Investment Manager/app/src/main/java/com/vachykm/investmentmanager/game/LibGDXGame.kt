package com.vachykm.investmentmanager.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.vachykm.investmentmanager.MainActivity
import com.vachykm.investmentmanager.game.manager.NavigationManager
import com.vachykm.investmentmanager.game.screens.SplashScreen
import com.vachykm.investmentmanager.game.utils.GameColor
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var colorit = GameColor.purple

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(colorit)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}