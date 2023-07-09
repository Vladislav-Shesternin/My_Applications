package com.golovkoe.cryptosafe.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.golovkoe.cryptosafe.MainActivity
import com.golovkoe.cryptosafe.game.game
import com.golovkoe.cryptosafe.game.manager.*
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedGroup
import com.golovkoe.cryptosafe.game.utils.advanced.AdvancedScreen
import com.golovkoe.cryptosafe.game.utils.runGDX
import com.golovkoe.cryptosafe.util.log
import kotlinx.coroutines.Dispatchers
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
        loadSplashAssets()
        setBackgrounds(SpriteManager.SplashRegion.BARODADA.region)
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
        addSplash()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addSplash() {
        MainActivity.lottie.hideLoader()

        val image = Image(SpriteManager.SplashRegion.LODERKAD.region)
        addActor(image)
        image.apply {
            setBounds(154f, 61f, 368f, 366f)
            setOrigin(Align.center)
            addAction(Actions.forever(Actions.rotateBy(360f, 1f)))
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BARODADA, SpriteManager.EnumTexture.LODERKAD)
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
            loadableListFont = (FontTTFManager.Regular.values + FontTTFManager.Medium.values).toMutableList()
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
                    if (progress % 5 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    //delay((3..7).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            coroutine.launch(Dispatchers.IO) {
                val isAgree = GameDataStoreManager.Agree.get() ?: false
                runGDX {
                    MainActivity.lottie.hideLoader()
                    NavigationManager.navigate(if (isAgree) FirstScreen() else PricTermsikScreen())
                }
            }
        }
    }


}