package com.gen.bettermeditatio.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.gen.bettermeditatio.MainActivity
import com.gen.bettermeditatio.game.manager.NavigationManager
import com.gen.bettermeditatio.game.screens.StartScreen
import com.gen.bettermeditatio.game.util.GameColor
import com.gen.bettermeditatio.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(StartScreen())
    }

    override fun render() {
        ScreenUtils.clear(GameColor.background)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}