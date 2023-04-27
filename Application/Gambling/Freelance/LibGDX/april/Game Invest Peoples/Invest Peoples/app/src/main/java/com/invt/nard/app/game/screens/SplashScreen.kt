package com.invt.nard.app.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.invt.nard.app.MainActivity
import com.invt.nard.app.game.game
import com.invt.nard.app.game.manager.FontTTFManager
import com.invt.nard.app.game.manager.MusicManager
import com.invt.nard.app.game.manager.NavigationManager
import com.invt.nard.app.game.manager.SpriteManager
import com.invt.nard.app.game.utils.GameColor
import com.invt.nard.app.game.utils.actor.setBounds
import com.invt.nard.app.game.utils.advanced.AdvancedGroup
import com.invt.nard.app.game.utils.advanced.AdvancedScreen
import com.invt.nard.app.game.utils.runGDX
import com.invt.nard.app.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.invt.nard.app.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val progressLabel by lazy { Label("", Label.LabelStyle(FontTTFManager.SemiBold.font_72.font, GameColor.blue)) }


    override fun show() {
        loadSplashAssets()
        super.show()
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)
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
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.SemiBold.font_72)
            load(game.assetManager)
        }
        with(MusicManager) {
            loadListMusic = MusicManager.EnumMusic.values().toMutableList()
            load(game.assetManager)
        }
        game.assetManager.finishLoading()

        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
        MusicManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = FontTTFManager.SemiBold.values.toMutableList()
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
                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((10..15).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            MainActivity.lottie.hideLoader()
            NavigationManager.navigate(ProfileScreen())
        }
    }


}