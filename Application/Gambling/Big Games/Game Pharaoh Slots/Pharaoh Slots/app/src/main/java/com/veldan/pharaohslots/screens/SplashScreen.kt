package com.veldan.pharaohslots.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.pharaohslots.AndroidLauncher
import com.veldan.pharaohslots.advanced.AdvancedScreen
import com.veldan.pharaohslots.advanced.AdvancedStage
import com.veldan.pharaohslots.assetManager
import com.veldan.pharaohslots.assets.*
import com.veldan.pharaohslots.languageSprite
import com.veldan.pharaohslots.manager.LanguageManager
import com.veldan.pharaohslots.manager.NavigationManager
import com.veldan.pharaohslots.utils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)

    private val coroutineProgress = CoroutineScope(Dispatchers.Default)

    private val onceLoadAssets = Once()

    private val progressLabel by lazy { Label("0", Label.LabelStyle(FontBMPManager.EnumFont.PROGRESS.data.font, null)) }

    private var progressValue = 0
    private val progressMutex = Mutex()



    override fun show() {
        super.show()
        AndroidLauncher.showLoader()
        loadSplashAssets()
        background = SpriteManager.SplashSprite.BACKGROUND.data.texture
        stage.addActorsOnStage()
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        if (assetManager.update()) onceLoadAssets.once {
            showProgress(1f)
            initAssets()
            languageSprite = LanguageManager.languageSprite
            AndroidLauncher.hideLoader()
            NavigationManager.navigate(MenuScreen())
        }
        else showProgress(assetManager.progress)
    }

    override fun dispose() {
        super.dispose()
        cancelCoroutinesAll(coroutineProgress)
    }



    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadListSprite = mutableListOf(*SpriteManager.SplashSprite.values())
            load(assetManager)
        }
        with(FontBMPManager) {
            loadListFont = mutableListOf(FontBMPManager.EnumFont.PROGRESS)
            load(assetManager)
        }

        assetManager.finishLoading()

        SpriteManager.init(assetManager)
        FontBMPManager.init(assetManager)
    }



    private fun loadAssets() {

        with(SpriteManager) {
            loadListSprite = mutableListOf(
                *SpriteManager.MenuSprite.values(),
                *SpriteManager.OptionsSprite.values(),
                *SpriteManager.GameSprite.values(),

                *SpriteManager.MenuUKSprite.values(),
                *SpriteManager.MenuRUSprite.values(),
                *SpriteManager.MenuUSSprite.values(),

                *SpriteManager.GameRUSprite.values(),
                *SpriteManager.GameUKSprite.values(),
                *SpriteManager.GameUSSprite.values(),
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

    private fun initAssets() {
        SpriteManager.init(assetManager)
        FontTTFManager.init(assetManager)
        MusicManager.init(assetManager)
        SoundManager.init(assetManager)
    }



    private fun showProgress(progress: Float) {
        coroutineProgress.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    log("$progressValue%")
                    Gdx.app.postRunnable { progressLabel.setText("$progressValue") }
                    if (progressValue == 100) cancel()
                }
            }
        }
    }



    private fun AdvancedStage.addActorsOnStage() {
        addProgressPanel()
        addProgress()
    }


    private fun AdvancedStage.addProgressPanel() {
        val image = Image(SpriteManager.SplashSprite.PROGRESS_PANEL.data.texture).apply {
            setBoundsFigmaY(Splash.PROGRESS_PANEL_X, Splash.PROGRESS_PANEL_Y, Splash.PROGRESS_PANEL_W, Splash.PROGRESS_PANEL_H)
        }
        addActor(image)
    }

    private fun AdvancedStage.addProgress() {
        progressLabel.apply {
            setAlignment(Align.center)
            setBoundsFigmaY(Splash.PROGRESS_X, Splash.PROGRESS_Y, Splash.PROGRESS_W, Splash.PROGRESS_H)
        }
        addActor(progressLabel)
    }


}