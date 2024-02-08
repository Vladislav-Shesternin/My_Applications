package avia.puzzle.wings.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import avia.puzzle.wings.game.LibGDXGame
import avia.puzzle.wings.game.actors.progress.ALoading
import avia.puzzle.wings.game.manager.MusicManager
import avia.puzzle.wings.game.manager.SoundManager
import avia.puzzle.wings.game.manager.SpriteManager
import avia.puzzle.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.puzzle.wings.game.utils.actor.animHide
import avia.puzzle.wings.game.utils.actor.setBounds
import avia.puzzle.wings.game.utils.advanced.AdvancedScreen
import avia.puzzle.wings.game.utils.advanced.AdvancedStage
import avia.puzzle.wings.game.utils.region
import avia.puzzle.wings.game.utils.runGDX
import avia.puzzle.wings.util.log
import kotlinx.coroutines.delay
import avia.puzzle.wings.game.utils.Layout.Splash as LS

class AviatorLoadingScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val aviatorImg by lazy { Image(game.splashAssets.avia_and_loading) }
    private val loading    by lazy { ALoading(this) }


    override fun show() {
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackgrounds(game.splashAssets.AviatorLoading.region)
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
        addAviator()
        addLoading()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addAviator() {
        addActor(aviatorImg)
        aviatorImg.setBounds(LS.aviator)
    }

    private fun AdvancedStage.addLoading() {
        addActor(loading)
        loading.setBounds(LS.loading)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.LOADING.data)
            loadAtlas()
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.AviatorLoading.data,)
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initAtlasAndTexture()
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
        with(game.soundManager) {
            loadableSoundList = SoundManager.EnumSound.values().map { it.data }.toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
        game.musicManager.init()
        game.soundManager.init()
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
                    runGDX {
                        loading.setProgressPercent(progress.toFloat())
//                        loading.setText("LOADING $progress...")
                    }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((12..20).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = PUZZLE_MUSIC.apply { isLooping = true } }

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.navigate(AviatorMenuScreen::class.java.name)
            }
        }
    }


}