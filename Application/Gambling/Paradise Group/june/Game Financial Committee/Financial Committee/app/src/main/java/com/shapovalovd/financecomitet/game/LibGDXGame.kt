package com.shapovalovd.financecomitet.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.shapovalovd.financecomitet.MainActivity
import com.shapovalovd.financecomitet.game.manager.NavigationManager
import com.shapovalovd.financecomitet.game.screens.SplashScreen
import com.shapovalovd.financecomitet.game.utils.GameColor
import com.shapovalovd.financecomitet.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

var calara = GameColor.bab
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(calara)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}