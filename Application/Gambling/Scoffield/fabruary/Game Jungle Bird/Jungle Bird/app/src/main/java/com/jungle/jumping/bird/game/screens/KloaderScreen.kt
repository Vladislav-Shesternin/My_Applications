package com.jungle.jumping.bird.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.jungle.jumping.bird.game.actors.label.ALabelStyle
import com.jungle.jumping.bird.game.game
import com.jungle.jumping.bird.game.manager.FontTTFManager
import com.jungle.jumping.bird.game.manager.NavigationManager
import com.jungle.jumping.bird.game.manager.SpriteManager
import com.jungle.jumping.bird.game.utils.Once
import com.jungle.jumping.bird.game.utils.actor.setBounds
import com.jungle.jumping.bird.game.utils.advanced.AdvancedScreen
import com.jungle.jumping.bird.game.utils.advanced.AdvancedStage
import com.jungle.jumping.bird.game.utils.runGDX
import com.jungle.jumping.bird.utils.log
import kotlinx.coroutines.*
import com.jungle.jumping.bird.game.utils.Layout.Splash as LS

var bonus: Sound? = null
var fail: Sound? = null
var swing: Sound? = null

class KloaderScreen : AdvancedScreen() {

    private val panelImage    by lazy { Image(SpriteManager.GameRegion.PDFKA.region) }
    private val progressLabel by lazy { Label("0", ALabelStyle.font_45) }

    private val onceFinishLoadingAssets = Once()
    private var progressValue = 0



    override fun show() {
        loadSplashAssets()
        setBackBackground(SpriteManager.GameRegion.BACKGROUND.region)
        super.show()
        coroutine.launch { showProgress(1f) }
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
        panelImage.setBounds(LS.button)
    }

    private fun AdvancedStage.addProgress() {
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
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = (FontTTFManager.AladinFont.values).toMutableList()
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private suspend fun showProgress(progress: Float) {
        while ((progressValue / 100f) < progress) {
            progressValue++
            log("$progressValue%")
            runGDX { progressLabel.setText("$progressValue%") }
            if (progressValue == 100) onceFinishLoadingAssets.once { runGDX {

                game.assetManager.load("jungle-music.ogg", Music::class.java)
                game.assetManager.load("bonus.mp3", Sound::class.java)
                game.assetManager.load("fail.mp3", Sound::class.java)
                game.assetManager.load("swing.mp3", Sound::class.java)
                game.assetManager.finishLoading()
                val music = game.assetManager["jungle-music.ogg", Music::class.java]
                bonus = game.assetManager["bonus.mp3", Sound::class.java]
                fail  = game.assetManager["fail.mp3", Sound::class.java]
                swing = game.assetManager["swing.mp3", Sound::class.java]
                music.apply {
                    isLooping = true
                    volume    = 0.25f
                    play()
                }

                NavigationManager.navigate(MenuScreen())
            } }
            delay((10..15).shuffled().first().toLong())
        }
    }

}