package com.fortunetiger.bigwin.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.fortunetiger.bigwin.game.LibGDXGame
import com.fortunetiger.bigwin.game.actors.progress.AProgress
import com.fortunetiger.bigwin.game.manager.MusicManager
import com.fortunetiger.bigwin.game.manager.SoundManager
import com.fortunetiger.bigwin.game.manager.SpriteManager
import com.fortunetiger.bigwin.game.utils.TIME_ANIM_ALPHA
import com.fortunetiger.bigwin.game.utils.actor.animHide
import com.fortunetiger.bigwin.game.utils.actor.setBounds
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedScreen
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedStage
import com.fortunetiger.bigwin.game.utils.font.FontParameter
import com.fortunetiger.bigwin.game.utils.font.FontParameter.CharType
import com.fortunetiger.bigwin.game.utils.region
import com.fortunetiger.bigwin.game.utils.runGDX
import com.fortunetiger.bigwin.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.fortunetiger.bigwin.game.utils.Layout.Splash as LS

class FTWLoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter().setCharacters(CharType.NUMBERS.chars+"%").setSize(64)

    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGenerator_JuaRegular.generateFont(parameter), Color.WHITE)) }
    private val progressBar   by lazy { AProgress(this) }

    override fun show() {
        game.activity.lottie.hideLoader()
        loadSplashAssets()
        setBackBackground(game.loaderAssets.FTWBackground.region)
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
        addForutne()
        addProgress()
        addProgressLbl()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addForutne() {
        val forutne = Image(game.loaderAssets.loa_svet)
        addActor(forutne)
        forutne.setBounds(428f, 0f, 1068f, 923f)
    }

    private fun AdvancedStage.addProgress() {
        addActor(progressBar)
        progressBar.setBounds(LS.progress)
    }

    private fun AdvancedStage.addProgressLbl() {
        addActor(progressLabel)
        progressLabel.setBounds(729f, 760f, 461f, 80f)
        progressLabel.setAlignment(Align.center)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.LOADER.data)
            loadAtlas()

            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.FTWBackground.data,
                SpriteManager.EnumTexture.loa_svet.data,
            )
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initAtlasAndTexture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
        with(game.musicManager) {
            loadableMusicList = MusicManager.EnumMusic.values().map { it.data }.toMutableList()
            load()
        }
        with(game.soundManager) {
            loadableSoundList = SoundManager.EnumSound.values().map { it.data }.toMutableList()
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
                        progressBar.setProgressPercent(progress.toFloat())
                        progressLabel.setText("$progress%")
                    }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((15..30).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply {
                coff  = 0.25f
                music = MUSIC.apply { isLooping = true }
            }

            stageUI.root.animHide(TIME_ANIM_ALPHA) {
                game.navigationManager.navigate(FTWMenuScreen::class.java.name)
            }
        }
    }


}