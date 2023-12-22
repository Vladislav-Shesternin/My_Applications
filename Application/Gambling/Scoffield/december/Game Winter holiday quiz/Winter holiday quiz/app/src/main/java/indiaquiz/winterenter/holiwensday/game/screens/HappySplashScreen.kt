package indiaquiz.winterenter.holiwensday.game.screens

import indiaquiz.winterenter.holiwensday.game.LibGDXGame
import indiaquiz.winterenter.holiwensday.game.manager.MusicManager
import indiaquiz.winterenter.holiwensday.game.manager.SpriteManager
import indiaquiz.winterenter.holiwensday.game.utils.actor.animHide
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedScreen
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedStage
import indiaquiz.winterenter.holiwensday.game.utils.region
import indiaquiz.winterenter.holiwensday.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HappySplashScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.EnumTexture.Splash.data.texture.region)
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
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.Splash.data)
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTexture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
        with(game.musicManager) {
            loadableMusicList = MusicManager.EnumMusic.values().map { it.data }.toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
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

            game.musicUtil.apply { music = MuSiC.apply { isLooping = true } }

            stageUI.root.animHide(0.3f) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(HappyMenuScreen::class.java.name)
            }
        }
    }


}