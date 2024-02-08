package com.cosmo3v1.cosmoinc.game.screens

import android.annotation.SuppressLint
import com.cosmo3v1.cosmoinc.MainActivity
import com.cosmo3v1.cosmoinc.adAppOpen
import com.cosmo3v1.cosmoinc.game.game
import com.cosmo3v1.cosmoinc.game.manager.FontTTFManager
import com.cosmo3v1.cosmoinc.game.manager.MusicManager
import com.cosmo3v1.cosmoinc.game.manager.NavigationManager
import com.cosmo3v1.cosmoinc.game.manager.SpriteManager
import com.cosmo3v1.cosmoinc.game.util.advanced.AdvancedScreen
import com.cosmo3v1.cosmoinc.game.util.advanced.AdvancedStage
import com.cosmo3v1.cosmoinc.game.util.runGDX
import com.cosmo3v1.cosmoinc.util.Once
import com.cosmo3v1.cosmoinc.util.log
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen(1280f, 727f) {

   // private val progressLabel by lazy { Label("0", ALabelStyle.akshar_white_25) }

    private val onceFinishLoadingAssets = Once()
    private val progressMutex = Mutex()
    private var progressValue = 0

    private val coroutineUpdateAssets = CoroutineScope(Dispatchers.Main)



    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
        loadAssets()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
    }


    override fun AdvancedStage.addActorsOnStageUI() {
//        addPanel()
//        addProgress()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
//    private fun AdvancedStage.addPanel() {
//        addActor(panelImage)
//        panelImage.apply {
//            with(LS.panel) { setBounds(x, y, w, h) }
//        }
//    }
//
//    private fun AdvancedStage.addProgress() {
//        addActor(progressLabel)
//        progressLabel.apply {
//            with(LS.progress) { setBounds(x, y, w, h) }
//            setAlignment(Align.center)
//        }
//    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------
    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
            loadTexture(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.initTexture(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList   = SpriteManager.EnumAtlas.values().toMutableList()
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadAtlas(game.assetManager)
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = FontTTFManager.BOWLERFont.values.toMutableList()
            load(game.assetManager)
        }
        with(MusicManager) {
            loadableMusicList = MusicManager.EnumMusic.values().toMutableList()
            load(game.assetManager)
        }
    }

    private fun loadingAssets() {
        if (coroutineUpdateAssets.isActive) {
            coroutineUpdateAssets.launch {
                progressMutex.withLock {
                    runGDX { game.assetManager.update() }
                    showProgress(game.assetManager.progress)
                }
            }
        }
    }

    private suspend fun showProgress(progress: Float) {
        while ((progressValue / 100f) < progress) {
            progressValue++
          //  runGDX { progressLabel.setText("$progressValue%") }
            if (progressValue % 25 == 0) log("$progressValue%")
            if (progressValue == 100) {
                onceFinishLoadingAssets.once {
                    coroutineUpdateAssets.cancel()
                    runGDX {
                        game.assetManager.finishLoading()
                        initAssets()

                        adAppOpen?.showAd(game.activity)

                        NavigationManager.navigate(MenuScreen())
                        MainActivity.lottie.hideLoader()
                    }
                }
            }
            delay((10..20).shuffled().first().toLong())
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
        MusicManager.init(game.assetManager)
    }

}