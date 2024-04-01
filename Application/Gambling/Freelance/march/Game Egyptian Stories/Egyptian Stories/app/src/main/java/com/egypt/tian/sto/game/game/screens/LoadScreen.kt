package com.egypt.tian.sto.game.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.egypt.tian.sto.game.game.LibGDXGame
import com.egypt.tian.sto.game.game.actors.progress.ALoading
import com.egypt.tian.sto.game.game.manager.MusicManager
import com.egypt.tian.sto.game.game.manager.SoundManager.*
import com.egypt.tian.sto.game.game.manager.SpriteManager.*
import com.egypt.tian.sto.game.game.utils.actor.setBounds
import com.egypt.tian.sto.game.game.utils.advanced.AdvancedScreen
import com.egypt.tian.sto.game.game.utils.advanced.AdvancedStage
import com.egypt.tian.sto.game.game.utils.font.FontParameter
import com.egypt.tian.sto.game.game.utils.region
import com.egypt.tian.sto.game.game.utils.runGDX
import com.egypt.tian.sto.game.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.egypt.tian.sto.game.game.utils.Layout.Splash as LS

class LoadScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+"%").setSize(48)
    private val font      = fontGenerator_InknutAntiqua_Regular.generateFont(parameter)

    private val loading by lazy { ALoading(this) }
    private val textLbl by lazy { Label("0%", Label.LabelStyle(font, Color.WHITE)) }

    override fun show() {
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackgrounds(game.loaderAssets.eqiptian.region)
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
        addLoading()
        addText()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addLoading() {
        addActor(loading)
        loading.setBounds(LS.loading)
    }

    private fun AdvancedStage.addText() {
        addActor(textLbl)
        textLbl.setBounds(LS.texteri)
        textLbl.setAlignment(Align.center)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList = mutableListOf(EnumAtlas.Loader.data)
            loadAtlas()
            loadableTextureList = mutableListOf(
                EnumTexture.eqiptian.data,
                EnumTexture.maska.data,
            )
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initAtlasAndTexture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
        with(game.musicManager) {
            loadableMusicList = MusicManager.EnumMusic.values().map { it.data }.toMutableList()
            load()
        }
        with(game.soundManager) {
            loadableSoundList = EnumSound.values().map { it.data }.toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
        game.musicManager.init()
        game.soundManager.init()
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
        coroutine?.launch {
            var progress = 0
            progressFlow.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
                    runGDX {
                        loading.setProgressPercent(progress.toFloat())
                        textLbl.setText("$progress%")
                    }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((10..15).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = east.apply {
                coff      = 0.7f
                isLooping = true
            } }
            animHideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
    }


}