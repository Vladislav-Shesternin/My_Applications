package com.ukracc.finproject.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.ukracc.finproject.MainActivity
import com.ukracc.finproject.game.manager.NavigationManager
import com.ukracc.finproject.game.screens.SplashScreen
import com.ukracc.finproject.game.utils.advanced.AdvancedGame
import com.ukracc.finproject.game.utils.asset.MusicUtil
import com.ukracc.finproject.game.utils.asset.SoundUtil

lateinit var game     : LibGDXGame private set
lateinit var musicUtil: MusicUtil private set
lateinit var soundUtil: SoundUtil private set
class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()
        musicUtil    = MusicUtil()
        soundUtil    = SoundUtil()

        NavigationManager.navigate(SplashScreen())
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        musicUtil.dispose()
    }

}