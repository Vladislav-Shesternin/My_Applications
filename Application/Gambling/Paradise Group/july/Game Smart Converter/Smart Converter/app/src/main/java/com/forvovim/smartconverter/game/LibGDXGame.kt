package com.forvovim.smartconverter.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.forvovim.smartconverter.LazkaActivity
import com.forvovim.smartconverter.game.manager.NavigationManager
import com.forvovim.smartconverter.game.screens.DividendScreen
import com.forvovim.smartconverter.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: LazkaActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(DividendScreen())
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