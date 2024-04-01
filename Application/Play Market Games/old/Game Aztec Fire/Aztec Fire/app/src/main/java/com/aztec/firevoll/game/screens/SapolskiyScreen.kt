package com.aztec.firevoll.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.aztec.firevoll.MainActivity
import com.aztec.firevoll.game.actors.label.LabelStyle
import com.aztec.firevoll.game.game
import com.aztec.firevoll.game.manager.FontTTFManager
import com.aztec.firevoll.game.manager.NavigationManager
import com.aztec.firevoll.game.manager.SpriteManager
import com.aztec.firevoll.game.util.advanced.AdvancedScreen
import com.aztec.firevoll.game.util.advanced.AdvancedStage
import com.aztec.firevoll.Once
import com.aztec.firevoll.log
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.aztec.firevoll.game.util.Layout.Splash as LS

var soundik: Sound? = null

class SapolskiyScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.akronimRed_50) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0



    override fun show() {
        loadSplashAssets()
//        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
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
//        with(SpriteManager) {
//            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
//            load(game.assetManager)
//        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.AkronimFont.font_50)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

       // SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            load(game.assetManager)
        }
    }

    private fun loadingAssets() {
        game.assetManager.update()
        showProgress(game.assetManager.progress)
    }

    private fun showProgress(progress: Float) {
        coroutine.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    log("$progressValue%")
                    Gdx.app.postRunnable { progressLabel.setText("$progressValue%") }
                    if (progressValue == 100) {
                        onceFinishLoadingAssets.once {
                            cancel()
                            initAssets()
                            game.activity.lottie.hideLoader()

                            game.assetManager.load("african-rhythm-africa.mp3", Music::class.java)
                            game.assetManager.load("goodresult.mp3", Sound::class.java)
                            game.assetManager.finishLoading()
                            val music = game.assetManager["african-rhythm-africa.mp3", Music::class.java]
                            soundik = game.assetManager["goodresult.mp3", Sound::class.java]
                            music.apply {
                                isLooping = true
                                play()
                            }

                            NavigationManager.navigate(GameScreen())
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