package com.book.of.dead.deluxe.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.utils.ScreenUtils
import com.book.of.dead.deluxe.MainActivity
import com.book.of.dead.deluxe.game.manager.NavigationManager
import com.book.of.dead.deluxe.game.screens.PreloaderScreen
import com.book.of.dead.deluxe.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(PreloaderScreen())
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