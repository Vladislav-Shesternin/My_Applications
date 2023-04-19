package com.veldan.junglego.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.junglego.*
import com.veldan.junglego.advanced.AdvancedScreen
import com.veldan.junglego.advanced.AdvancedStage
import com.veldan.junglego.assets.FontManager
import com.veldan.junglego.assets.MusicManager
import com.veldan.junglego.assets.SoundManager
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.manager.NavigationManager
import com.veldan.junglego.utils.*
import com.veldan.junglego.manager.LanguageManager
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    private val coroutineProgress = CoroutineScope(Dispatchers.Default)
    private val coroutineInitAssets = CoroutineScope(Dispatchers.Default)

    private val onceLoadAssets = Once()

    private val progressLabel by lazy { Label("0%", Label.LabelStyle(FontManager.EnumFont.RATIONALE_85.fontData.font, Color.BLACK)) }

    private var progressValue = 0
    private val progressMutex = Mutex()



    override fun show() {
        super.show()
        showLoader()
        loadSplashAssets()
        background = SpriteManager.SplashSprite.BACKGROUND.textureData.texture
        stage.addActorsOnStage()
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        if (assetManager.update()) { onceLoadAssets.once { coroutineInitAssets.launch {
            initAssets()
            log("INIT ASSETS")
            delay(1f.toDelay)
            languageSprite = LanguageManager.languageSprite
            showProgress(1f)
        } } } else showProgress(assetManager.progress)
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineProgress, coroutineInitAssets)
    }



    private fun loadSplashAssets() {
        SpriteManager.apply {
            loadListSprite = mutableListOf(*SpriteManager.SplashSprite.values())
            load(assetManager)
        }
        FontManager.apply {
            loadListFont = mutableListOf(FontManager.EnumFont.RATIONALE_85)
            load(assetManager)
        }

        assetManager.finishLoading()

        SpriteManager.init(assetManager)
        FontManager.init(assetManager)
    }



    private fun loadAssets() {
        with(SpriteManager) {
            loadListSprite = mutableListOf(
                *SpriteManager.MenuSprite.values(),
                *SpriteManager.OptionsSprite.values(),
                *SpriteManager.GameSprite.values(),

                *SpriteManager.MenuRUSprite.values(),
                *SpriteManager.MenuUKSprite.values(),
                *SpriteManager.MenuUSSprite.values(),

                *SpriteManager.GameRUSprite.values(),
                *SpriteManager.GameUKSprite.values(),
                *SpriteManager.GameUSSprite.values(),
            )
            loadListSpriteList = mutableListOf(
                *SpriteManager.AnimationSpriteList.values(),
                *SpriteManager.SlotItemSpriteList.values(),
                *SpriteManager.PriceSpriteList.values(),
            )
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

        with(FontManager) {
            loadListFont = mutableListOf(*FontManager.EnumFont.values())
            load(assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.init(assetManager)
        MusicManager.init(assetManager)
        SoundManager.init(assetManager)
        FontManager.init(assetManager)
    }



    private fun showProgress(progress: Float) {
        coroutineProgress.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    Gdx.app.postRunnable { progressLabel.setText("$progressValue%") }
                    log("progress: $progressValue%")
                    delay(20)
                    if (progressValue == 100) {
                        log("progress: 100% - GO TO MENU")
                        hideLoader()
                        NavigationManager.navigate(MenuScreen())
                        cancel()
                    }
                }
            }
        }
    }



    private fun AdvancedStage.addActorsOnStage() {
        addProgress()
    }


    private fun AdvancedStage.addProgress() {
        val label = progressLabel.apply {
            setAlignment(Align.center)
            setBoundsFigmaY(Splash.PROGRESS_X, Splash.PROGRESS_Y, Splash.PROGRESS_W, Splash.PROGRESS_H)
        }
        addActor(label)
    }


}