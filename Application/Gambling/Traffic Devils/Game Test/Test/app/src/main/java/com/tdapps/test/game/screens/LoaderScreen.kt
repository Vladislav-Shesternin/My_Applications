package com.tdapps.test.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.tdapps.test.game.LibGDXGame
import com.tdapps.test.game.manager.SpriteManager
import com.tdapps.test.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.tdapps.test.game.utils.actor.animHide
import com.tdapps.test.game.utils.actor.setBounds
import com.tdapps.test.game.utils.advanced.AdvancedScreen
import com.tdapps.test.game.utils.advanced.AdvancedStage
import com.tdapps.test.game.utils.font.FontParameter
import com.tdapps.test.game.utils.font.FontParameter.CharType
import com.tdapps.test.game.utils.region
import com.tdapps.test.game.utils.runGDX
import com.tdapps.test.util.log
import com.tdapps.test.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorKaushanScript_Regular.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"%").setSize(136)), Color.WHITE)) }

    override fun show() {
        loadSplashAssets()
        setBackground(game.spriteUtilSplash.BACKGROUND.region)
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
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND.data)
            loadTexture()

            game.assetManager.finishLoading()

            initTexture()
        }
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }

        game.assetManager.load("music.mp3", Music::class.java)
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
        game.assetManager["music.mp3", Music::class.java].apply {
            isLooping = true
            play()
        }

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

                    delay((7..12).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(MenuScreen::class.java.name)
            }
        }
    }


}