package com.bezdrodovam.cryptoinsightspro.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.bezdrodovam.cryptoinsightspro.MainActivity
import com.bezdrodovam.cryptoinsightspro.game.actors.Mask
import com.bezdrodovam.cryptoinsightspro.game.game
import com.bezdrodovam.cryptoinsightspro.game.manager.*
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.animAlpha0
import com.bezdrodovam.cryptoinsightspro.game.utils.actor.animSUSAlpha1
import com.bezdrodovam.cryptoinsightspro.game.utils.advanced.AdvancedGroup
import com.bezdrodovam.cryptoinsightspro.game.utils.advanced.AdvancedScreen
import com.bezdrodovam.cryptoinsightspro.game.utils.runGDX
import com.bezdrodovam.cryptoinsightspro.util.log
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SaploshScreen : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

   // private val paragrasssssa by lazy { Label("", Label.LabelStyle(FontTTFManager.RegularraFont.font_100.font, Color.WHITE)) }

    private val logoImg by lazy { Image(SpriteManager.StartRegion.LOGO.region) }
    private val maska   by lazy { Mask(SpriteManager.StartRegion.BLACK_OUT.region) }
    private val loadImg by lazy { Image(SpriteManager.StartRegion.LOAD.region) }
    private val progImg by lazy { Image(SpriteManager.StartRegion.POROGRESSKA.region) }


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
        coroutine.launch {
            runGDX {
                addLogoLoader()
            }
            animLogo(0.2f)
            loadImg.animSUSAlpha1(0.2f)

            runGDX {
                progImg.addAction(Actions.sequence(
                    Actions.moveTo(0f, 0f, (10..20).random().toFloat() / 10f, Interpolation.fade),
                    Actions.run { isFinishAnim = true }
                ))
            }
        }
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addLogoLoader() {
        MainActivity.lottie.hideLoader()

        addActors(logoImg, loadImg, maska)

        logoImg.apply {
            setBounds(0f, 605f, 679f, 679f)
            setOrigin(Align.center)
            addAction(Actions.scaleTo(0f, 0f))
            animAlpha0()
        }
        loadImg.apply {
            setBounds(130f, 264f, 419f, 83f)
            animAlpha0()
        }
        maska.apply {
            setBounds(130f, 336f, 419f, 11f)

            addActor(progImg)
            progImg.setBounds(-419f, 0f, 419f, 11f)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(SpriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.SPAH)
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
                    FontTTFManager.GilReg.values
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

            mainGroup.animAlpha0(0.5f) {
                coroutine.launch {
                    val isFlagAGA = GameDataStoreManager.FlagPrivata.get() ?: false

                    NavigationManager.navigate(if (isFlagAGA) MarketsBeginnersGuideScreen() else PpoolliiscScreen())
                }
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

    private suspend fun animLogo(time: Float) = CompletableDeferred<Unit>().also { co ->
        runGDX {
            logoImg.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(time),
                    Actions.scaleTo(1f, 1f, time, Interpolation.elasticOut)
                ),
                Actions.run { co.complete(Unit) }
            ))
        }
    }.await()

}