package com.fellinger.yeasman.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.fellinger.yeasman.MainActivity
import com.fellinger.yeasman.game.game
import com.fellinger.yeasman.game.manager.FontTTFManager
import com.fellinger.yeasman.game.manager.NavigationManager
import com.fellinger.yeasman.game.manager.SpriteManager
import com.fellinger.yeasman.game.utils.Once
import com.fellinger.yeasman.game.utils.advanced.AdvancedScreen
import com.fellinger.yeasman.game.utils.advanced.AdvancedStage
import com.fellinger.yeasman.game.utils.runGDX
import com.fellinger.yeasman.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


var error: Sound? = null
var happy_pop: Sound? = null
var proshel: Sound? = null

class Loader : AdvancedScreen() {

    //private val progressLabel by lazy { Label("0", ALabelStyle.roboto_white_40) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex = Mutex()
    private var progressValue = 0

    private val coroutineUpdateAssets = CoroutineScope(Dispatchers.Main)



    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
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
//        addActor(progressLabel)
//        progressLabel.apply {
//            setBounds(LS.progress)
//            setAlignment(Align.center)
//        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
            loadAtlas(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = (FontTTFManager.KottaOneFont.values).toMutableList()
            load(game.assetManager)
        }
    }

    private fun loadingAssets() {
        if (coroutineUpdateAssets.isActive) {
            coroutineUpdateAssets.launch {
                progressMutex.withLock {
                    runGDX { game.assetManager.update() }
                    showProgress(game.assetManager.progress)
                }
            }
        }
    }

    private suspend fun showProgress(progress: Float) {
        while ((progressValue / 100f) < progress) {
            progressValue++
            log("$progressValue%")
           // runGDX { progressLabel.setText("$progressValue%") }
            if (progressValue == 100) {
                onceFinishLoadingAssets.once {
                    coroutineUpdateAssets.cancel()
                    runGDX {
                        game.assetManager.finishLoading()
                        initAssets()

                        game.assetManager.load("ambient-atmosphere-brightest-night.ogg", Music::class.java)
                        game.assetManager.load("error.mp3", Sound::class.java)
                        game.assetManager.load("happy-pop.mp3", Sound::class.java)
                        game.assetManager.load("proshel.mp3", Sound::class.java)
                        game.assetManager.finishLoading()
                        val music = game.assetManager["ambient-atmosphere-brightest-night.ogg", Music::class.java]
                        error     = game.assetManager["error.mp3", Sound::class.java]
                        happy_pop = game.assetManager["happy-pop.mp3", Sound::class.java]
                        proshel   = game.assetManager["proshel.mp3", Sound::class.java]
                        music.apply {
                            isLooping = true
                            volume    = 0.25f
                            play()
                        }

                        NavigationManager.navigate(MenuScreen())
                        MainActivity.lottie.hideLoader()
                    }
                }
            }
            delay((10..15).shuffled().first().toLong())
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

}