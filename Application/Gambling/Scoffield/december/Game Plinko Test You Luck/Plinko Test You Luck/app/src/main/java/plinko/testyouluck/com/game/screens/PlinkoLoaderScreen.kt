package plinko.testyouluck.com.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import plinko.testyouluck.com.game.LibGDXGame
import plinko.testyouluck.com.game.actors.progress.ALoader
import plinko.testyouluck.com.game.manager.MusicManager
import plinko.testyouluck.com.game.manager.SoundManager
import plinko.testyouluck.com.game.manager.SpriteManager
import plinko.testyouluck.com.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.testyouluck.com.game.utils.actor.animHide
import plinko.testyouluck.com.game.utils.advanced.AdvancedScreen
import plinko.testyouluck.com.game.utils.advanced.AdvancedStage
import plinko.testyouluck.com.game.utils.font.FontParameter
import plinko.testyouluck.com.game.utils.region
import plinko.testyouluck.com.game.utils.runGDX
import plinko.testyouluck.com.util.log

class PlinkoLoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val params = FontParameter()
    private val font   = fontGeneratorDefault.generateFont(params.setCharacters(FontParameter.CharType.NUMBERS.chars + "%").setSize(43))

    private val loader      by lazy { ALoader(this) }
    private val progressLbl by lazy { Label("", Label.LabelStyle(font, Color.WHITE)) }

    override fun show() {
        loadSplashAssets()
        setBackgrounds(game.splashAssets.PLINKO_BACKGROUND.region)
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
        addAndFillActor(Image(game.splashAssets.COINS_AND_BOMB))
        addAndFillActor(Image(game.splashAssets.LOADER_PLINKO_BALL))
        addLoader()
        addProgress()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addLoader() {
        addActor(loader)
        loader.setBounds(150f, 568f, 781f, 77f)
    }

    private fun AdvancedStage.addProgress() {
        addActor(progressLbl)
        progressLbl.setBounds(771f, 587f, 120f, 43f)
        progressLbl.setAlignment(Align.right)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList   = mutableListOf(SpriteManager.EnumAtlas.SPLASH.data)
            loadAtlas()
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.PLINKO_BACKGROUND.data,
                SpriteManager.EnumTexture.COINS_AND_BOMB.data,
                SpriteManager.EnumTexture.LOADER_PLINKO_BALL.data,
                SpriteManager.EnumTexture.LOADER_MASK.data,
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
                    runGDX {
                        loader.setProgressPercent(progress.toFloat())
                        progressLbl.setText("$progress%")
                    }
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
                game.navigationManager.navigate(PlinkoMenuScreen::class.java.name)
            }
        }
    }


}