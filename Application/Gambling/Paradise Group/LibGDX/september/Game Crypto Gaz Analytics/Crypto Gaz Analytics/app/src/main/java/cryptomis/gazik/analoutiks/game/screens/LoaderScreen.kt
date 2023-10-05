package cryptomis.gazik.analoutiks.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import cryptomis.gazik.analoutiks.game.LibGDXGame
import cryptomis.gazik.analoutiks.game.manager.SpriteManager
import cryptomis.gazik.analoutiks.game.utils.CryptoUtil
import cryptomis.gazik.analoutiks.game.utils.Layout
import cryptomis.gazik.analoutiks.game.utils.TIME_ANIM_SCREEN_ALPHA
import cryptomis.gazik.analoutiks.game.utils.actor.animHide
import cryptomis.gazik.analoutiks.game.utils.actor.setBounds
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedScreen
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedStage
import cryptomis.gazik.analoutiks.game.utils.font.CharType.*
import cryptomis.gazik.analoutiks.game.utils.font.FontPath
import cryptomis.gazik.analoutiks.game.utils.font.setCharacters
import cryptomis.gazik.analoutiks.game.utils.font.setSize
import cryptomis.gazik.analoutiks.game.utils.runGDX
import cryptomis.gazik.analoutiks.util.log
import cryptomis.gazik.analoutiks.util.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false
    private var isLoadCrypto     = false

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Inco_Regular))
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
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
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

                    delay((15..20).random().toLong())
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
                NetworkUtil.service.getCrypto().also { crypto ->
                    log("size crypto - ${crypto.data.size}")
                    crypto.data.onEach { data ->
                        CryptoUtil.cryptoList.add(CryptoUtil.Crypto().apply {
                            data.id?.let { id = it }
                            data.name?.let { name = it }
                            data.symbol?.let { symbol = it }
                            data.quote?.USD?.price?.let { price = it }
                        })
                    }
                }

                val ids = CryptoUtil.cryptoList.map { it.id }.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")

                NetworkUtil.service.getMetaData(ids).also { metaData ->
                    log("size logo - ${metaData.data?.size}")

                    CryptoUtil.cryptoList.also { crList ->
                        crList.sortBy { cr -> cr.id }

                        metaData.data?.onEachIndexed { index, map ->
                            crList[index].logo = map.value.logo
                        }
                    }
                }

                isLoadCrypto = true
            } catch (e: Exception) { log("e: ${e.message}") }
        }
    }


}