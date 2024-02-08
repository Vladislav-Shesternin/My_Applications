package com.huge.casine.slyts.game.screens

import android.annotation.SuppressLint
import com.huge.casine.slyts.MainActivity
import com.huge.casine.slyts.game.game
import com.huge.casine.slyts.game.manager.FontTTFManager
import com.huge.casine.slyts.game.manager.NavigationManager
import com.huge.casine.slyts.game.manager.SpriteManager
import com.huge.casine.slyts.game.utils.advanced.AdvancedGroup
import com.huge.casine.slyts.game.utils.advanced.AdvancedScreen
import com.huge.casine.slyts.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   // private val progressLabel by lazy { Label("", Label.LabelStyle(FontTTFManager.IngridFont.font_135.font, GameColor.wit)) }


    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.SplashRegion.BURAKSA.region)
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
        addProgress()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addProgress() {
//        addActor(progressLabel)
//        progressLabel.apply {
//            setBounds(LS.progress)
//            setAlignment(Align.center)
//        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BRJAJAK)
            loadTexture(game.assetManager)
        }
        game.assetManager.finishLoading()

        SpriteManager.initTexture(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.InriaSerifFont.font_68)
            load(game.assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
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
                   // runGDX { progressLabel.setText("$progress%") }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                   // delay((10..15).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            MainActivity.lottie.hideLoader()
            NavigationManager.navigate(MonoScreen())
        }
    }


}