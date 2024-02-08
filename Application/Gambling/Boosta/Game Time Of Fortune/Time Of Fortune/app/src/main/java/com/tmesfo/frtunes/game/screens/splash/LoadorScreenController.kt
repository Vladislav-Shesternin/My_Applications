package com.tmesfo.frtunes.game.screens.splash

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Disposable
import com.tmesfo.frtunes.game.game
import com.tmesfo.frtunes.game.manager.NavigationManager
import com.tmesfo.frtunes.game.manager.assets.FontTTFManager
import com.tmesfo.frtunes.game.manager.assets.MusicManager
import com.tmesfo.frtunes.game.manager.assets.SoundManager
import com.tmesfo.frtunes.game.manager.assets.SpriteManager
import com.tmesfo.frtunes.game.screens.menu.MenuScreen
import com.tmesfo.frtunes.game.utils.Once
import com.tmesfo.frtunes.game.utils.controller.ScreenController
import com.tmesfo.frtunes.lottie
import com.tmesfo.frtunes.util.cancelCoroutinesAll
import com.tmesfo.frtunes.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class LoadorScreenController(
    override val screen: LoadorScreen
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
            MusicManager.init(assetManager)
            SoundManager.init(assetManager)
        }
    }

    private fun showProgress(progress: Float) {
        coroutineProgress.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    log("$progressValue%")
                    Gdx.app.postRunnable { screen.progressLabel.setText("$progressValue") }
                    if (progressValue == 100) coroutineProgress.cancel()
                }
            }
        }
    }



    fun loadingAssets() {
        if (game.assetManager.update()) {
            onceLoadAssets.once {
                showProgress(1f)
                log("init")
                initAssets()

                lottie.hideLoader()
                NavigationManager.navigate(MenuScreen())
            }
        } else showProgress(game.assetManager.progress)
    }

    fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.SourceTexture.BACKGROUND)
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadListFont = mutableListOf(FontTTFManager.AbrilFatfaceFont.font_80)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.SourceAtlas.values().toMutableList()
            load(game.assetManager)
        }

        with(FontTTFManager) {
            loadListFont = (
                    FontTTFManager.AbrilFatfaceFont.values +
                    FontTTFManager.NotoSansFont.values
                    ).toMutableList()
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