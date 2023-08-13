package com.olympic.pair.shields.game.screens

import android.annotation.SuppressLint
import com.olympic.pair.shields.MainActivity
import com.olympic.pair.shields.game.game
import com.olympic.pair.shields.game.manager.NavigationManager
import com.olympic.pair.shields.game.manager.SpriteManager
import com.olympic.pair.shields.game.util.advanced.AdvancedScreen

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
        game.activity.lottie.hideLoader()
        NavigationManager.navigate(GameScreen())
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas._1)
            load(com.olympic.pair.shields.game.game.assetManager)
        }
        game.assetManager.finishLoading()
        SpriteManager.init(game.assetManager)
    }

}