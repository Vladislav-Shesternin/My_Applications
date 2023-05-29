package aer.com.gamesas.mobile.slot.game.screens

import aer.com.gamesas.mobile.slot.MainActivity
import aer.com.gamesas.mobile.slot.game.game
import aer.com.gamesas.mobile.slot.game.manager.FontTTFManager
import aer.com.gamesas.mobile.slot.game.manager.NavigationManager
import aer.com.gamesas.mobile.slot.game.manager.SpriteManager
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedGroup
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedScreen
import aer.com.gamesas.mobile.slot.util.log
import android.annotation.SuppressLint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

//    private val progressLabel by lazy { Label("", Label.LabelStyle(FontTTFManager.RegularFont.fo.font, GameColor.loader)) }


    override fun show() {
        loadSplashAssets()
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
        loadAssets()
        collectProgress()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }


    override fun AdvancedGroup.addActorsOnGroup() {
        addProgress()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addProgress() {
//        addActor(progressLabel)
//        progressLabel.apply {
//            setBounds(LS.progress)
//            setAlignment(Align.center)
//        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND)
            loadTexture(game.assetManager)
        }
//        with(FontTTFManager) {
//            loadableListFont = mutableListOf(FontTTFManager.RegularFont.font_90)
//            load(game.assetManager)
//        }
        game.assetManager.finishLoading()

        SpriteManager.initTexture(game.assetManager)
      //  FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
            loadAtlas(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = FontTTFManager.RegularFont.values.toMutableList()
            load(game.assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        FontTTFManager.init(game.assetManager)
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
        coroutine.launch {
            var progress = 0
            progressFlow.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
                   // runGDX { progressLabel.setText("$progress%") }
                    if (progress % 33 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((5..8).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            MainActivity.lottie.hideLoader()
            NavigationManager.navigate(MenuScreen())
        }
    }


}