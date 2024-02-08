package com.csmttt.medus.play.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.csmttt.medus.play.MainActivity
import com.csmttt.medus.play.game.actors.label.ALabelStyle
import com.csmttt.medus.play.game.game
import com.csmttt.medus.play.game.manager.FontTTFManager
import com.csmttt.medus.play.game.manager.NavigationManager
import com.csmttt.medus.play.game.manager.SpriteManager
import com.csmttt.medus.play.game.util.advanced.AdvancedScreen
import com.csmttt.medus.play.game.util.advanced.AdvancedStage
import com.csmttt.medus.play.util.Once
import com.csmttt.medus.play.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.csmttt.medus.play.game.util.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen(1800f, 800f) {

    private val progressLabel by lazy { Label("0", ALabelStyle.fontWhite_141) }
    private val ledyImage     by lazy { Image(SpriteManager.SplashRegion.LOGO_LEDY.region) }

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


    override fun AdvancedStage.addActorsOnStageUI() {
        addLedy()
        addProgress()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addLedy() {
        addActor(ledyImage)
        ledyImage.apply {
            with(LS.ledy) { setBounds(x, y, w, h) }
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
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas._1)
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.Font.font_141)
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
                                MainActivity.lottie.hideLoader()
                                NavigationManager.navigate(GameScreen())
                            }
                        }
                    }
                    delay(10)
                }
            }
        }
    }

    private fun initAssets() {
        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

}