package com.obezana.playtocrypto.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.obezana.playtocrypto.MainActivity
import com.obezana.playtocrypto.game.manager.NavigationManager
import com.obezana.playtocrypto.game.screens.ZagruzkaScreen
import com.obezana.playtocrypto.game.utils.GameColor
import com.obezana.playtocrypto.game.utils.advanced.AdvancedGame

lateinit var game     : LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(ZagruzkaScreen())
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