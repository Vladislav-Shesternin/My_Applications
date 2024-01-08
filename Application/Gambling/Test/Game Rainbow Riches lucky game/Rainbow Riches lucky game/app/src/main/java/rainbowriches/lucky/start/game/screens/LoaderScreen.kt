package rainbowriches.lucky.start.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import rainbowriches.lucky.start.game.LibGDXGame
import rainbowriches.lucky.start.game.actors.progress.ALoading
import rainbowriches.lucky.start.game.manager.MusicManager
import rainbowriches.lucky.start.game.manager.SoundManager
import rainbowriches.lucky.start.game.manager.SpriteManager
import rainbowriches.lucky.start.game.utils.TIME_ANIM_SCREEN_ALPHA
import rainbowriches.lucky.start.game.utils.actor.animShow
import rainbowriches.lucky.start.game.utils.actor.setBounds
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.advanced.AdvancedStage
import rainbowriches.lucky.start.game.utils.region
import rainbowriches.lucky.start.game.utils.runGDX
import rainbowriches.lucky.start.util.log
import rainbowriches.lucky.start.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

//    private val parameter = FontParameter()
//
//    private val progressLabel  by lazy { Label("", Label.LabelStyle(fontRegular.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"LOADING%.").setSize(32)), GameColor.yellow)) }
    private val leprechaunImg by lazy { Image(game.splashAssets.LEPRECHAUN).apply { color.a = 0f } }
    private val loading       by lazy { ALoading(this).apply { color.a = 0f } }


    override fun show() {
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackgrounds(game.splashAssets.ASK_MY_NAME.region)
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
        addLeprechaun()
        addLoading()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addLeprechaun() {
        addActor(leprechaunImg)
        leprechaunImg.apply {
            setBounds(LS.leprechaun)
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

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.SPLASH.data)
            loadAtlas()
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.ASK_MY_NAME.data,
                SpriteManager.EnumTexture.BLACKWOOD.data,
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
                        loading.setProgressPercent(progress.toFloat())
//                        loading.setText("LOADING $progress...")
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
            animHideScreen { game.navigationManager.navigate(ButtonsScreen::class.java.name) }
        }
    }


}