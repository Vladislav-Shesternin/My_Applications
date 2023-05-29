package com.hello.piramidka.game.screens

import android.annotation.SuppressLint
import com.hello.piramidka.MainActivity
import com.hello.piramidka.game.game
import com.hello.piramidka.game.manager.*
import com.hello.piramidka.game.utils.ThemeUtil
import com.hello.piramidka.game.utils.advanced.AdvancedGroup
import com.hello.piramidka.game.utils.advanced.AdvancedScreen
import com.hello.piramidka.game.utils.runGDX
import com.hello.piramidka.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

lateinit var themeUtil: ThemeUtil  private set
@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

//    private val icon by lazy { Image(SpriteManager.SplashRegion.BACKGROUND.region) }


    override fun show() {
        loadSplashAssets()
//        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
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
        addIcon()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addIcon() {
//        addActor(icon)
//        icon.setBounds(LS.icon)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(SpriteManager) {
//            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
//            loadTexture(game.assetManager)
//        }
//        game.assetManager.finishLoading()
//
//        SpriteManager.initTexture(game.assetManager)
       // FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = (
                FontTTFManager.BlackFont.values
            ).toMutableList()
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
                   // runGDX { icon.setText("$progress%") }
                    if (progress % 10 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((5..10).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            coroutine.launch(Dispatchers.IO) {
                val isAgree = GameDataStoreManager.Agree.get() ?: false
                runGDX {
                    isFinishAnim = false
                    themeUtil    = ThemeUtil().apply { setTheme(ThemeUtil.ThemeEnum.DARK) }
                    MainActivity.lottie.hideLoader()
                    NavigationManager.navigate(if (isAgree) MenuScreen() else PrTermsScreen())
                }
            }
        }
    }


}