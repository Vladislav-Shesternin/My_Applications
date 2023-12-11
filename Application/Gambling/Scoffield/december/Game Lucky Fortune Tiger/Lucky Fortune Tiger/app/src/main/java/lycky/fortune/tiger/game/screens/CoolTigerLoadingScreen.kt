package lycky.fortune.tiger.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import lycky.fortune.tiger.game.LibGDXGame
import lycky.fortune.tiger.game.actors.progress.ATigerLoader
import lycky.fortune.tiger.game.manager.MusicManager
import lycky.fortune.tiger.game.manager.SoundManager
import lycky.fortune.tiger.game.manager.SpriteManager
import lycky.fortune.tiger.game.utils.actor.animShow
import lycky.fortune.tiger.game.utils.actor.setBounds
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen
import lycky.fortune.tiger.game.utils.advanced.AdvancedStage
import lycky.fortune.tiger.game.utils.region
import lycky.fortune.tiger.game.utils.runGDX
import lycky.fortune.tiger.util.log
import lycky.fortune.tiger.game.utils.Layout.Splash as LS

class CoolTigerLoadingScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val tigerLoader by lazy { ATigerLoader(this).apply { color.a = 0f } }


    override fun show() {
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackgrounds(game.splashAssets.FIRST_BACKGROUND.region)
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
        coroutine?.launch {
            delay(500)
            runGDX {
                addAndFillActor(Image(game.splashAssets.VARIOUS_LUXURY_ITEMS).apply {
                    color.a = 0f
                    animShow(0.3f)
                })
                addActor(Image(game.splashAssets.FORTUNE_TIGER).apply { setBounds(LS.tiger) })
                addTigerLoader()
            }
            delay(700)
            isFinishAnim = true
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addTigerLoader() {
        addActor(tigerLoader)
        tigerLoader.apply {
            setBounds(LS.loader)
            animShow(0.25f)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.SPLASH.data)
            loadAtlas()
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.TIGER_MASK.data,
                SpriteManager.EnumTexture.VARIOUS_LUXURY_ITEMS.data,
                SpriteManager.EnumTexture.FORTUNE_TIGER.data,
                SpriteManager.EnumTexture.FIRST_BACKGROUND.data,
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
                    runGDX { tigerLoader.setProgressPercent(progress.toFloat()) }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((20..50).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = tokio_music.apply { isLooping = true } }
            game.navigationManager.navigate(MoreButtonScreen::class.java.name)
        }
    }


}