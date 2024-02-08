package sinet.startup.indrive.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import sinet.startup.indrive.MainActivity
import sinet.startup.indrive.game.actors.label.LabelStyle
import sinet.startup.indrive.game.game
import sinet.startup.indrive.game.manager.FontTTFManager
import sinet.startup.indrive.game.manager.NavigationManager
import sinet.startup.indrive.game.manager.SpriteManager
import sinet.startup.indrive.game.util.advanced.AdvancedScreen
import sinet.startup.indrive.game.util.advanced.AdvancedStage
import sinet.startup.indrive.util.Once
import sinet.startup.indrive.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import sinet.startup.indrive.game.util.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.akronimGold_60) }

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
            loadableListFont = mutableListOf(FontTTFManager.AkronimFont.font_60)
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