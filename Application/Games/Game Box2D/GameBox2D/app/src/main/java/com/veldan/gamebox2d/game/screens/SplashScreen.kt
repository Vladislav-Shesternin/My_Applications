package com.veldan.gamebox2d.game.screens

import android.annotation.SuppressLint
import android.graphics.NinePatch
import android.graphics.drawable.NinePatchDrawable
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.gamebox2d.MainActivity
import com.veldan.gamebox2d.game.actors.LoaderGroup
import com.veldan.gamebox2d.game.actors.label.ALabelStyle
import com.veldan.gamebox2d.game.game
import com.veldan.gamebox2d.game.manager.FontTTFManager
import com.veldan.gamebox2d.game.manager.NavigationManager
import com.veldan.gamebox2d.game.manager.SpriteManager
import com.veldan.gamebox2d.game.utils.actor.setBounds
import com.veldan.gamebox2d.game.utils.advanced.AdvancedGroup
import com.veldan.gamebox2d.game.utils.advanced.AdvancedScreen
import com.veldan.gamebox2d.game.utils.runGDX
import com.veldan.gamebox2d.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.veldan.gamebox2d.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val progressLabel by lazy { Label("", ALabelStyle.style(ALabelStyle.Roboto._40)) }


    override fun show() {
        loadSplashAssets()
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
        addProgress()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.progress)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(SpriteManager) {
//            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
//            loadAtlas(game.assetManager)
//        }
        with(FontTTFManager) {
            loadableFontList = mutableListOf(
                FontTTFManager.RobotoRegularFont.font_75,
                FontTTFManager.JosefinRegularFont.font_75,
                FontTTFManager.JosefinBoldFont.font_75,
            )
            load(game.assetManager)
        }
        game.assetManager.finishLoading()

        //SpriteManager.initAtlas(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
//        FontTTFManager.init(game.assetManager)
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.assetManager.update(17)) {
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
                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((2..3).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            val loader = LoaderGroup(SpriteManager.GameRegion.LOADER.region)
            stageUI.addActor(loader)
            loader.setBounds(859f, 41f, 103f, 103f)

            var progress = 100
            coroutine.launch {
                while (isActive) {
                    if (progress >= 0) {
                        delay(10)
                        loader.setProgress(progress)
                        progress--
                    } else cancel()
                }
                MainActivity.lottie.hideLoader()
                NavigationManager.navigate(GameScreen())
            }
        }
    }


}