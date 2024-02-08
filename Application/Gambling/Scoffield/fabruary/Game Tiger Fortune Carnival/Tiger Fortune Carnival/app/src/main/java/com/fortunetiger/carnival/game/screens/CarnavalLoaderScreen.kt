package com.fortunetiger.carnival.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.fortunetiger.carnival.game.LibGDXGame
import com.fortunetiger.carnival.game.actors.progress.AProgressBar
import com.fortunetiger.carnival.game.manager.MusicManager
import com.fortunetiger.carnival.game.manager.SoundManager
import com.fortunetiger.carnival.game.manager.SpriteManager
import com.fortunetiger.carnival.game.utils.TIME_ANIM
import com.fortunetiger.carnival.game.utils.actor.animHide
import com.fortunetiger.carnival.game.utils.advanced.AdvancedScreen
import com.fortunetiger.carnival.game.utils.advanced.AdvancedStage
import com.fortunetiger.carnival.game.utils.font.FontParameter
import com.fortunetiger.carnival.game.utils.region
import com.fortunetiger.carnival.game.utils.runGDX
import com.fortunetiger.carnival.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CarnavalLoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter by lazy { FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "%").setSize(64) }
    private val font      by lazy { fontGeneratorBlackHanSans.generateFont(parameter) }

    private val progressBar by lazy { AProgressBar(this) }
    private val progressLbl by lazy { Label("", Label.LabelStyle(font, Color.WHITE)) }

    override fun show() {
        loadSplashAssets()
        setBackgrounds(game.loaderAssets.CARNAVAL.region)
        game.activity.lottie.hideLoader()
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
        addActor(progressBar)
        progressBar.setBounds(103f, 1432f, 929f, 134f)

        addActor(progressLbl)
        progressLbl.setBounds(455f, 1459f, 171f, 80f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList   = mutableListOf(SpriteManager.EnumAtlas.LOADER.data)
            loadAtlas()
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.CARNAVAL.data,
                SpriteManager.EnumTexture.ts_msk.data,
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
                        progressLbl.setText("$progress%")
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

            game.musicUtil.apply { music = MUSIC.apply { isLooping = true } }

            stageUI.root.animHide(TIME_ANIM) {
                game.navigationManager.navigate(CarnavalMenuScreen::class.java.name)
            }
        }
    }


}