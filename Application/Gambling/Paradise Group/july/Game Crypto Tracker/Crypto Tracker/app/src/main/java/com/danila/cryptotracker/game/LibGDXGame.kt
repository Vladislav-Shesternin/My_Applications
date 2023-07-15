package com.danila.cryptotracker.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.danila.cryptotracker.PopinsActivity
import com.danila.cryptotracker.game.manager.NavigationManager
import com.danila.cryptotracker.game.screens.SaploshScreen
import com.danila.cryptotracker.game.utils.GameColor
import com.danila.cryptotracker.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: PopinsActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SaploshScreen())
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