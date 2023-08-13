package com.lakalutic.statisticsmanager.game.screens

import com.lakalutic.statisticsmanager.LambarginiActivity
import com.lakalutic.statisticsmanager.game.game
import com.lakalutic.statisticsmanager.game.manager.FontTTFManager
import com.lakalutic.statisticsmanager.game.manager.GameDataStoreManager
import com.lakalutic.statisticsmanager.game.manager.NavigationManager
import com.lakalutic.statisticsmanager.game.manager.SpriteManager
import com.lakalutic.statisticsmanager.game.utils.advanced.AdvancedScreen
import com.lakalutic.statisticsmanager.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MarrionetkaScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   // private val paragrasssssa by lazy { Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_100.font, Color.WHITE)) }

    override fun show() {
        loadSplashAssets()
        //setBackgrounds(SpriteManager.SplashRegion.BARODADA.region)
        super.show()
        loadAssets()
        collectProgress()

        isFinishAnim = true
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }


//    override fun AdvancedStage.addActorsOnStageUI() {
//        coroutine.launch {
//
//            runGDX {
//                addLoaders()
//                root.animShow(0.5f)
//            }
//
//            delay(2_000)
//            isFinishAnim = true
//        }
//    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

//    private fun AdvancedStage.addLoaders() {
//
//    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(SpriteManager) {
//            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.LOADER, SpriteManager.EnumAtlas.CIRCLE)
//            loadAtlas(game.assetManager)
//            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
//            loadTexture(game.assetManager)
//        }
//        game.assetManager.finishLoading()
//
//        SpriteManager.initAtlas(game.assetManager)
//        SpriteManager.initTexture(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
//        with(FontTTFManager) {
//            loadableListFont = (
//                    FontTTFManager.GilRegularro.values +
//                    FontTTFManager.GilMediummo.values
//            ).toMutableList()
//            load(game.assetManager)
//        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
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
                    //runGDX { paragrasssssa.setText("$progress%") }
                    if (progress % 35 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((5..10).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            coroutine.launch {
                val isLotok = GameDataStoreManager.Lotos.get() ?: false

                LambarginiActivity.lottie.hideLoader()

                NavigationManager.navigate(if (isLotok) SVozvratomDrugScreen() else MejenerScreen())
            }
        }
    }

}