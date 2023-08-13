package com.veldan.lbjt.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.manager.FontTTFManager
import com.veldan.lbjt.game.manager.MusicManager
import com.veldan.lbjt.game.manager.SoundManager
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.TIME_ANIM_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.setBounds
import com.veldan.lbjt.game.utils.advanced.AdvancedGame
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage
import com.veldan.lbjt.game.utils.runGDX
import com.veldan.lbjt.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.veldan.lbjt.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val progressLabel by lazy { Label("", Label.LabelStyle(game.fontTTFUtil.fontInterExtraBold.font_100.font, GameColor.textGreen)) }


    override fun show() {
        loadSplashAssets()
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
        addProgress()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.progress)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.fontTTFManager) {
            loadableFontList = mutableListOf(game.fontTTFUtil.fontInterExtraBold.font_100)
            load()
        }
        game.assetManager.finishLoading()
        game.fontTTFManager.init()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture()
        }
        with(game.fontTTFManager) {
            loadableFontList = (
                game.fontTTFUtil.fontInterExtraBold.values +
                game.fontTTFUtil.fontInterMedium.values +
                game.fontTTFUtil.fontInterBlack.values
            ).toMutableList()
            load()
        }
        with(game.musicManager) {
            loadableMusicList = (MusicManager.EnumMusic.values()).toMutableList()
            load()
        }
        with(game.soundManager) {
            loadableSoundList = (SoundManager.EnumSound.values()).toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
        game.fontTTFManager.init()
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
                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    //delay(16)
                    //delay((2..5).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(MenuScreen(game))
            }
        }
    }


}