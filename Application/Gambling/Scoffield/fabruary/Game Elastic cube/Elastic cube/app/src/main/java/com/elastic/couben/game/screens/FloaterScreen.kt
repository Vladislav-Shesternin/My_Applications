package com.elastic.couben.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.elastic.couben.game.actors.label.ALabelStyle
import com.elastic.couben.game.game
import com.elastic.couben.game.manager.FontTTFManager
import com.elastic.couben.game.manager.NavigationManager
import com.elastic.couben.game.manager.SpriteManager
import com.elastic.couben.game.utils.actor.setBounds
import com.elastic.couben.game.utils.advanced.AdvancedGroup
import com.elastic.couben.game.utils.advanced.AdvancedScreen
import com.elastic.couben.game.utils.runGDX
import com.elastic.couben.utils.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.elastic.couben.game.utils.Layout.Splash as LS

var cartoon_jump: Sound? = null
var knocking: Sound? = null
var message: Sound? = null

class FloaterScreen : AdvancedScreen() {

    private val mutex           = Mutex()
    private var progress        = 0
    private var isFinishLoading = false

    private val logoImage     by lazy { Image(SpriteManager.GameRegion.LOGO.region) }
    private val progressLabel by lazy { Label("", ALabelStyle.style(ALabelStyle.Roboto._40)) }


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
        addLogo()
        addProgress()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addLogo() {
        addActor(logoImage)
        logoImage.apply {
            setBounds(LS.logo)
        }
    }

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
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
            loadAtlas(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.RobotoFont.font_40)
            load(game.assetManager)
        }
        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
//        with(SpriteManager) {
//            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
//            loadAtlas(game.assetManager)
//            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
//            loadTexture(game.assetManager)
//        }
    }

    private fun loadingAssets() {
        if (isFinishLoading.not() && game.assetManager.update(17)) {
            initAssets()
            coroutine.launch { mutex.withLock { while (progress < (game.assetManager.progress * 100)) {
                log("progress - $progress")
                progress += 1
                runGDX { progressLabel.setText("$progress%") }
                if (progress == 100) {
                    isFinishLoading = true

                    game.assetManager.load("better-day.ogg", Music::class.java)
                    game.assetManager.load("cartoon-jump.mp3", Sound::class.java)
                    game.assetManager.load("knocking.mp3", Sound::class.java)
                    game.assetManager.load("message.mp3", Sound::class.java)
                    game.assetManager.finishLoading()
                    val music = game.assetManager["better-day.ogg", Music::class.java]
                    cartoon_jump = game.assetManager["cartoon-jump.mp3", Sound::class.java]
                    knocking = game.assetManager["knocking.mp3", Sound::class.java]
                    message = game.assetManager["message.mp3", Sound::class.java]
                    music.apply {
                        isLooping = true
                        volume    = 0.3f
                        play()
                    }

                    NavigationManager.navigate(GameScreen())
                }
                delay((9..12).shuffled().first().toLong())
            } } }
        }
    }

    private fun initAssets() {
//        SpriteManager.initAtlas(game.assetManager)
//        SpriteManager.initTexture(game.assetManager)
//        FontTTFManager.init(game.assetManager)
    }



}