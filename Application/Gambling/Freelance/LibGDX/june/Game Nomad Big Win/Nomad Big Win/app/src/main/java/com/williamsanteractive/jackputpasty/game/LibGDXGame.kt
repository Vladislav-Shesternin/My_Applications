package com.williamsanteractive.jackputpasty.game

import com.williamsanteractive.jackputpasty.MainActivity
import com.williamsanteractive.jackputpasty.game.manager.NavigationManager
import com.williamsanteractive.jackputpasty.game.screens.SplashScreen
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedGame
import com.williamsanteractive.jackputpasty.game.utils.asset.MusicUtil
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils

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
        ScreenUtils.clear(Color.CLEAR)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
        musicUtil.dispose()
    }

}