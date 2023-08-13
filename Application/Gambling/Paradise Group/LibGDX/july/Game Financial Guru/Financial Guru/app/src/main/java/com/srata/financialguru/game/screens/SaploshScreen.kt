package com.srata.financialguru.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.srata.financialguru.MainActivity
import com.srata.financialguru.game.game
import com.srata.financialguru.game.manager.*
import com.srata.financialguru.game.utils.actor.animInvisible
import com.srata.financialguru.game.utils.advanced.AdvancedGroup
import com.srata.financialguru.game.utils.advanced.AdvancedScreen
import com.srata.financialguru.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SaploshScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   // private val paragrasssssa by lazy { Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_100.font, Color.WHITE)) }

    val finGuruImg by lazy { Image(SpriteManager.SplashRegion.FIN_GURU.region) }

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


    override fun AdvancedGroup.addActorsOnGroup() {
       addSplash()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addSplash() {
        MainActivity.lottie.hideLoader()

        addActor(finGuruImg)

        finGuruImg.apply {
            setBounds(37f, 512f, 614f, 563f)
            setOrigin(Align.center)
            animInvisible()
            addAction(Actions.scaleTo(0f, 0f))
        }

        animFinGuru(0.35f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.FIN_GURU)
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
            loadableListFont = (FontTTFManager.GilMed.values).toMutableList()
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
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((5..10).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            mainGroup.animInvisible(0.5f) {
                coroutine.launch {
                    val isAgree = GameDataStoreManager.Agree.get() ?: false

                    //MainActivity.lottie.hideLoader()
                    NavigationManager.navigate(if (isAgree) VasiliyScreen() else PiriatinScreen())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private fun animFinGuru(time: Float) {
        finGuruImg.addAction(Actions.sequence(
            Actions.parallel(
                Actions.fadeIn(time),
                Actions.scaleTo(1f, 1f, time)
            ),
            Actions.run {
                finGuruImg.addAction(Actions.forever(Actions.sequence(
                    Actions.scaleBy(-0.3f, -0.3f, 0.275f),
                    Actions.scaleBy(0.3f, 0.3f, 0.375f),
                )))
            }
        ))
    }

}

val carencyList = listOf(
    "USD",
    "JYP",
    "THB",
    "SGD",
    "AUS",
    "AED",
    "IDR",
    "EUR",
    "GBP",
    "CAD",
)