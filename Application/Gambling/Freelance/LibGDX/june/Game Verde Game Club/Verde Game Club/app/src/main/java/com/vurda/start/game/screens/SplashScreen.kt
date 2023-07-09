package com.vurda.start.game.screens

import android.annotation.SuppressLint
import com.vurda.start.MainActivity
import com.vurda.start.game.game
import com.vurda.start.game.manager.NavigationManager
import com.vurda.start.game.manager.SpriteManager
import com.vurda.start.game.utils.advanced.AdvancedGroup
import com.vurda.start.game.utils.advanced.AdvancedScreen
import com.vurda.start.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false


    override fun show() {
        //loadSplashAssets()
        //setUIBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
        loadAssets()
        collectProgress()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }


    override fun AdvancedGroup.addActorsOnGroup() {
            isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(SpriteManager) {
//            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
//            loadTexture(game.assetManager)
//        }

//        game.assetManager.finishLoading()

//        SpriteManager.initTexture(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.assetManager.update(16)) {
                isFinishLoading = true
                initAssets()
            }
            progressFlow.value = game.assetManager.progress
        }
    }

    private fun collectProgress() {
        coroutine.launch {
            var progress = 0
            progressFlow.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
                    if (progress % 25 == 0) log("progress = $progress")
                    if (progress == 100) isFinishProgress = true
                    delay((5..7).shuffled().first().toLong())
                }
            }
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            MainActivity.lottie.hideLoader()
            NavigationManager.navigate(MenuScreen())
        }
    }


}