package com.leto.advancedcalculator.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.leto.advancedcalculator.NewVebkaZaebcaActivity
import com.leto.advancedcalculator.game.actors.ImageGif
import com.leto.advancedcalculator.game.game
import com.leto.advancedcalculator.game.manager.FontTTFManager
import com.leto.advancedcalculator.game.manager.GameDataStoreManager
import com.leto.advancedcalculator.game.manager.NavigationManager
import com.leto.advancedcalculator.game.manager.SpriteManager
import com.leto.advancedcalculator.game.utils.actor.animShow
import com.leto.advancedcalculator.game.utils.advanced.AdvancedScreen
import com.leto.advancedcalculator.game.utils.advanced.AdvancedStage
import com.leto.advancedcalculator.game.utils.runGDX
import com.leto.advancedcalculator.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SatisfactionScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   // private val paragrasssssa by lazy { Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_100.font, Color.WHITE)) }

    private val loader by lazy { ImageGif(SpriteManager.ListRegion.LOADER.regionList) }
    private val circle by lazy { ImageGif(SpriteManager.ListRegion.CIRC.regionList)   }
    private val text   by lazy { Image(SpriteManager.SRegion.TEXT.region)   }

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
            NewVebkaZaebcaActivity.lottie.hideLoader()

            runGDX {
                addLoaders()
                root.animShow(0.5f)
            }

            delay(2_000)
            isFinishAnim = true
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addLoaders() {
        addActor(loader)
        loader.setBounds(256f, 758f, 267f, 244f)
        addActor(circle)
        circle.setBounds(313f, 131f, 179f, 179f)
        addActor(text)
        text.setBounds(137f, 599f, 505f, 138f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.LOADER, SpriteManager.EnumAtlas.CIRCLE)
            loadAtlas(game.assetManager)
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
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
                    FontTTFManager.GilRegularro.values +
                    FontTTFManager.GilMediummo.values
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
                    if (progress % 22 == 0) log("progress = $progress%")
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
                val isFlagAGA = GameDataStoreManager.ChtoEto.get() ?: false

                runGDX {
                    stageUI.root.addAction(Actions.sequence(
                        Actions.fadeOut(0.5f),
                        Actions.run { NavigationManager.navigate(if (isFlagAGA) LetaSonceJaraaJaRabotkayouScreen() else UslovieVipolnenoScreen()) }
                    ))
                }
            }
        }
    }

}