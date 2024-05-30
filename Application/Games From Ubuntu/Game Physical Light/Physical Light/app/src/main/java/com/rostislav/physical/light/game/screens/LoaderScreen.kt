package com.rostislav.physical.light.game.screens

import com.rostislav.physical.light.game.GdxGame
import com.rostislav.physical.light.game.manager.SpriteManager
import com.rostislav.physical.light.game.screens.light.ChainScreen
import com.rostislav.physical.light.game.screens.light.ConeScreen
import com.rostislav.physical.light.game.screens.light.PointSettingsScreen
import com.rostislav.physical.light.game.utils.TIME_ANIM_ALPHA
import com.rostislav.physical.light.game.utils.actor.animHide
import com.rostislav.physical.light.game.utils.advanced.AdvancedScreen
import com.rostislav.physical.light.game.utils.advanced.AdvancedStage
import com.rostislav.physical.light.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoaderScreen(override val game: GdxGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false

    override fun show() {
        loadSplashAssets()
       // setBackBackground(game.assetsLoaderUtil.backgrounds.random().region)
        super.show()
        loadAssets()
        collectProgress()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }

    override fun AdvancedStage.addActorsOnStageUI() {}

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(game.spriteManager) {
//            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
//            loadTexture()
//        }
//        game.assetManager.finishLoading()
//        game.spriteManager.initTexture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
//            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
//            loadTexture()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlas()
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
        coroutine?.launch {
            var progress = 0
            progressFlow.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    //delay(16)
                    //delay((2..5).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress) {
            isFinishProgress = false

            stageUI.root.animHide(TIME_ANIM_ALPHA) {
                game.activity.lottie.hide()
                game.navigationManager.navigate(MenuScreen::class.java.name)
            }
        }
    }


}