package crupp.tsk.learnn.inggg.game.screens

import crupp.tsk.learnn.inggg.game.LibGDXGame
import crupp.tsk.learnn.inggg.game.manager.SpriteManager
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedScreen
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedStage
import crupp.tsk.learnn.inggg.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MasloScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    //private val progressLabel by lazy { Label("", Label.LabelStyle(game.fontTTFUtil.fontInterBlack.font_68.font, GameColor.textGreen)) }
    //private val zefsImg by lazy { Image(game.spriteUtil.SPLASH.ZEFS) }


    override fun show() {
        //loadSplashAssets()
        //setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
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
      //  game.activity.lottie.hideLoader()
      //  addZefs()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addZefs() {
//        addActor(zefsImg)
//        zefsImg.apply {
//            animHide()
//            setBounds(LS.zefs)
//            addAction(Actions.forever(Actions.sequence(
//                Actions.fadeIn(0.5f),
//                Actions.alpha(0.5f, 0.5f),
//            )))
//        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

//    private fun loadSplashAssets() {
//        with(game.spriteManager) {
//            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
//            loadTexture()
//        }
//        game.assetManager.finishLoading()
//        game.spriteManager.initTexture()
//    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas()
//            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
//            loadTexture()
        }
        with(game.fontTTFManager) {
            loadableFontList = (game.fontTTFUtil.fontInterBlack.values).toMutableList()
            load()
        }
//        with(game.musicManager) {
//            loadableMusicList = (MusicManager.EnumMusic.values()).toMutableList()
//            load()
//        }
//        with(game.soundManager) {
//            loadableSoundList = (SoundManager.EnumSound.values()).toMutableList()
//            load()
//        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlas()
        game.fontTTFManager.init()
        //game.musicManager.init()
        //game.soundManager.init()
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.assetManager.update(17)) {
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
                    //runGDX { progressLabel.setText("$progress%") }
                    if (progress % 10 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    //delay(16)
                    delay((10..15).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

//            game.musicUtil.apply { music = DEFAULT.apply { isLooping = true } }

            game.activity.lottie.hideLoader()
            game.navigationManager.navigate(LobudkaScreen(game))
        }
    }


}