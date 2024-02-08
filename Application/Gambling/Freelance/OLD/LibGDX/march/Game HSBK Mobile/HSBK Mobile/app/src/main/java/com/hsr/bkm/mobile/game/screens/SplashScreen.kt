package com.hsr.bkm.mobile.game.screens

import android.annotation.SuppressLint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.hsr.bkm.mobile.game.game
import com.hsr.bkm.mobile.game.manager.FontTTFManager
import com.hsr.bkm.mobile.game.manager.NavigationManager
import com.hsr.bkm.mobile.game.manager.SpriteManager
import com.hsr.bkm.mobile.game.utils.advanced.AdvancedGroup
import com.hsr.bkm.mobile.game.utils.advanced.AdvancedScreen
import com.hsr.bkm.mobile.util.data.News
import com.hsr.bkm.mobile.util.log

var news: List<News> = listOf()
var isGame = false

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    // private val logo by lazy { Image(SpriteManager.SplashRegion.LOGO.region) }


    override fun show() {
        loadSplashAssets()
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)
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
        addLogo()
        isFinishAnim = true

//        coroutine.launch {
//            animStage()
//            animLogo()
//            delay(2f.toMS)
//            isFinishAnim = true
//        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addLogo() {
//        addActor(logo)
//        logo.apply {
//            setBounds(LS.logo)
//        }
    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
            loadAtlas(game.assetManager)
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
            loadTexture(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
    }

    private fun loadAssets() {
//        with(SpriteManager) {
//            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
//            loadAtlas(game.assetManager)
//            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
//            loadTexture(game.assetManager)
//        }
        with(FontTTFManager) {
            loadableListFont = (
                    FontTTFManager.Inter.RegularFont.values
            ).toMutableList()
            load(game.assetManager)
        }
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.assetManager.update(17)) {
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
                    if (progress % 10 == 0) log("progress = $progress")
                    if (progress == 100) isFinishProgress = true
                    delay((10..15).shuffled().first().toLong())
                }
            }
        }
    }

    private fun initAssets() {
        // SpriteManager.initAtlas(game.assetManager)
        // SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim && news.isNotEmpty() && isGame) {
            isFinishAnim = false

            // MainActivity.lottie.hideLoader()
            NavigationManager.navigate(MenuScreen())
        }
    }


}