package rateflow.procurrency.exchelonmaster.game.screens

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import rateflow.procurrency.exchelonmaster.game.LibGDXGame
import rateflow.procurrency.exchelonmaster.game.utils.TIME_ANIM_SCREEN_ALPHA
import rateflow.procurrency.exchelonmaster.game.utils.actor.animHide
import rateflow.procurrency.exchelonmaster.game.utils.advanced.AdvancedScreen
import rateflow.procurrency.exchelonmaster.game.utils.advanced.AdvancedStage
import rateflow.procurrency.exchelonmaster.game.utils.font.CharType.*
import rateflow.procurrency.exchelonmaster.util.log

class PaladiScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    //private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    //private val parameter = FreeTypeFontParameter()

    //private val progressLabel by lazy { Label("", Label.LabelStyle(generator.generateFont(parameter.setCharacters(NUMBERS.chars+"%").setSize(58)), Color.BLUE)) }

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
//        addProgress()
        isFinishAnim = true
    }

//    override fun dispose() {
//        super.dispose()
//        generator.dispose()
//    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

//    private fun AdvancedStage.addProgress() {
//        addActor(progressLabel)
//        progressLabel.apply {
//            setBounds(rateflow.procurrency.exchelonmaster.game.utils.Layout.Loader.progress)
//            setAlignment(Align.center)
//        }
//    }

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
            loadableAtlasList = rateflow.procurrency.exchelonmaster.game.manager.SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = rateflow.procurrency.exchelonmaster.game.manager.SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
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
                    if (progress % 33 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((5..10).random().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(GolemScreen::class.java.name)
            }
        }
    }

}