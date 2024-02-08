package com.jumping.cubuletus.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.jumping.cubuletus.game.actors.image.AImage
import com.jumping.cubuletus.game.actors.label.ALabelStyle
import com.jumping.cubuletus.game.game
import com.jumping.cubuletus.game.manager.FontTTFManager
import com.jumping.cubuletus.game.manager.NavigationManager
import com.jumping.cubuletus.game.manager.SpriteManager
import com.jumping.cubuletus.game.util.advanced.AdvancedScreen
import com.jumping.cubuletus.game.util.advanced.AdvancedStage
import com.jumping.cubuletus.game.util.runGDX
import com.jumping.cubuletus.util.Once
import com.jumping.cubuletus.util.log
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.jumping.cubuletus.game.util.Layout.Splash as LS

var soundJump: Sound? = null
var bonusJump: Sound? = null

class JumpLiaScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", ALabelStyle.akshar_white_25) }
    private val panelImage    by lazy { AImage(SpriteManager.GameRegion.PANEL.region) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex = Mutex()
    private var progressValue = 0

    private val coroutineUpdateAssets = CoroutineScope(Dispatchers.Main)



    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
       // loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }


    override fun AdvancedStage.addActorsOnStageUI() {
        addPanel()
        addProgress()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPanel() {
        addActor(panelImage)
        panelImage.apply {
            with(LS.panel) { setBounds(x, y, w, h) }
        }
    }

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
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.AksharFont.font_25)
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

 //   private fun loadAssets() {
//        with(SpriteManager) {
//            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
//            loadAtlas(game.assetManager)
//        }
//        with(FontTTFManager) {
//            loadableListFont = (FontTTFManager.AngkorFont.values).toMutableList()
//            load(game.assetManager)
//        }
 //   }

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
            runGDX { progressLabel.setText("$progressValue%") }
            if (progressValue == 100) {
                onceFinishLoadingAssets.once {
                    coroutineUpdateAssets.cancel()
                    runGDX {
                        game.assetManager.finishLoading()
                       // initAssets()

                        game.assetManager.load("funny-running.mp3", Music::class.java)
                        game.assetManager.load("funny-spring-jump.mp3", Sound::class.java)
                        game.assetManager.load("bonus.mp3", Sound::class.java)
                        game.assetManager.finishLoading()
                        val music = game.assetManager["funny-running.mp3", Music::class.java]
                        soundJump = game.assetManager["funny-spring-jump.mp3", Sound::class.java]
                        bonusJump = game.assetManager["bonus.mp3", Sound::class.java]
                        music.apply {
                            isLooping = true
                            volume    = 0.3f
                            play()
                        }

                        NavigationManager.navigate(GameScreen())
                        game.activity.lottie.hideLoader()
                    }
                }
            }
            delay((10..30).shuffled().first().toLong())
        }
    }

   // private fun initAssets() {
//        SpriteManager.initAtlas(game.assetManager)
//        FontTTFManager.init(game.assetManager)
  //  }

}