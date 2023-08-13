package com.zaykoa.investmentanalyzer.game.screens

import android.annotation.SuppressLint
import com.zaykoa.investmentanalyzer.MainActivity
import com.zaykoa.investmentanalyzer.game.game
import com.zaykoa.investmentanalyzer.game.manager.*
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedGroup
import com.zaykoa.investmentanalyzer.game.utils.advanced.AdvancedScreen
import com.zaykoa.investmentanalyzer.game.utils.runGDX
import com.zaykoa.investmentanalyzer.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   // private val paragrasssssa by lazy { Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_100.font, Color.WHITE)) }


    override fun show() {
        //loadSplashAssets()
        //setBackgrounds(SpriteManager.SplashRegion.BARODADA.region)
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
      // addSplash()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

//    private fun AdvancedGroup.addSplash() {
//        MainActivity.lottie.hideLoader()
//
//        val image = Image(SpriteManager.SplashRegion.LODERKAD.region)
//        addActor(image)
//        image.apply {
//            setBounds(154f, 61f, 368f, 366f)
//            setOrigin(Align.center)
//            addAction(Actions.forever(Actions.rotateBy(360f, 1f)))
//        }
//    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

//    private fun loadSplashAssets() {
//        with(SpriteManager) {
//            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BARODADA, SpriteManager.EnumTexture.LODERKAD)
//            loadTexture(game.assetManager)
//        }
//        game.assetManager.finishLoading()
//
//        SpriteManager.initTexture(game.assetManager)
//    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = (
                    FontTTFManager.PopSemBold.values +
                    FontTTFManager.GilMed.values +
                    FontTTFManager.GilBold.values +
                    FontTTFManager.GilSemBold.values
            ).toMutableList()
            load(game.assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
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
        coroutine.launch {
            var progress = 0
            progressFlow.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
                    //runGDX { paragrasssssa.setText("$progress%") }
                    if (progress % 98 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((3..5).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            MainActivity.lottie.hideLoader()
            NavigationManager.navigate(LoginScreen())
        }
    }


}