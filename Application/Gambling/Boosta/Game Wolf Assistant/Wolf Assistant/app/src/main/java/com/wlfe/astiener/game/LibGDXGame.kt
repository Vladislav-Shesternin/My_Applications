package com.wlfe.astiener.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.wlfe.astiener.MainActivity
import com.wlfe.astiener.game.manager.NavigationManager
import com.wlfe.astiener.game.screens.LoaderScreen
import com.wlfe.astiener.game.util.GameColor
import com.wlfe.astiener.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(LoaderScreen())
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