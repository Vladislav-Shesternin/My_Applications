package fludilia.mamkinarow.lotrends.game.screens

import fludilia.mamkinarow.lotrends.MainActivity
import fludilia.mamkinarow.lotrends.R
import fludilia.mamkinarow.lotrends.game.LibGDXGame
import fludilia.mamkinarow.lotrends.game.utils.T_FARA
import fludilia.mamkinarow.lotrends.game.utils.actor.animHide
import fludilia.mamkinarow.lotrends.game.utils.advanced.AdvancedScreen
import fludilia.mamkinarow.lotrends.game.utils.advanced.AdvancedStage
import fludilia.mamkinarow.lotrends.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

//    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Semibold))
//    private val parameter = FreeTypeFontParameter()
//
//    private val progressLabel by lazy { Label("", Label.LabelStyle(generator.generateFont(parameter.setCharacters(NUMBERS.chars+"%").setSize(91)), Color.BLACK)) }

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
//            setBounds(Layout.Loader.progress)
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
            loadableAtlasList = fludilia.mamkinarow.lotrends.game.manager.SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = fludilia.mamkinarow.lotrends.game.manager.SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
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
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((8..12).random().toLong())
                }
            }
        }
    }

    class Dobrak(val pozor: MainActivity, val okHttpClient: OkHttpClient) {
         fun getReshenie(linkCheck: String, key: String, link: String) {
                 try {
                     val request: Request = Request.Builder().url(linkCheck).build()

                     okHttpClient.newCall(request).execute().use { response ->
                         if (response.isSuccessful) {
                             //log("getResponseFromServer SUCCESS code = ${response.code}")
                             DobavitScreen.Mazdun().otigolochke(pozor, key, link)
                         } else {
                             //log("getResponseFromServer newCall FAIL: ${response.code} ${response.message}")
                             MainActivity.startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                         }
                     }
                 } catch (e: IOException) {
                     log("getResponseFromServer FAIL: $e")
                     MainActivity.startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                 }
             }
        }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(T_FARA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(BannerScreen::class.java.name)
            }
        }
    }

}