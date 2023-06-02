package com.karpenkov.budgetgid.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.karpenkov.budgetgid.MainActivity
import com.karpenkov.budgetgid.game.manager.NavigationManager
import com.karpenkov.budgetgid.game.screens.SplashScreen
import com.karpenkov.budgetgid.game.utils.GameColor
import com.karpenkov.budgetgid.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
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