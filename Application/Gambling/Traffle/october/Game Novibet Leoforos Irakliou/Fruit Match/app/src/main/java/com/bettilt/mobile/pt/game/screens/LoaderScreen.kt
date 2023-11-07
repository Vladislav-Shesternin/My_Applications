package com.bettilt.mobile.pt.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.bettilt.mobile.pt.game.LibGDXGame
import com.bettilt.mobile.pt.game.manager.MusicManager.*
import com.bettilt.mobile.pt.game.manager.SoundManager.*
import com.bettilt.mobile.pt.game.manager.SpriteManager
import com.bettilt.mobile.pt.game.manager.SpriteManager.*
import com.bettilt.mobile.pt.game.manager.util.SpriteUtil
import com.bettilt.mobile.pt.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.bettilt.mobile.pt.game.utils.actor.animHide
import com.bettilt.mobile.pt.game.utils.actor.setBounds
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedStage
import com.bettilt.mobile.pt.game.utils.font.FontParameter
import com.bettilt.mobile.pt.game.utils.font.FontParameter.CharType
import com.bettilt.mobile.pt.game.utils.region
import com.bettilt.mobile.pt.game.utils.runGDX
import com.bettilt.mobile.pt.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.bettilt.mobile.pt.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val splashAssets by lazy { SpriteUtil.SplashAssets() }

    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorKadwa_Bold.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"%").setSize(55)), Color.BLACK)) }
    private val girlImg       by lazy { Image(splashAssets.GIRL) }

    override fun show() {
        loadSplashAssets()
        setBackgrounds(splashAssets.BACKGROUND.region)
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
        addGirl()
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

    private fun AdvancedStage.addGirl() {
        addActor(girlImg)
        girlImg.setBounds(LS.girl)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(EnumTexture.BACKGROUND.data, EnumTexture.GIRL.data)
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
            loadableMusicList = EnumMusic.values().map { it.data }.toMutableList()
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
                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((7..12L).random())
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