package com.hk.stck.nord.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.hk.stck.nord.MainActivity
import com.hk.stck.nord.game.game
import com.hk.stck.nord.game.manager.FontTTFManager
import com.hk.stck.nord.game.manager.MusicManager
import com.hk.stck.nord.game.manager.NavigationManager
import com.hk.stck.nord.game.manager.SpriteManager
import com.hk.stck.nord.game.utils.advanced.AdvancedGroup
import com.hk.stck.nord.game.utils.advanced.AdvancedScreen
import com.hk.stck.nord.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false


    override fun show() {
        loadSplashAssets()
        stageUI.addAction(Actions.alpha(0f))
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
        loadAssets()
        collectProgress()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }


    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
           // delay(00)
           // showStage()
            isFinishAnim = true
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

//    private fun AdvancedGroup.addProgress() {
//        addActor(progressLabel)
//        progressLabel.apply {
//            setBounds(LS.progress)
//            setAlignment(Align.center)
//        }
//    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
//            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
//            loadAtlas(game.assetManager)
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
            loadTexture(game.assetManager)
        }
//        with(FontTTFManager) {
//            loadableListFont = mutableListOf(FontTTFManager.Jura.font_78)
//            load(game.assetManager)
//        }

        game.assetManager.finishLoading()

      //  SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
       // FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = (
                    FontTTFManager.AlegreyaSansSC_Regular.values +
                    FontTTFManager.AlegreyaSansSC_ExtraBold.values +
                    FontTTFManager.AlegreyaSansSC_Bold.values
            ).toMutableList()
            load(game.assetManager)
        }
        with(MusicManager) {
            loadableMusicList = mutableListOf(MusicManager.EnumMusic.MAIN)
            load(game.assetManager)
        }
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.assetManager.update(16)) {
                isFinishLoading = true
                initAssets()
            }
            progressFlow.value = game.assetManager.progress
        }
    }

    private fun collectProgress() {
        coroutine.launch {
            var progress = 0
            progressFlow.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
                   // progressLabel.setText("$progress%")
                    if (progress % 25 == 0) log("progress = $progress")
                    if (progress == 100) isFinishProgress = true
                   // delay((7..14).shuffled().first().toLong())
                }
            }
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
        MusicManager.init(game.assetManager)
    }

//    private suspend fun showStage() = suspendCoroutine<Unit> { continuation ->
//        runGDX {
//            stageUI.addAction(Actions.sequence(
//                Actions.fadeIn(0.4f),
//                Actions.run { continuation.resume(Unit) }
//            ))
//        }
//    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim && MainActivity.isBebViewVisibleFlow.value == false) {
            isFinishAnim = false

            MainActivity.lottie.hideLoader()
            NavigationManager.navigate(MenuScreen())
        }
    }


}