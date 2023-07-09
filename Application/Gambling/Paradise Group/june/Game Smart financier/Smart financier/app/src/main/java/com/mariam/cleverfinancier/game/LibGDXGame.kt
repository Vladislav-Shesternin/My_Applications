package com.mariam.cleverfinancier.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.mariam.cleverfinancier.MainActivity
import com.mariam.cleverfinancier.game.manager.NavigationManager
import com.mariam.cleverfinancier.game.screens.SplashScreen
import com.mariam.cleverfinancier.game.utils.GameColor
import com.mariam.cleverfinancier.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

var colorneucherus = GameColor.whitik
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