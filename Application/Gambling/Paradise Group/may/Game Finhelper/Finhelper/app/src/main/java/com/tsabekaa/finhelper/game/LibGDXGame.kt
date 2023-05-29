package com.tsabekaa.finhelper.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.tsabekaa.finhelper.MainActivity
import com.tsabekaa.finhelper.game.manager.NavigationManager
import com.tsabekaa.finhelper.game.screens.SplashScreen
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGame
import com.tsabekaa.finhelper.game.utils.asset.MusicUtil
import com.tsabekaa.finhelper.game.utils.asset.SoundUtil

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