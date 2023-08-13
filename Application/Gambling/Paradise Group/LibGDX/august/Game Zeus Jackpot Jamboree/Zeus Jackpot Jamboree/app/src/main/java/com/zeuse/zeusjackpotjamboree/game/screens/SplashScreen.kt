package com.zeuse.zeusjackpotjamboree.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.zeuse.zeusjackpotjamboree.game.LibGDXGame
import com.zeuse.zeusjackpotjamboree.game.manager.FontTTFManager
import com.zeuse.zeusjackpotjamboree.game.manager.MusicManager
import com.zeuse.zeusjackpotjamboree.game.manager.SoundManager
import com.zeuse.zeusjackpotjamboree.game.manager.SpriteManager
import com.zeuse.zeusjackpotjamboree.game.utils.GameColor
import com.zeuse.zeusjackpotjamboree.game.utils.TIME_ANIM_ALPHA
import com.zeuse.zeusjackpotjamboree.game.utils.actor.animHide
import com.zeuse.zeusjackpotjamboree.game.utils.actor.setBounds
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedGame
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedScreen
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedStage
import com.zeuse.zeusjackpotjamboree.game.utils.region
import com.zeuse.zeusjackpotjamboree.game.utils.runGDX
import com.zeuse.zeusjackpotjamboree.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.zeuse.zeusjackpotjamboree.game.utils.Layout.Splash as LS

@SuppressLint("CustomSplashScreen")
class SplashScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    //private val progressLabel by lazy { Label("", Label.LabelStyle(game.fontTTFUtil.fontInterBlack.font_68.font, GameColor.textGreen)) }


    override fun show() {
        loadSplashAssets()
        setUIBackground(SpriteManager.EnumTexture.BACKGROUND_A.data.texture.region)
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
        //addProgress()
        isFinishAnim = true
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

//    private fun AdvancedStage.addProgress() {
//        addActor(progressLabel)
//        progressLabel.apply {
//            setBounds(LS.progress)
//            setAlignment(Align.center)
//        }
//    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND_A)
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTexture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().toMutableList()
            loadTexture()
        }
        with(game.fontTTFManager) {
            loadableFontList = (game.fontTTFUtil.fontInterBlack.values).toMutableList()
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
                    //runGDX { progressLabel.setText("$progress%") }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    //delay(16)
                    delay((5..15).shuffled().first().toLong())
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