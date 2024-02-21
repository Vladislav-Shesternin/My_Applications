package com.fellinger.yeasman.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.fellinger.yeasman.game.utils.advanced.AdvancedGame
import com.fellinger.yeasman.game.manager.NavigationManager
import com.fellinger.yeasman.game.screens.Loader
import com.fellinger.yeasman.MainActivity

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(Loader())
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}