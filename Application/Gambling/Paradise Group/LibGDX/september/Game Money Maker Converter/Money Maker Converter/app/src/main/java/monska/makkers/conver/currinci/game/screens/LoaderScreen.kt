package monska.makkers.conver.currinci.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import monska.makkers.conver.currinci.game.LibGDXGame
import monska.makkers.conver.currinci.game.utils.Layout
import monska.makkers.conver.currinci.game.utils.TIME_ANIM_SCREEN_ALPHA
import monska.makkers.conver.currinci.game.utils.actor.animHide
import monska.makkers.conver.currinci.game.utils.actor.setBounds
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedScreen
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedStage
import monska.makkers.conver.currinci.game.utils.font.CharType.*
import monska.makkers.conver.currinci.game.utils.font.FontPath
import monska.makkers.conver.currinci.game.utils.font.setCharacters
import monska.makkers.conver.currinci.game.utils.font.setSize
import monska.makkers.conver.currinci.game.utils.runGDX
import monska.makkers.conver.currinci.util.log
import monska.makkers.conver.currinci.util.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import monska.makkers.conver.currinci.util.network.Valuta
import monska.makkers.conver.currinci.util.network.ValutaNameta

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false
    private var isLoadCrypto     = false

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Jaldi))
    private val parameter = FreeTypeFontParameter()

    private val progressLabel by lazy { Label("", Label.LabelStyle(generator.generateFont(parameter.setCharacters(NUMBERS.chars+"%").setSize(50)), Color.WHITE)) }

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
            loadableAtlasList = monska.makkers.conver.currinci.game.manager.SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = monska.makkers.conver.currinci.game.manager.SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
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
                valuta = NetworkUtil.service.getValute()
                valutaNamesta = NetworkUtil.service.getValuteNameta()
                isLoadCrypto = true
            } catch (e: Exception) { log("e: ${e.message}") }
        }
    }

}

var valuta: Valuta? = null
var valutaNamesta: ValutaNameta? = null