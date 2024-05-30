package com.rochevasternin.physical.joints.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.rochevasternin.physical.joints.game.LibGDXGame
import com.rochevasternin.physical.joints.game.manager.MusicManager
import com.rochevasternin.physical.joints.game.manager.SoundManager
import com.rochevasternin.physical.joints.game.manager.SpriteManager
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JGearScreen
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JMotorScreen
import com.rochevasternin.physical.joints.game.screens.practicalScreen.Practical_JWheelScreen
import com.rochevasternin.physical.joints.game.screens.tutorialsScreen.JMotorScreen
import com.rochevasternin.physical.joints.game.utils.GameColor
import com.rochevasternin.physical.joints.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.rochevasternin.physical.joints.game.utils.actor.animHide
import com.rochevasternin.physical.joints.game.utils.actor.setBounds
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedScreen
import com.rochevasternin.physical.joints.game.utils.advanced.AdvancedStage
import com.rochevasternin.physical.joints.game.utils.font.FontParameter
import com.rochevasternin.physical.joints.game.utils.font.FontParameter.CharType
import com.rochevasternin.physical.joints.game.utils.runGDX
import com.rochevasternin.physical.joints.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.rochevasternin.physical.joints.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorInter_ExtraBold.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"%").setSize(100)), GameColor.textGreen)) }

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

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(/*Practical_JWheelScreen::class.java.name) */ MenuScreen::class.java.name)
            }
        }
    }


}