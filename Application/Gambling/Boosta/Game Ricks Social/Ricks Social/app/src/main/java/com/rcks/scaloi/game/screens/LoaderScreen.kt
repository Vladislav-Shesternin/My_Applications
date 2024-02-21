package com.rcks.scaloi.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.rcks.scaloi.game.actors.label.LabelStyle
import com.rcks.scaloi.game.game
import com.rcks.scaloi.game.manager.FontTTFManager
import com.rcks.scaloi.game.manager.NavigationManager
import com.rcks.scaloi.game.manager.SpriteManager
import com.rcks.scaloi.game.util.advanced.AdvancedScreen
import com.rcks.scaloi.game.util.advanced.AdvancedStage
import com.rcks.scaloi.game.util.runGDX
import com.rcks.scaloi.util.Once
import com.rcks.scaloi.util.log
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.rcks.scaloi.game.util.Layout.Splash as LS


var isMusic = true
var isSound = true

var buttonSound: Sound? = null
var musical: Music? = null

class LoaderScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.font_150) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex = Mutex()
    private var progressValue = 0

    private val coroutineUpdateAssets = CoroutineScope(Dispatchers.Main)



    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }


    override fun AdvancedStage.addActorsOnStage() {
        addProgress()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            with(LS.progress) { setBounds(x, y, w, h) }
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.AreYouSeriousFont.font_150)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = (FontTTFManager.AreYouSeriousFont.values).toMutableList()
            load(game.assetManager)
        }
    }

    private fun loadingAssets() {
        if (coroutineUpdateAssets.isActive) {
            coroutineUpdateAssets.launch {
                progressMutex.withLock {
                    runGDX { game.assetManager.update() }
                    showProgress(game.assetManager.progress)
                }
            }
        }
    }

    private suspend fun showProgress(progress: Float) {
        while ((progressValue / 100f) < progress) {
            progressValue++
            log("$progressValue%")
            runGDX { progressLabel.setText("$progressValue%") }
            if (progressValue == 100) {
                onceFinishLoadingAssets.once {
                    coroutineUpdateAssets.cancel()
                    runGDX {
                        game.assetManager.finishLoading()
                        initAssets()

                        game.assetManager.load("magical-moments-of-christmas.ogg", Music::class.java)
                        game.assetManager.load("button.mp3", Sound::class.java)
                        game.assetManager.finishLoading()
                        musical = game.assetManager["magical-moments-of-christmas.ogg", Music::class.java]
                        buttonSound = game.assetManager["button.mp3", Sound::class.java]
                        musical?.apply {
                            isLooping = true
                            volume    = 0.25f
                            play()
                        }

                        NavigationManager.navigate(MenuScreen())
                        game.activity.lottie.hideLoader()
                    }
                }
            }
            delay(10)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

}