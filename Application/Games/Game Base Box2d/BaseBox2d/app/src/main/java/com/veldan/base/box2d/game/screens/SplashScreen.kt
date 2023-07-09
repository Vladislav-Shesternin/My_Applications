package com.veldan.base.box2d.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.base.box2d.MainActivity
import com.veldan.base.box2d.game.game
import com.veldan.base.box2d.game.manager.FontTTFManager
import com.veldan.base.box2d.game.manager.NavigationManager
import com.veldan.base.box2d.game.manager.SpriteManager
import com.veldan.base.box2d.game.utils.GameColor
import com.veldan.base.box2d.game.utils.TIME_ANIM_ALPHA
import com.veldan.base.box2d.game.utils.actor.animHide
import com.veldan.base.box2d.game.utils.actor.setBounds
import com.veldan.base.box2d.game.utils.advanced.AdvancedGroup
import com.veldan.base.box2d.game.utils.advanced.AdvancedScreen
import com.veldan.base.box2d.game.utils.runGDX
import com.veldan.base.box2d.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.veldan.base.box2d.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val progressLabel by lazy { Label("", Label.LabelStyle(FontTTFManager.Inter.ExtraBold.font_100.font, GameColor.textGreen)) }


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
        with(FontTTFManager) {
            loadableFontList = mutableListOf(FontTTFManager.Inter.ExtraBold.font_100)
            load(game.assetManager)
        }
        game.assetManager.finishLoading()

        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableFontList = (
                FontTTFManager.Inter.ExtraBold.values
            ).toMutableList()
            load(game.assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
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
                    delay((4..7).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            mainGroup.animHide(TIME_ANIM_ALPHA) {
                com.veldan.base.box2d.MainActivity.lottie.hideLoader()
                NavigationManager.navigate(MenuScreen())
            }
        }
    }


}