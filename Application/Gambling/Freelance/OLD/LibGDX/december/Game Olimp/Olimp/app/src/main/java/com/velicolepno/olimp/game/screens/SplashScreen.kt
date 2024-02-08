package com.velicolepno.olimp.game.screens

import android.annotation.SuppressLint
import com.velicolepno.olimp.MainActivity
import com.velicolepno.olimp.game.game
import com.velicolepno.olimp.game.manager.NavigationManager
import com.velicolepno.olimp.game.manager.SpriteManager
import com.velicolepno.olimp.game.util.advanced.AdvancedScreen

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
        MainActivity.lottie.hideLoader()
        NavigationManager.navigate(GameScreen())
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas._1)
            load(game.assetManager)
        }
        game.assetManager.finishLoading()
        SpriteManager.init(game.assetManager)
    }

}