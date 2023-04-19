package com.veldan.fantasticslots.screens.splash

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Disposable
import com.veldan.fantasticslots.assetManager
import com.veldan.fantasticslots.assets.*
import com.veldan.fantasticslots.utils.Once
import com.veldan.fantasticslots.utils.controller.ScreenController
import com.veldan.fantasticslots.utils.cancelCoroutinesAll
import com.veldan.fantasticslots.utils.log
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

    val coroutineProgress = CoroutineScope(Dispatchers.Default)
    val onceLoadAssets = Once()
    var progressValue = 0
    val progressMutex = Mutex()



    override fun dispose() {
        cancelCoroutinesAll(coroutineProgress)
    }



    fun loadSplashAssets() {
        with(SpriteManager) {
            loadListSprite = mutableListOf(*SpriteManager.SplashSprite.values())
            load(assetManager)
        }
        with(FontBMPManager) {
            loadListFont = mutableListOf(FontBMPManager.EnumFont.LUCIDA_110)
            load(assetManager)
        }

        assetManager.finishLoading()

        SpriteManager.init(assetManager)
        FontBMPManager.init(assetManager)
    }



    fun loadAssets() {

        with(SpriteManager) {
            loadListSprite = mutableListOf(
                *SpriteManager.MenuSprite.values(),
                *SpriteManager.OptionsSprite.values(),
                *SpriteManager.GameSprite.values(),
            )
            loadListSpriteList = mutableListOf(
                *SpriteManager.SlotItemSpriteList.values(),
            )
            load(assetManager)
        }

        with(FontTTFManager) {
            loadListFont = mutableListOf(*FontTTFManager.EnumFont.values())
            load(assetManager)
        }

        with(MusicManager) {
            loadListMusic = mutableListOf(*MusicManager.EnumMusic.values())
            load(assetManager)
        }

        with(SoundManager) {
            loadListSound = mutableListOf(*SoundManager.EnumSound.values())
            load(assetManager)
        }
    }

    fun initAssets() {
        SpriteManager.init(assetManager)
        FontTTFManager.init(assetManager)
        MusicManager.init(assetManager)
        SoundManager.init(assetManager)
    }



    fun showProgress(progress: Float) {
        coroutineProgress.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    log("$progressValue%")
                    Gdx.app.postRunnable { screen.progressLabel.setText("$progressValue") }
                    if (progressValue == 100) cancel()
                }
            }
        }
    }

}