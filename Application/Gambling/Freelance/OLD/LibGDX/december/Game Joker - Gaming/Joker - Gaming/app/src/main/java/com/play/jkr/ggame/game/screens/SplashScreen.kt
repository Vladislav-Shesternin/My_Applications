package com.play.jkr.ggame.game.screens

import android.annotation.SuppressLint
import com.play.jkr.ggame.MainActivity
import com.play.jkr.ggame.game.game
import com.play.jkr.ggame.game.manager.NavigationManager
import com.play.jkr.ggame.game.manager.SpriteManager
import com.play.jkr.ggame.game.util.advanced.AdvancedScreen

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
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            load(game.assetManager)
        }
        game.assetManager.finishLoading()
        SpriteManager.init(game.assetManager)
    }

}