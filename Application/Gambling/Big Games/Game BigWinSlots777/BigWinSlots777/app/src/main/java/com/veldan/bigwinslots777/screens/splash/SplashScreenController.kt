package com.veldan.bigwinslots777.screens.splash

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Disposable
import com.veldan.bigwinslots777.actors.label.scrolling.ScrollingLabel
import com.veldan.bigwinslots777.main.game
import com.veldan.bigwinslots777.manager.NavigationManager
import com.veldan.bigwinslots777.manager.assets.*
import com.veldan.bigwinslots777.manager.assets.SpriteManager.SourceTexture
import com.veldan.bigwinslots777.screens.game.GameScreen
import com.veldan.bigwinslots777.utils.Once
import com.veldan.bigwinslots777.utils.cancelCoroutinesAll
import com.veldan.bigwinslots777.utils.controller.ScreenController
import com.veldan.bigwinslots777.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@SuppressLint("CustomSplashScreen")
class SplashScreenController(
    override val screen: SplashScreen
) : ScreenController, Disposable {

    private val coroutineProgress = CoroutineScope(Dispatchers.Default)
    private val onceLoadAssets = Once()
    private var progressValue = 0
    private val progressMutex = Mutex()



    override fun dispose() {
        cancelCoroutinesAll(coroutineProgress)
    }



    private fun initAssets() {
        with(game) {
            SpriteManager.init(assetManager)
            FontTTFManager.init(assetManager)
            FontBMPManager.init(assetManager)
            MusicManager.init(assetManager)
            SoundManager.init(assetManager)
        }
    }

    private fun showProgress(progress: Float) {
        coroutineProgress.launch { progressMutex.withLock {
            while ((progressValue / 100f) < progress) {
                progressValue++
                log("$progressValue%")
                Gdx.app.postRunnable { screen.progressLabel.setText("$progressValue") }
                if (progressValue == 100) coroutineProgress.cancel()
            }
        } }
    }



    fun loadingAssets() {
        if (game.assetManager.update()) {
            onceLoadAssets.once {
                showProgress(1f)
                log("init")
                initAssets()

                game.activity.controller.hideLoader()
                NavigationManager.navigate(GameScreen())
            }
        } else showProgress(game.assetManager.progress)
    }

    fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SourceTexture.BACKGROUND)
            load(game.assetManager)
        }
        with(FontBMPManager) {
            loadListFont = mutableListOf(FontBMPManager.GoldFont.font_200)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.init(game.assetManager)
        FontBMPManager.init(game.assetManager)
    }

    fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.SourceAtlas.values().toMutableList()
            load(game.assetManager)
        }

        with(FontTTFManager) {
            loadListFont = (
                            FontTTFManager.RockwellFont.values +
                            FontTTFManager.AmaranteFont.values +
                            FontTTFManager.NotoSansFont.values
                    ).toMutableList()
            load(game.assetManager)
        }

        with(FontBMPManager) {
            loadListFont = (FontBMPManager.GoldFont.values).toMutableList()
            load(game.assetManager)
        }

        with(MusicManager) {
            loadListMusic = mutableListOf(*MusicManager.EnumMusic.values())
            load(game.assetManager)
        }

        with(SoundManager) {
            loadListSound = mutableListOf(*SoundManager.EnumSound.values())
            load(game.assetManager)
        }
    }

}