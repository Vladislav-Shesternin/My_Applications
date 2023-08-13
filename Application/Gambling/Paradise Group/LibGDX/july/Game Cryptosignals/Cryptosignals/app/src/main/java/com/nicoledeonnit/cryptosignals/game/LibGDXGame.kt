package com.nicoledeonnit.cryptosignals.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.nicoledeonnit.cryptosignals.MiniActivity
import com.nicoledeonnit.cryptosignals.game.manager.NavigationManager
import com.nicoledeonnit.cryptosignals.game.screens.CryptosignalsmeScreen
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var oranged = Color.BLACK

class LibGDXGame(val activity: MiniActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()
        NavigationManager.navigate(CryptosignalsmeScreen())
    }

    override fun render() {
        ScreenUtils.clear(oranged)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}