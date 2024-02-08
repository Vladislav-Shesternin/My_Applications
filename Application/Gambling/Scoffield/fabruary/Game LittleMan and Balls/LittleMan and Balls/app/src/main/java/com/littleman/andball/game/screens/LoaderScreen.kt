package com.littleman.andball.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.littleman.andball.game.actors.label.LabelStyle
import com.littleman.andball.game.game
import com.littleman.andball.game.manager.FontTTFManager
import com.littleman.andball.game.manager.NavigationManager
import com.littleman.andball.game.manager.SpriteManager
import com.littleman.andball.game.util.advanced.AdvancedScreen
import com.littleman.andball.game.util.advanced.AdvancedStage
import com.littleman.andball.util.Once
import com.littleman.andball.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.littleman.andball.game.util.Layout.Splash as LS

class LoaderScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.fanta_45) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0



    override fun show() {
        loadSplashAssets()
        //setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
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
//            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
//            load(game.assetManager)
//        }
        with(FontTTFManager) {
            loadableListFont = FontTTFManager.FantaFont.values.toMutableList()
            load(game.assetManager)
        }

        game.assetManager.finishLoading()

        //SpriteManager.init(game.assetManager)
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