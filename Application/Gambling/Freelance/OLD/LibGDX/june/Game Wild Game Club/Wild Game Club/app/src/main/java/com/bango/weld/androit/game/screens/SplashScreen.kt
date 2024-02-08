package com.bango.weld.androit.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bango.weld.androit.MainActivity
import com.bango.weld.androit.game.game
import com.bango.weld.androit.game.manager.FontTTFManager
import com.bango.weld.androit.game.manager.MusicManager
import com.bango.weld.androit.game.manager.NavigationManager
import com.bango.weld.androit.game.manager.SpriteManager
import com.bango.weld.androit.game.utils.actor.setBounds
import com.bango.weld.androit.game.utils.advanced.AdvancedGroup
import com.bango.weld.androit.game.utils.advanced.AdvancedScreen
import com.bango.weld.androit.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.bango.weld.androit.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   // private val progressLabel by lazy { Label("", Label.LabelStyle(FontTTFManager.RegularFont.font_90.font, GameColor.loader)) }


    override fun show() {
        loadSplashAssets()
//        setBackgrounds(SpriteManager.SplashRegion.BARAGRANDOM.region)
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

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(SpriteManager) {
//            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BARAGRANDOM, SpriteManager.EnumTexture.SOVA)
//            loadTexture(game.assetManager)
//        }
//        with(FontTTFManager) {
//            loadableListFont = mutableListOf(FontTTFManager.RegularFont.font_90)
//            load(game.assetManager)
//        }
//        game.assetManager.finishLoading()
//
//        SpriteManager.initTexture(game.assetManager)
        //FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(MusicManager) {
            loadableListMusic = mutableListOf(MusicManager.EnumMusic.MAIN)
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadableFontList = mutableListOf(FontTTFManager.Jim.font_75)
            load(game.assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        MusicManager.init(game.assetManager)
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
                    //runGDX { progressLabel.setText("$progress%") }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((4..9).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            MainActivity.lottie.hideLoader()
            NavigationManager.navigate(MenuScreen())
        }
    }


}