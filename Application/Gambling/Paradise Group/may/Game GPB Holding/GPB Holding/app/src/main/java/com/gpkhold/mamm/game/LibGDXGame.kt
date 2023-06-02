package com.gpkhold.mamm.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.gpkhold.mamm.MainActivity
import com.gpkhold.mamm.game.manager.NavigationManager
import com.gpkhold.mamm.game.screens.SplashScreen
import com.gpkhold.mamm.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.WHITE)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}