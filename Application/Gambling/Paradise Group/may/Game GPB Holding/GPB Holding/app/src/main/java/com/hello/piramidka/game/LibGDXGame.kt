package com.hello.piramidka.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.hello.piramidka.MainActivity
import com.hello.piramidka.game.manager.NavigationManager
import com.hello.piramidka.game.screens.SplashScreen
import com.hello.piramidka.game.utils.ThemeUtil
import com.hello.piramidka.game.utils.advanced.AdvancedGame

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