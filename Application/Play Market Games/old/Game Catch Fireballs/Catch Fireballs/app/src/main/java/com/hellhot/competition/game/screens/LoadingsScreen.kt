package com.hellhot.competition.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.hellhot.competition.Once
import com.hellhot.competition.game.actors.label.ALabelStyle
import com.hellhot.competition.game.game
import com.hellhot.competition.game.manager.FontTTFManager
import com.hellhot.competition.game.manager.NavigationManager
import com.hellhot.competition.game.manager.SpriteManager
import com.hellhot.competition.game.util.advanced.AdvancedScreen
import com.hellhot.competition.game.util.advanced.AdvancedStage
import com.hellhot.competition.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.hellhot.competition.game.util.Layout.Splash as LS

var soundTake: Sound? = null
var soundUdar: Sound? = null

class LoadingsScreen : AdvancedScreen(1800f, 808f) {

    private val progressLabel by lazy { Label("0", ALabelStyle.fontRed_120) }

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
//        with(SpriteManager) {
//            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas._1)
//            load(game.assetManager)
//        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.Font.font_120)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

      //  SpriteManager.init(game.assetManager)
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
        coroutine.launch {
            progressMutex.withLock {
                while ((progressValue / 100f) < progress) {
                    progressValue++
                    log("$progressValue%")
                    Gdx.app.postRunnable {
                        progressLabel.setText("$progressValue%")

                        if (progressValue == 100) {
                            onceFinishLoadingAssets.once {
                                cancel()
                                initAssets()


                                game.assetManager.load("infernal.mp3", Music::class.java)

                                game.assetManager.load("take.mp3", Sound::class.java)
                                game.assetManager.load("udar.mp3", Sound::class.java)

                                game.assetManager.finishLoading()

                                val music = game.assetManager["infernal.mp3", Music::class.java]
                                music.apply {
                                    isLooping = true
                                    volume = 0.3f
                                    play()
                                }

                                soundTake = game.assetManager["take.mp3", Sound::class.java]
                                soundUdar = game.assetManager["udar.mp3", Sound::class.java]

                                game.activity.lottie.hideLoader()
                                NavigationManager.navigate(GameScreen())
                            }
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