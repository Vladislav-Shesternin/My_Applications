package com.mesga.moolahit.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.mesga.moolahit.MainActivity
import com.mesga.moolahit.Once
import com.mesga.moolahit.game.actors.label.ALabelStyle
import com.mesga.moolahit.game.game
import com.mesga.moolahit.game.manager.FontTTFManager
import com.mesga.moolahit.game.manager.NavigationManager
import com.mesga.moolahit.game.manager.SpriteManager
import com.mesga.moolahit.game.util.advanced.AdvancedScreen
import com.mesga.moolahit.game.util.advanced.AdvancedStage
import com.mesga.moolahit.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.mesga.moolahit.game.util.Layout.Splash as LS


var bonus: Sound? = null
    private set
var plus: Sound? = null
    private set
var win: Sound? = null
    private set

class FirsterScreen : AdvancedScreen(1280f, 720f) {

    private val progressLabel by lazy { Label("0", ALabelStyle.fontGreen_100) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0



    override fun show() {
        loadSplashAssets()
       // setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }


    override fun AdvancedStage.addActorsOnStageUI() {
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
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas._1)
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.Font.font_100)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = FontTTFManager.Font.values.toMutableList()
            load(game.assetManager)
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

                            game.assetManager.load("the-rhythm-of-the-africa.mp3", Music::class.java)

                            game.assetManager.load("bonus.mp3", Sound::class.java)
                            game.assetManager.load("plus.mp3", Sound::class.java)
                            game.assetManager.load("win.mp3", Sound::class.java)

                            game.assetManager.finishLoading()

                            val music = game.assetManager["the-rhythm-of-the-africa.mp3", Music::class.java]
                            music.apply {
                                isLooping = true
                                volume = 0.1f
                                play()
                            }

                            bonus = game.assetManager["bonus.mp3", Sound::class.java]
                            plus  = game.assetManager["plus.mp3", Sound::class.java]
                            win   = game.assetManager["win.mp3", Sound::class.java]


                            game.activity.lottie.hideLoader()
                            NavigationManager.navigate(GameScreen())
                        }
                    }
                    delay(5)
                }
            }
        }
    }

    private fun initAssets() {
        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

}