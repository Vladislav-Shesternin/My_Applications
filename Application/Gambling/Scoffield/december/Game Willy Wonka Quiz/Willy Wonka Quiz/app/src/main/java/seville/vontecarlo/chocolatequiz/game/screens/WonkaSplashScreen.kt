package seville.vontecarlo.chocolatequiz.game.screens

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import seville.vontecarlo.chocolatequiz.game.LibGDXGame
import seville.vontecarlo.chocolatequiz.game.manager.MusicManager
import seville.vontecarlo.chocolatequiz.game.manager.SpriteManager
import seville.vontecarlo.chocolatequiz.game.utils.actor.animHide
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedScreen
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedStage
import seville.vontecarlo.chocolatequiz.game.utils.region
import seville.vontecarlo.chocolatequiz.util.log

class WonkaSplashScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow = MutableStateFlow(0f)
    private var isFinishLoading = false
    private var isFinishProgress = false
    private var isFinishAnim = false

    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.EnumTexture.BACKGROUND.data.texture.region)
        super.show()
        loadAssets()
        collectProgress()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND.data)
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTexture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
//            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
//            loadTexture()
        }
        with(game.musicManager) {
            loadableMusicList = MusicManager.EnumMusic.values().map { it.data }.toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlas()
        game.musicManager.init()
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
//                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    //delay(16)
                    delay((7..12).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = WonkaMusic.apply { isLooping = true } }

            stageUI.root.animHide(0.33f) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(WonkaMenuScreen::class.java.name)
            }
        }
    }


}