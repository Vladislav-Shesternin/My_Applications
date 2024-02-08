package com.littleman.andball.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.littleman.andball.MainActivity
import com.littleman.andball.game.manager.NavigationManager
import com.littleman.andball.game.screens.LoaderScreen
import com.littleman.andball.game.util.GameColor
import com.littleman.andball.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(LoaderScreen())
    }

    override fun render() {
        ScreenUtils.clear(GameColor.kalar)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}