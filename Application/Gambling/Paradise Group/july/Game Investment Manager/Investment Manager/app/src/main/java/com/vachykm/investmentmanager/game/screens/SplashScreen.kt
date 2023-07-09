package com.vachykm.investmentmanager.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.vachykm.investmentmanager.MainActivity
import com.vachykm.investmentmanager.game.game
import com.vachykm.investmentmanager.game.manager.*
import com.vachykm.investmentmanager.game.utils.Layout
import com.vachykm.investmentmanager.game.utils.actor.animHide
import com.vachykm.investmentmanager.game.utils.actor.animShow
import com.vachykm.investmentmanager.game.utils.actor.setBounds
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedGroup
import com.vachykm.investmentmanager.game.utils.advanced.AdvancedScreen
import com.vachykm.investmentmanager.game.utils.runGDX
import com.vachykm.investmentmanager.util.log
import kotlinx.coroutines.CompletableDeferred
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

    val headerImg by lazy { Image(SpriteManager.SplashRegion.HEADER.region)         }
    val moneyImg  by lazy { Image(SpriteManager.SplashRegion.HAND_WITH_MONY.region) }
    val textImg   by lazy { Image(SpriteManager.SplashRegion.CENTERAR.region)       }
    val colorsImg by lazy { Image(SpriteManager.SplashRegion.BRODAGA.region)        }

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
       addSplashItems()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addSplashItems() {
        coroutine.launch {
            MainActivity.lottie.hideLoader()

            runGDX {
                addActors(headerImg, moneyImg, textImg, colorsImg)

                headerImg.apply {
                    setBounds(Layout.header)
                    animHide()
                }
                moneyImg.apply {
                    setBounds(143f, 871f, 394f, 405f)
                    animHide()
                }
                textImg.apply {
                    setBounds(44f, 505f, 560f, 115f)
                    animHide()
                }
                colorsImg.apply {
                    setBounds(193f, 163f, 266f, 266f)
                    setOrigin(Align.center)
                    animHide()
                    addAction(Actions.scaleTo(0f, 0f))
                }
            }

            launch { animColors(1f) }
            headerImg.animShow(0.4f)
            launch { animAB(0.5f) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.SPLASH)
            loadAtlas(game.assetManager)
        }
        game.assetManager.finishLoading()

        SpriteManager.initAtlas(game.assetManager)
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
                    if (progress % 20 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true
                    delay((10..12).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            mainGroup.animHide(0.5f) {
                coroutine.launch {
                    val isAgree = GameDataStoreManager.Agree.get() ?: false

                    MainActivity.lottie.hideLoader()
                    NavigationManager.navigate(if (isAgree) HomeSapienceScreen() else ProduktWelcomeScreen())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animColors(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            colorsImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(time),
                    Actions.scaleTo(1f, 1f, time)
                ),
                Actions.run { continuation.complete(Unit) }
            ))
            colorsImg.addAction(Actions.forever(Actions.rotateBy(-360f, 1.5f, Interpolation.linear)))
        }
    }.await()

    private suspend fun animAB(time: Float) = CompletableDeferred<Unit>().also { continuation ->
        runGDX {
            moneyImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(time),
                    Actions.moveTo(143f, 743f, time, Interpolation.sine)
                ),
            ))
            textImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(time),
                    Actions.moveTo(44f, 612f, time, Interpolation.sine)
                ),
                Actions.run {
                    moneyImg.addAction(Actions.forever(Actions.sequence(
                        Actions.moveBy(25f, 25f, 0.3f, Interpolation.sine),
                        Actions.moveBy(-25f, -25f, 0.3f, Interpolation.sine),
                    )))
                    continuation.complete(Unit)
                }
            ))
        }
    }.await()


}