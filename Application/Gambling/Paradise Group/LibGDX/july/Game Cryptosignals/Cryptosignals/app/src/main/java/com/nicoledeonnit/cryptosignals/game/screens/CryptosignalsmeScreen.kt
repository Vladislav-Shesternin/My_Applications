package com.nicoledeonnit.cryptosignals.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.nicoledeonnit.cryptosignals.MiniActivity
import com.nicoledeonnit.cryptosignals.game.game
import com.nicoledeonnit.cryptosignals.game.manager.FontTTFManager
import com.nicoledeonnit.cryptosignals.game.manager.GameDataStoreManager
import com.nicoledeonnit.cryptosignals.game.manager.NavigationManager
import com.nicoledeonnit.cryptosignals.game.manager.SpriteManager
import com.nicoledeonnit.cryptosignals.game.utils.actor.animHide
import com.nicoledeonnit.cryptosignals.game.utils.actor.animpokazat
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedScreen
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedStage
import com.nicoledeonnit.cryptosignals.game.utils.runGDX
import com.nicoledeonnit.cryptosignals.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CryptosignalsmeScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   // private val paragrasssssa by lazy { Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_100.font, Color.WHITE)) }

    override fun show() {
        loadSplashAssets()
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


    override fun AdvancedStage.addActorsOnStageUI() {
        coroutine.launch {

            runGDX {
                addLoaders()
                root.animpokazat(0.7f) { isFinishAnim = true }
            }

        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addLoaders() {
        val img = Image(SpriteManager.Saprano.garfada.region)
        addActor(img)
        img.setBounds(156f, 603f, 568f, 390f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.garfada)
            loadTexture(game.assetManager)
        }
        game.assetManager.finishLoading()

        SpriteManager.initTexture(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = (FontTTFManager.Regular.values + FontTTFManager.Medium.values + FontTTFManager.Light.values).toMutableList()
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
                    //runGDX { paragrasssssa.setText("$progress%") }
                    if (progress % 98 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((5..10).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {

            coroutine.launch {
                val voxel = GameDataStoreManager.Fantom.get() ?: false

                stageUI.root.animHide(0.7f) {
                    game.activity.lottie.hideLoader()
                    NavigationManager.navigate(if (voxel) VoznagradaScreen() else TelebonScreen())
                }
            }
        }
    }

}