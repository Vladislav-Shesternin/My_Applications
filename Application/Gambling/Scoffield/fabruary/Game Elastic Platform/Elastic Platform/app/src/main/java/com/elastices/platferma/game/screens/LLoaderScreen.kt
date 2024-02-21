package com.elastices.platferma.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.elastices.platferma.game.actors.label.ALabelStyle
import com.elastices.platferma.game.game
import com.elastices.platferma.game.manager.FontTTFManager
import com.elastices.platferma.game.manager.NavigationManager
import com.elastices.platferma.game.manager.SpriteManager
import com.elastices.platferma.game.utils.actor.setBounds
import com.elastices.platferma.game.utils.advanced.AdvancedGroup
import com.elastices.platferma.game.utils.advanced.AdvancedScreen
import com.elastices.platferma.game.utils.runGDX
import com.elastices.platferma.utils.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.elastices.platferma.game.utils.Layout.Splash as LS

var metalSound: Sound? = null

class LLoaderScreen : AdvancedScreen() {

    private val mutex           = Mutex()
    private var progress        = 0
    private var isFinishLoading = false

    private val progressLabel by lazy { Label("", ALabelStyle.style(ALabelStyle.Roboto._48)) }


    override fun show() {
        loadSplashAssets()
        super.show()
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }


    override fun AdvancedGroup.addActorsOnGroup() {
        addProgress()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.progress)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(SpriteManager) {
//            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
//            loadAtlas(game.assetManager)
//        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.RobotoFont.font_48)
            load(com.elastices.platferma.game.game.assetManager)
        }
        game.assetManager.finishLoading()

//        SpriteManager.initAtlas(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
            loadAtlas(com.elastices.platferma.game.game.assetManager)
        }
//        with(SpriteManager) {
//            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
//            loadAtlas(game.assetManager)
//            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
//            loadTexture(game.assetManager)
//        }
    }

    private fun loadingAssets() {
        if (isFinishLoading.not() && game.assetManager.update(16)) {
            initAssets()
            coroutine.launch { mutex.withLock { while (progress < (game.assetManager.progress * 100)) {
                log("progress - $progress")
                progress += 1
                runGDX { progressLabel.setText("$progress%") }
                if (progress == 100) {
                   // MainActivity.lottie.hideLoader()
                    isFinishLoading = true

                    game.assetManager.load("fire-cyberpunk-sport-music.ogg", Music::class.java)
                    game.assetManager.load("fx-metal-hit.mp3", Sound::class.java)
                    game.assetManager.finishLoading()
                    val music  = game.assetManager["fire-cyberpunk-sport-music.ogg", Music::class.java]
                    metalSound = game.assetManager["fx-metal-hit.mp3", Sound::class.java]
                    music.apply {
                        isLooping = true
                        volume    = 0.25f
                        play()
                    }

                    NavigationManager.navigate(GameScreen())
                }
                delay((8..12).shuffled().first().toLong())
            } } }
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
//        SpriteManager.initTexture(game.assetManager)
//        FontTTFManager.init(game.assetManager)
    }



}