package com.prochenkoa.businessplanner.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.prochenkoa.businessplanner.MercedesActivity
import com.prochenkoa.businessplanner.game.game
import com.prochenkoa.businessplanner.game.manager.FontTTFManager
import com.prochenkoa.businessplanner.game.manager.GameDataStoreManager
import com.prochenkoa.businessplanner.game.manager.NavigationManager
import com.prochenkoa.businessplanner.game.manager.SpriteManager
import com.prochenkoa.businessplanner.game.utils.actor.animShow
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedScreen
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedStage
import com.prochenkoa.businessplanner.game.utils.runGDX
import com.prochenkoa.businessplanner.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BiznesScreen : AdvancedScreen() {

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
                root.animShow(0f)
            }

            isFinishAnim = true
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addLoaders() {
        val img = Image(SpriteManager.Sepe.POCO.region)
        addActor(img)
        img.setBounds(158f, 764f, 425f, 145f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.poco)
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
            loadableListFont = (
                    FontTTFManager.Regular.values +
                    FontTTFManager.Semibold.values
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
                    if (progress % 37 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((5..10).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            coroutine.launch {
                val iskat = GameDataStoreManager.Matis.get() ?: false

                MercedesActivity.lottie.hideLoader()
                NavigationManager.navigate(if (iskat) KategirijScreen() else BiznesPlanScreen())
            }
        }
    }

}