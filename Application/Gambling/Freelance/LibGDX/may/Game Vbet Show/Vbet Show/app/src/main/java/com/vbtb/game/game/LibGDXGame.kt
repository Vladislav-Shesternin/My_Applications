package com.vbtb.game.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.vbtb.game.game.screens.SplashScreen
import com.vbtb.game.MainActivity
import com.vbtb.game.game.manager.NavigationManager
import com.vbtb.game.game.utils.GameColor
import com.vbtb.game.game.utils.advanced.AdvancedGame
import com.vbtb.game.game.utils.asset.MusicUtil

lateinit var game     : LibGDXGame private set
lateinit var musicUtil: MusicUtil private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()
        musicUtil    = MusicUtil()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(GameColor.background)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        musicUtil.dispose()
        assetManager.dispose()
    }

}