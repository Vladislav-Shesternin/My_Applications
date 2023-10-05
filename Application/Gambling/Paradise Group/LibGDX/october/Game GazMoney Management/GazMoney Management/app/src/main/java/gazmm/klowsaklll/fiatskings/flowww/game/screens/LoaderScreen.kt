package gazmm.klowsaklll.fiatskings.flowww.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import gazmm.klowsaklll.fiatskings.flowww.game.LibGDXGame
import gazmm.klowsaklll.fiatskings.flowww.game.utils.Layout
import gazmm.klowsaklll.fiatskings.flowww.game.utils.TIME_ANIM_SCREEN_ALPHA
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.animHide
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.setBounds
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedStage
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.CharType.*
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.FontPath
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setCharacters
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setSize
import gazmm.klowsaklll.fiatskings.flowww.game.utils.format
import gazmm.klowsaklll.fiatskings.flowww.game.utils.runGDX
import gazmm.klowsaklll.fiatskings.flowww.util.log
import gazmm.klowsaklll.fiatskings.flowww.util.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import gazmm.klowsaklll.fiatskings.flowww.util.network.Currrent
import gazmm.klowsaklll.fiatskings.flowww.util.network.CurrentNames

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false
    private var isLoadCrypto     = false

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Semibold))
    private val parameter = FreeTypeFontParameter()

    private val progressLabel by lazy { Label("", Label.LabelStyle(generator.generateFont(parameter.setCharacters(NUMBERS.chars+"%").setSize(91)), Color.BLACK)) }

    override fun show() {
        loadCrypto()
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

    override fun dispose() {
        super.dispose()
        generator.dispose()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(Layout.Loader.progress)
            setAlignment(Align.center)
        }
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
            loadableAtlasList = gazmm.klowsaklll.fiatskings.flowww.game.manager.SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = gazmm.klowsaklll.fiatskings.flowww.game.manager.SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
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
                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((7..12).random().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim && isLoadCrypto) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(StartScreen::class.java.name)
            }
        }
    }

    private fun loadCrypto() {
        coroutine?.launch(Dispatchers.IO) {
            try {
                curent = NetworkUtil.service.getCurent()
                curentNamka = NetworkUtil.service.getCurentNames()

                RUB = curent?.rates?.get("RUB")

                isLoadCrypto = true
            } catch (e: Exception) { log("e: ${e.message}") }
        }
    }

}

var RUB: Double? = null
var curent: Currrent? = null
var curentNamka: CurrentNames? = null

val Double.toRub: String get() = this.div(RUB ?: 80.0).format(2).replace('.',',')