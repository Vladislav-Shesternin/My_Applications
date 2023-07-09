package com.vitaliyi.financeanalizator.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.vitaliyi.financeanalizator.MainActivity
import com.vitaliyi.financeanalizator.game.manager.NavigationManager
import com.vitaliyi.financeanalizator.game.screens.SplashScreen
import com.vitaliyi.financeanalizator.game.utils.GameColor
import com.vitaliyi.financeanalizator.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

var colorneucherus = GameColor.babkun
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