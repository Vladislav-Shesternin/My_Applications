package com.forvovim.smartconverter.game.screens

import com.forvovim.smartconverter.LazkaActivity
import com.forvovim.smartconverter.game.game
import com.forvovim.smartconverter.game.manager.FontTTFManager
import com.forvovim.smartconverter.game.manager.GameDataStoreManager
import com.forvovim.smartconverter.game.manager.NavigationManager
import com.forvovim.smartconverter.game.manager.SpriteManager
import com.forvovim.smartconverter.game.utils.advanced.AdvancedGroup
import com.forvovim.smartconverter.game.utils.advanced.AdvancedScreen
import com.forvovim.smartconverter.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DividendScreen : AdvancedScreen() {

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
    // Add Actors
    // ------------------------------------------------------------------------

//    private fun AdvancedGroup.addLogoLoader() {
//    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(SpriteManager) {
//            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.SPAH)
//            loadAtlas(game.assetManager)
//        }
//        game.assetManager.finishLoading()
//
//        SpriteManager.initAtlas(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = (
                    FontTTFManager.GilReg.values +
                    FontTTFManager.GilMedium.values +
                    FontTTFManager.GilSemBold.values
            ).toMutableList()
            load(game.assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
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
                    //runGDX { paragrasssssa.setText("$progress%") }
                    if (progress % 15 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((6..11).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            coroutine.launch {
                val isFlagAGA = GameDataStoreManager.Piston.get() ?: false

                LazkaActivity.lottie.hideLoader()
                NavigationManager.navigate(if (isFlagAGA) YjePoznoIdiSpatkiScreen() else ParnuhaScreen())
            }
        }
    }

}