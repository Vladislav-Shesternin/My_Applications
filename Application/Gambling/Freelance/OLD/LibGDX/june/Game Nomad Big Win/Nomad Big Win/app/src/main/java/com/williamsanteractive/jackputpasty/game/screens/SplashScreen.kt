package com.williamsanteractive.jackputpasty.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.williamsanteractive.jackputpasty.MainActivity
import com.williamsanteractive.jackputpasty.game.game
import com.williamsanteractive.jackputpasty.game.manager.FontTTFManager
import com.williamsanteractive.jackputpasty.game.manager.MusicManager
import com.williamsanteractive.jackputpasty.game.manager.NavigationManager
import com.williamsanteractive.jackputpasty.game.manager.SpriteManager
import com.williamsanteractive.jackputpasty.game.utils.actor.setBounds
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedGroup
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedScreen
import com.williamsanteractive.jackputpasty.game.utils.runGDX
import com.williamsanteractive.jackputpasty.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.williamsanteractive.jackputpasty.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   //private val progressLabel by lazy { Label("", Label.LabelStyle(FontTTFManager.Money.font_90.font, GameColor.loader)) }


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
        addGirl()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addGirl() {
        val girl = Image(SpriteManager.SplashRegion.GIRL.region)
        addActor(girl)
        girl.setBounds(LS.girl)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture(game.assetManager)
        }
        with(FontTTFManager) {
            loadableListFont = mutableListOf(FontTTFManager.Money.font_55)
            load(game.assetManager)
        }
        game.assetManager.finishLoading()

        SpriteManager.initTexture(game.assetManager)
        FontTTFManager.init(game.assetManager)
    }

    private fun loadAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.GAME)
            loadAtlas(game.assetManager)
        }
        with(MusicManager) {
            loadableListMusic = mutableListOf(MusicManager.EnumMusic.MAIN)
            load(game.assetManager)
        }
    }

    private fun initAssets() {
        SpriteManager.initAtlas(game.assetManager)
        FontTTFManager.init(game.assetManager)
        MusicManager.init(game.assetManager)
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
                    //runGDX { progressLabel.setText("$progress%") }
                    if (progress % 23 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    //delay((6..13).shuffled().first().toLong())
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