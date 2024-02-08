package com.book.of.dead.deluxe.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.book.of.dead.deluxe.Once
import com.book.of.dead.deluxe.game.actors.label.LabelStyle
import com.book.of.dead.deluxe.game.game
import com.book.of.dead.deluxe.game.manager.FontTTFManager
import com.book.of.dead.deluxe.game.manager.NavigationManager
import com.book.of.dead.deluxe.game.manager.SpriteManager
import com.book.of.dead.deluxe.game.util.advanced.AdvancedScreen
import com.book.of.dead.deluxe.game.util.advanced.AdvancedStage
import com.book.of.dead.deluxe.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.book.of.dead.deluxe.game.util.Layout.Splash as LS

class PreloaderScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.akronimGold_60) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0



    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND_1.region)
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
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas._2)
            load(com.book.of.dead.deluxe.game.game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.AkronimFont.font_60)
            load(com.book.of.dead.deluxe.game.game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            load(com.book.of.dead.deluxe.game.game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = FontTTFManager.AkronimFont.values.toMutableList()
            load(com.book.of.dead.deluxe.game.game.assetManager)
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
                            game.activity.lottie.hideLoader()
                            NavigationManager.navigate(GameScreen())

                            game.assetManager.load("arabic.mp3", Music::class.java)
                            game.assetManager.finishLoading()
                            val music = game.assetManager["arabic.mp3", Music::class.java]
                            music.apply {
                                isLooping = true
                                play()
                            }

                        }
                    }
                    delay(20)
                    //delay((30..50L).random())
                }
            }
        }
    }

    private fun initAssets() {
        with(game) {
            SpriteManager.init(assetManager)
            FontTTFManager.init(assetManager)
        }
    }

}