package com.tmesfo.frtunes.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.tmesfo.frtunes.MainActivity
import com.tmesfo.frtunes.game.advanced.AdvancedGame
import com.tmesfo.frtunes.game.manager.NavigationManager
import com.tmesfo.frtunes.game.manager.assets.util.MusicUtil
import com.tmesfo.frtunes.game.screens.splash.LoadorScreen

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    private val color = Color.BLACK

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(LoadorScreen())
    }

    override fun render() {
        ScreenUtils.clear(color)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        MusicUtil.dispose()
        assetManager.dispose()
    }

}