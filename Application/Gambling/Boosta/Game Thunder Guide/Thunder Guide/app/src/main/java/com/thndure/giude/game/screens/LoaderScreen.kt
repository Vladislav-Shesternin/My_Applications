package com.thndure.giude.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.thndure.giude.game.LibGDXGame
import com.thndure.giude.game.manager.SpriteManager
import com.thndure.giude.game.utils.GameColor
import com.thndure.giude.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.thndure.giude.game.utils.actor.animHide
import com.thndure.giude.game.utils.actor.setBounds
import com.thndure.giude.game.utils.advanced.AdvancedScreen
import com.thndure.giude.game.utils.advanced.AdvancedStage
import com.thndure.giude.game.utils.font.FontParameter
import com.thndure.giude.game.utils.font.FontParameter.CharType
import com.thndure.giude.game.utils.runGDX
import com.thndure.giude.log
import com.thndure.giude.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorArial_Black.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"%").setSize(100)), GameColor.text)) }

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
//        with(game.fontTTFManager) {
//            loadableFontList = mutableListOf(game.fontTTFUtil.font_Inter_ExtraBold_100)
//            load()
//        }
//        game.assetManager.finishLoading()
//        game.fontTTFManager.init()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
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
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((5..10).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.assetManager.load("upbeat-funk-rhythmic-background-music-intro-theme.mp3", Music::class.java)
            game.assetManager.finishLoading()
            val music = game.assetManager["upbeat-funk-rhythmic-background-music-intro-theme.mp3", Music::class.java]
            music.apply {
                isLooping = true
                volume    = 0.2f
                play()
            }

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(MenuScreen::class.java.name)
            }
        }
    }


}