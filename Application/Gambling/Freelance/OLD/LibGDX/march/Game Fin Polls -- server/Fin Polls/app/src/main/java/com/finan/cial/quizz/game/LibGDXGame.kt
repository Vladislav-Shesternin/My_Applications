package com.finan.cial.quizz.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.finan.cial.quizz.MainActivity
import com.finan.cial.quizz.game.manager.NavigationManager
import com.finan.cial.quizz.game.screens.SplashScreen
import com.finan.cial.quizz.game.utils.MusicUtil
import com.finan.cial.quizz.game.utils.advanced.AdvancedGame

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
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        musicUtil.dispose()
    }

}