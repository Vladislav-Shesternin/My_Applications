package com.vbbb.time.game.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.vbbb.time.game.game.screens.SplashScreen
import com.vbbb.time.game.MainActivity
import com.vbbb.time.game.game.manager.NavigationManager
import com.vbbb.time.game.game.utils.GameColor
import com.vbbb.time.game.game.utils.advanced.AdvancedGame
import com.vbbb.time.game.game.utils.asset.SoundUtil

lateinit var game     : LibGDXGame private set
lateinit var soundUtil: SoundUtil private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()
        soundUtil    = SoundUtil()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(GameColor.wit)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}