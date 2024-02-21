package com.jungle.jumping.bird.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.jungle.jumping.bird.game.utils.advanced.AdvancedGame
import com.jungle.jumping.bird.game.manager.NavigationManager
import com.jungle.jumping.bird.game.screens.KloaderScreen
import com.jungle.jumping.bird.MainActivity

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(KloaderScreen())
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