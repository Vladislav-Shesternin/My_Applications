package com.veldan.gamebox2d.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.gamebox2d.game.screens.SplashScreen
import com.veldan.gamebox2d.MainActivity
import com.veldan.gamebox2d.game.manager.NavigationManager
import com.veldan.gamebox2d.game.utils.GameColor
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGame
import com.veldan.gamebox2d.game.utils.asset.MusicUtil

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