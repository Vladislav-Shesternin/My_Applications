package com.aztec.blaze.game.screens

import com.aztec.blaze.MainActivity
import com.aztec.blaze.game.actors.label.LabelStyle
import com.aztec.blaze.game.game
import com.aztec.blaze.game.manager.FontTTFManager
import com.aztec.blaze.game.manager.NavigationManager
import com.aztec.blaze.game.manager.SpriteManager
import com.aztec.blaze.game.util.advanced.AdvancedScreen
import com.aztec.blaze.game.util.advanced.AdvancedStage
import com.aztec.blaze.util.Once
import com.aztec.blaze.util.log
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.aztec.blaze.game.util.Layout.Splash as LS

class SplashkaScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.akronimGold_40) }

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
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
            load(com.aztec.blaze.game.game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.AkronimFont.font_40)
            load(com.aztec.blaze.game.game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.init(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            load(com.aztec.blaze.game.game.assetManager)
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
                            MainActivity.lottie.hideLoader()
                            NavigationManager.navigate(GameScreen())
                        }
                    }
                    delay((35..60L).random())
                }
            }
        }
    }

    private fun initAssets() {
        SpriteManager.init(game.assetManager)
    }

}