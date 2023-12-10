package fortune.tiger.avia.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortune.tiger.avia.game.LibGDXGame
import fortune.tiger.avia.game.manager.MusicManager
import fortune.tiger.avia.game.manager.SoundManager
import fortune.tiger.avia.game.manager.SpriteManager
import fortune.tiger.avia.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortune.tiger.avia.game.utils.actor.animHide
import fortune.tiger.avia.game.utils.actor.setBounds
import fortune.tiger.avia.game.utils.actor.setOnClickListener
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen
import fortune.tiger.avia.game.utils.advanced.AdvancedStage
import fortune.tiger.avia.game.utils.region
import fortune.tiger.avia.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import fortune.tiger.avia.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

//    private val parameter = FontParameter()
//
//    private val progressLabel  by lazy { Label("", Label.LabelStyle(fontRegular.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"LOADING%.").setSize(32)), GameColor.yellow)) }
    private val playImg by lazy { Image(game.splashAssets.PUSK) }

    override fun show() {
        loadSplashAssets()
        setBackgrounds(game.splashAssets.SPLASHIMG.region)
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
//        addPlay()
        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPlay() {
        addActor(playImg)
        playImg.apply {
            setBounds(LS.play)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.navigate(MenuScreen::class.java.name)
            } }
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
                SpriteManager.EnumTexture.SPLASHIMG.data,
                SpriteManager.EnumTexture.PG_MASKA.data,
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
//                    runGDX {
//                        progressLoader.setProgressPercent(progress.toFloat())
//                        progressLabel.setText("LOADING $progress...")
//                    }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    //delay((5..10).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = MASIKA.apply { isLooping = true } }
            game.activity.lottie.hideLoader()
            stageUI.addPlay()
        }
    }


}