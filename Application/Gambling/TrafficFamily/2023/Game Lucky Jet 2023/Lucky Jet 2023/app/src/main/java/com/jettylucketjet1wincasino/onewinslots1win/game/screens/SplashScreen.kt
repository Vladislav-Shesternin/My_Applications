package com.jettylucketjet1wincasino.onewinslots1win.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.jettylucketjet1wincasino.onewinslots1win.MainActivity
import com.jettylucketjet1wincasino.onewinslots1win.game.game
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.FontTTFManager
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.NavigationManager
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.SpriteManager
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.MAIN_ANIM_SPEED
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.setBounds
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedGroup
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedScreen
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.runGDX
import com.jettylucketjet1wincasino.onewinslots1win.util.log
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val logo by lazy { Image(SpriteManager.SplashRegion.LOGO.region) }


    override fun show() {
        loadSplashAssets()
        stageUI.addAction(Actions.alpha(0f))
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND_1.region)
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
        addLogo()

        coroutine.launch {
            animStage()
            delay(300)
            MainActivity.lottie.hideLoader()
            runGDX { logo.addAction(Actions.fadeIn(MAIN_ANIM_SPEED)) }
            delay(400)
            isFinishAnim = true
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addLogo() {
        addActor(logo)
        logo.setBounds(LS.logo)
        logo.addAction(Actions.alpha(0f))
    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.SPLASH)
            loadAtlas(game.assetManager)
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND_1)
            loadTexture(game.assetManager)
        }

        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
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
                    FontTTFManager.BoldFont.values +
                    FontTTFManager.RegularFont.values
            ).toMutableList()
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
                    if (progress % 25 == 0) log("progress = $progress")
                    if (progress == 100) isFinishProgress = true
                    //delay((31..50).shuffled().first().toLong())
                }
            }
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }


    private suspend fun animStage() = suspendCoroutine<Unit> { continuation ->
        runGDX {
            stageUI.addAction(Actions.sequence(
                Actions.fadeIn(MAIN_ANIM_SPEED, Interpolation.sineOut),
                Actions.run { continuation.resume(Unit) }
            ))
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            mainGroup.apply {
                addAction(Actions.sequence(
                    Actions.fadeOut(MAIN_ANIM_SPEED),
                    Actions.run { NavigationManager.navigate(MenuScreen()) }
                ))
            }
        }
    }


}