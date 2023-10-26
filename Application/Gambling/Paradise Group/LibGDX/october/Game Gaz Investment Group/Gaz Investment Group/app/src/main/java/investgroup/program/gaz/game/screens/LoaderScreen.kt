package investgroup.program.gaz.game.screens

import investgroup.program.gaz.game.LibGDXGame
import investgroup.program.gaz.game.utils.TIME_ANIM_SCREEN_ALPHA
import investgroup.program.gaz.game.utils.actor.animHide
import investgroup.program.gaz.game.utils.advanced.AdvancedScreen
import investgroup.program.gaz.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

//    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
//    private val parameter = FreeTypeFontParameter()

//    private val progressLabel by lazy { Label("", Label.LabelStyle(generator.generateFont(parameter.setCharacters(NUMBERS.chars+"%").setSize(58)), Color.BLUE)) }

    override fun show() {
        loadSplashAssets()
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
        addProgress()
        isFinishAnim = true
    }

//    override fun dispose() {
//        super.dispose()
//        generator.dispose()
//    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgress() {
//        addActor(progressLabel)
//        progressLabel.apply {
//            setBounds(investgroup.program.gaz.game.utils.Layout.Loader.progress)
//            setAlignment(Align.center)
//        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(game.fontTTFManager) {
//            loadableFontList = mutableListOf(game.fontTTFUtil.font_Inter_ExtraBold_100)
//            load()
//        }
//        game.assetManager.finishLoading()
//        game.fontTTFManager.init()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = investgroup.program.gaz.game.manager.SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = investgroup.program.gaz.game.manager.SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
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
//                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((6..9).random().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(SecretScreen::class.java.name)
            }
        }
    }

}