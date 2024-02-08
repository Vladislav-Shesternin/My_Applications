package com.egyptian.rebirth.dop_papka

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.egyptian.rebirth.MainActivity
import com.egyptian.rebirth.Once
import com.egyptian.rebirth.gremmy.actors.label.LabelStyle
import com.egyptian.rebirth.gremmy.manager.FontTTFManager
import com.egyptian.rebirth.gremmy.NavigationManager
import com.egyptian.rebirth.gremmy.manager.SpriteManager
import com.egyptian.rebirth.gremmy.util.advanced.AdvancedScreen
import com.egyptian.rebirth.gremmy.util.advanced.AdvancedStage
import com.egyptian.rebirth.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.egyptian.rebirth.gremmy.util.Layout.Splash as LS

class OttofonSphScr : AdvancedScreen() {

    private val progressLabel by lazy { Label("0", LabelStyle.akronimWhite_65) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex           = Mutex()
    private var progressValue           = 0



    override fun show() {
        loadSplashAssets()
       // setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
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
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas._1)
            load(gamed.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = FontTTFManager.AkronimFont.values.toMutableList()
            load(gamed.assetManager)
        }

        gamed.assetManager.finishLoading()

        SpriteManager.init(gamed.assetManager)
        FontTTFManager.init(gamed.assetManager)
    }

    private fun loadAssets() {
//        with(SpriteManager) {
//            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
//            load(game.assetManager)
//        }
    }

    private fun loadingAssets() {
        gamed.assetManager.update()
        showProgress(gamed.assetManager.progress)
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
                            gamed.activity.lottie.hideLoader()

                            gamed.assetManager.load("ancient.mp3", Music::class.java)
                            gamed.assetManager.finishLoading()
                            val music = gamed.assetManager["ancient.mp3", Music::class.java]
                            music.apply {
                                isLooping = true
                                play()
                            }
                            NavigationManager.navigate(GameScreen())
                        }
                    }
                    delay(5)
                }
            }
        }
    }

    private fun initAssets() {
      //  SpriteManager.init(game.assetManager)
    }

}