package com.hgrt.wrld.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.hgrt.wrld.MainActivity
import com.hgrt.wrld.game.actors.label.LabelStyle
import com.hgrt.wrld.game.game
import com.hgrt.wrld.game.manager.FontTTFManager
import com.hgrt.wrld.game.manager.MusicManager
import com.hgrt.wrld.game.manager.MusicUtil
import com.hgrt.wrld.game.manager.NavigationManager
import com.hgrt.wrld.game.manager.SpriteManager
import com.hgrt.wrld.game.util.advanced.AdvancedScreen
import com.hgrt.wrld.game.util.advanced.AdvancedStage
import com.hgrt.wrld.util.Once
import com.hgrt.wrld.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.hgrt.wrld.game.util.Layout.Splash as LS

var musicUtil: MusicUtil? = null
    private set

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.akronimGold_40) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0



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
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.AkronimFont.font_40)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.init(game.assetManager)
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
                            MainActivity.lottie.hideLoader()
                            mg.init()
                            musicUtil = MusicUtil()
                            musicUtil?.apply { music = MUSIC.apply { isLooping = true } }
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