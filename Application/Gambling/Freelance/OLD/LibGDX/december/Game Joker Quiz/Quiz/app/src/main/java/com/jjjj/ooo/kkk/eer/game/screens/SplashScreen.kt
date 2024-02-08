package com.jjjj.ooo.kkk.eer.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.jjjj.ooo.kkk.eer.MainActivity
import com.jjjj.ooo.kkk.eer.game.actors.label.LabelStyle
import com.jjjj.ooo.kkk.eer.game.game
import com.jjjj.ooo.kkk.eer.game.manager.FontTTFManager
import com.jjjj.ooo.kkk.eer.game.manager.NavigationManager
import com.jjjj.ooo.kkk.eer.game.manager.SpriteManager
import com.jjjj.ooo.kkk.eer.game.util.advanced.AdvancedScreen
import com.jjjj.ooo.kkk.eer.game.util.advanced.AdvancedStage
import com.jjjj.ooo.kkk.eer.util.Once
import com.jjjj.ooo.kkk.eer.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.jjjj.ooo.kkk.eer.game.util.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.fantik) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0



    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
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
        with(FontTTFManager) {
            loadableListFont = (FontTTFManager.OpenSansBold.values).toMutableList()
            load(game.assetManager)
        }
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            load(game.assetManager)
        }
        game.assetManager.finishLoading()

        FontTTFManager.init(game.assetManager)
        SpriteManager.init(game.assetManager)
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
                            MainActivity.lottie.hideLoader()
                            NavigationManager.navigate(GameScreen())
                        }
                    }
                    delay(10)
                }
            }
        }
    }

}