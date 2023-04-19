package com.jjjj.ooo.kkk.eer.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.jjjj.ooo.kkk.eer.MainActivity
import com.jjjj.ooo.kkk.eer.game.manager.NavigationManager
import com.jjjj.ooo.kkk.eer.game.screens.SplashScreen
import com.jjjj.ooo.kkk.eer.game.util.advanced.AdvancedGame

lateinit var game: LibGDXGame private set

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {


    lateinit var assetManager: AssetManager private set



    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SplashScreen())
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