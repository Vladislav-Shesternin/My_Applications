package plinko.gameballs.nine.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import plinko.gameballs.nine.game.LibGDXGame
import plinko.gameballs.nine.game.manager.MusicManager
import plinko.gameballs.nine.game.manager.SoundManager
import plinko.gameballs.nine.game.manager.SpriteManager
import plinko.gameballs.nine.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.gameballs.nine.game.utils.actor.animHide
import plinko.gameballs.nine.game.utils.advanced.AdvancedScreen
import plinko.gameballs.nine.game.utils.advanced.AdvancedStage
import plinko.gameballs.nine.game.utils.region
import plinko.gameballs.nine.util.log

class RedBallLoadingScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val ballandlImg  by lazy { Image(game.splashAssets.BALL_AND_LOADING) }
    private val loadingImg   by lazy { Image(game.splashAssets.B_LOADING) }
    private val loaderImg    by lazy { Image(game.splashAssets.LOADER) }

    override fun show() {
        loadSplashAssets()
        setBackgrounds(game.splashAssets.B_LIGHTNING.region)
        game.activity.lottie.hideLoader()
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
        addAndFillActor(loadingImg)
        addLoader()
        addBAL()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addLoader() {
        addActor(loaderImg)
        loaderImg.apply {
            setBounds(123f, 510f, 836f, 836f)
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.rotateBy(-360f, 1f)))
        }
    }

    private fun AdvancedStage.addBAL() {
        addActor(ballandlImg)
        ballandlImg.setBounds(206f, 254f, 668f, 996f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.B_LIGHTNING.data,
                SpriteManager.EnumTexture.B_LOADING.data,
                SpriteManager.EnumTexture.LOADER.data,
                SpriteManager.EnumTexture.BALL_AND_LOADING.data,
            )
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
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((15..30).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = MUSIC.apply { isLooping = true } }

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.navigate(MenuButtonsScreen::class.java.name)
            }
        }
    }


}