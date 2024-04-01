package com.riseofgiza.velsolution.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.riseofgiza.velsolution.MainActivity
import com.riseofgiza.velsolution.game.actors.button.FontTTFManager
import com.riseofgiza.velsolution.game.actors.label.LabelStyle
import com.riseofgiza.velsolution.game.game
import com.riseofgiza.velsolution.game.manager.SpriteManager
import com.riseofgiza.velsolution.game.util.advanced.AdvancedScreen
import com.riseofgiza.velsolution.game.util.advanced.AdvancedStage
import com.riseofgiza.velsolution.util.Once
import com.riseofgiza.velsolution.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.riseofgiza.velsolution.game.util.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    object NavigationManager {

        private val backStack = mutableListOf<Screen>()
        var key: Int? = null
            private set

        fun navigate(to: Screen, from: Screen? = null, key: Int? = null) {
            Gdx.app.postRunnable {
                NavigationManager.key = key

                game.screen = to
                from?.let { backStack.add(it) }
                if (backStack.contains(to)) backStack.remove(to)
            }
        }

        fun back(key: Int? = null) {
            Gdx.app.postRunnable {
                NavigationManager.key = key

                if (backStack.isEmpty()) exit()
                else game.screen = backStack.removeLast()
            }
        }

        fun exit() {
            Gdx.app.postRunnable {
                backStack.clear()
                game.dispose()
                Gdx.app.exit()
            }
        }

    }

    private val progressLabel by lazy { Label("0", LabelStyle.akronimGold_20) }

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
            load(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.AkronimFont.font_80)
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
            loadableListFont = FontTTFManager.AkronimFont.values.toMutableList()
            load(game.assetManager)
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
                    delay(10)
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