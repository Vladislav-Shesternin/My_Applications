package com.bydeluxe.d3.android.program.sta.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bydeluxe.d3.android.program.sta.game.LibGDXGame
import com.bydeluxe.d3.android.program.sta.game.actors.progress.ALoading
import com.bydeluxe.d3.android.program.sta.game.manager.MusicManager
import com.bydeluxe.d3.android.program.sta.game.manager.SoundManager.*
import com.bydeluxe.d3.android.program.sta.game.manager.SpriteManager.*
import com.bydeluxe.d3.android.program.sta.game.utils.actor.setBounds
import com.bydeluxe.d3.android.program.sta.game.utils.advanced.AdvancedScreen
import com.bydeluxe.d3.android.program.sta.game.utils.advanced.AdvancedStage
import com.bydeluxe.d3.android.program.sta.game.utils.region
import com.bydeluxe.d3.android.program.sta.game.utils.runGDX
import com.bydeluxe.d3.android.program.sta.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.bydeluxe.d3.android.program.sta.game.utils.Layout.Splash as LS

class LoadScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val textImg      by lazy { Image(game.loadAssets.load) }
    private val loading      by lazy { ALoading(this) }

    override fun show() {
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackgrounds(game.loadAssets.background.region)
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
        addText()
        addLoading()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addText() {
        val avia = Image(game.loadAssets.plane)
        addActor(avia)
        avia.setBounds(5f, 575f, 638f, 506f)

        addActor(textImg)
        textImg.setBounds(78f, 139f, 492f, 155f)
    }

    private fun AdvancedStage.addLoading() {
        addActor(loading)
        loading.setBounds(LS.loading)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(
                EnumTexture.background.data,
                EnumTexture.load.data,
                EnumTexture.mask.data,
                EnumTexture.plane.data,
                EnumTexture.sss.data,
            )
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTexture()
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
//                        progressLabel.setText("$progress%")
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

            game.musicUtil.apply { music = Techno_MUSIC.apply { isLooping = true } }
            animHideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
    }


}