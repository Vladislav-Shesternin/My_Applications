package fortunetiger.you.luck.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import fortunetiger.you.luck.game.LibGDXGame
import fortunetiger.you.luck.game.actors.progress.ALoading
import fortunetiger.you.luck.game.manager.MusicManager
import fortunetiger.you.luck.game.manager.SoundManager
import fortunetiger.you.luck.game.manager.SpriteManager
import fortunetiger.you.luck.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortunetiger.you.luck.game.utils.actor.animShow
import fortunetiger.you.luck.game.utils.actor.setBounds
import fortunetiger.you.luck.game.utils.advanced.AdvancedScreen
import fortunetiger.you.luck.game.utils.advanced.AdvancedStage
import fortunetiger.you.luck.game.utils.font.FontParameter
import fortunetiger.you.luck.game.utils.region
import fortunetiger.you.luck.game.utils.runGDX
import fortunetiger.you.luck.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import fortunetiger.you.luck.game.utils.Layout.Splash as LS

class LoaLoadScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val centerImg     by lazy { Image(game.splashAssets.LOA_LOADING).apply { color.a = 0f } }
    private val loading       by lazy { ALoading(this).apply { color.a = 0f } }
    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorLionKing.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS.chars+"%").setSize(36)), Color.WHITE)) }


    override fun show() {
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackgrounds(game.splashAssets.LOALOAD_MAIN.region)
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
        addLoa()
        addLoading()
        addProgress()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addLoa() {
        addActor(centerImg)
        centerImg.apply {
            setBounds(LS.loa)
            animShow(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    private fun AdvancedStage.addLoading() {
        addActor(loading)
        loading.apply {
            setBounds(LS.loading)
            animShow(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.progres)
            setAlignment(Align.right)
            animShow(TIME_ANIM_SCREEN_ALPHA)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.LOA_LOAD.data,
                SpriteManager.EnumTexture.LOA_LOADING.data,
                SpriteManager.EnumTexture.LOA_MASK.data,
                SpriteManager.EnumTexture.LOALOAD_MAIN.data,
            )
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
                        progressLabel.setText("$progress%")
                    }
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

            game.musicUtil.apply { music = PUZZLE_MUSIC.apply { isLooping = true } }
            animHideScreen { game.navigationManager.navigate(LoaMenuScreen::class.java.name) }
        }
    }


}