package com.vbtb.game.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.vbtb.game.MainActivity
import com.vbtb.game.game.game
import com.vbtb.game.game.manager.FontTTFManager
import com.vbtb.game.game.manager.MusicManager
import com.vbtb.game.game.manager.NavigationManager
import com.vbtb.game.game.manager.SpriteManager
import com.vbtb.game.game.utils.actor.setBounds
import com.vbtb.game.game.utils.advanced.AdvancedGroup
import com.vbtb.game.game.utils.advanced.AdvancedScreen
import com.vbtb.game.game.utils.runGDX
import com.vbtb.game.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.vbtb.game.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val progressLabel by lazy { Label("", Label.LabelStyle(FontTTFManager.RobotoFont.font_75.font, Color.WHITE)) }


    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
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
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.progress)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
            loadAtlas(game.assetManager)
        }
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.RobotoFont.font_75)
            load(game.assetManager)
        }
        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(MusicManager) {
            loadableListMusic = mutableListOf(MusicManager.EnumMusic.MAIN)
            load(game.assetManager)
        }
//        with(SpriteManager) {
//            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
//            loadAtlas(game.assetManager)
//            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
//            loadTexture(game.assetManager)
//        }
    }

    private fun initAssets() {
//        SpriteManager.initAtlas(game.assetManager)
//        SpriteManager.initTexture(game.assetManager)
//        FontTTFManager.init(game.assetManager)
        MusicManager.init(game.assetManager)
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
                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((7..12).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            MainActivity.lottie.hideLoader()
            NavigationManager.navigate(SettingsScreen())
        }
    }


}