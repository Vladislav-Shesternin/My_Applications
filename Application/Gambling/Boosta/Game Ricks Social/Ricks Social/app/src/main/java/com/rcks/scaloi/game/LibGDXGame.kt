package com.rcks.scaloi.game

import com.rcks.scaloi.MainActivity
import com.rcks.scaloi.game.manager.NavigationManager
import com.rcks.scaloi.game.util.advanced.AdvancedGame
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.utils.ScreenUtils
import com.rcks.scaloi.game.screens.LoaderScreen

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(LoaderScreen())
    }

    override fun render() {
        ScreenUtils.clear(BLACK)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}