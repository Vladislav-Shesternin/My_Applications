package com.wlfe.astiener.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.wlfe.astiener.MainActivity
import com.wlfe.astiener.game.actors.label.LabelStyle
import com.wlfe.astiener.game.game
import com.wlfe.astiener.game.manager.FontTTFManager
import com.wlfe.astiener.game.manager.MusicManager
import com.wlfe.astiener.game.manager.MusicUtil
import com.wlfe.astiener.game.manager.NavigationManager
import com.wlfe.astiener.game.manager.SpriteManager
import com.wlfe.astiener.game.util.advanced.AdvancedScreen
import com.wlfe.astiener.game.util.advanced.AdvancedStage
import com.wlfe.astiener.util.Once
import com.wlfe.astiener.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.wlfe.astiener.game.util.Layout.Splash as LS


var musicUtil: MusicUtil? = null
    private set

class LoaderScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.akronimWhite_50) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0



    override fun show() {
        loadSplashAssets()
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
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.AkronimFont.font_50)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        FontTTFManager.init(game.assetManager)
    }
    private val mg = MusicManager(game.assetManager)

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            load(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            load(game.assetManager)
        }
        with(mg) {
            loadableMusicList = MusicManager.EnumMusic.values().map { it.data }.toMutableList()
            load()
        }
    }

    private fun loadingAssets() {
        game.assetManager.update()
        showProgress(game.assetManager.progress)
    }

    private fun showProgress(progress: Float) {
        coroutineMain.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    log("$progressValue%")
                    Gdx.app.postRunnable { progressLabel.setText("$progressValue%") }
                    if (progressValue == 100) {
                        onceFinishLoadingAssets.once {
                            cancel()
                            initAssets()
                            mg.init()
                            musicUtil = MusicUtil()
                            musicUtil?.apply { music = MUSIC.apply { isLooping = true } }
                            MainActivity.lottie.hideLoader()
                            NavigationManager.navigate(MenuScreen())
                        }
                    }
                    delay(10)
                }
            }
        }
    }

    private fun initAssets() {
        SpriteManager.init(game.assetManager)
    }

}