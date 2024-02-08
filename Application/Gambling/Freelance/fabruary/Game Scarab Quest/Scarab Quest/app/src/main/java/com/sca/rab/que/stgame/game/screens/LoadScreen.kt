package com.sca.rab.que.stgame.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.sca.rab.que.stgame.game.LibGDXGame
import com.sca.rab.que.stgame.game.actors.progress.ALoading
import com.sca.rab.que.stgame.game.manager.MusicManager
import com.sca.rab.que.stgame.game.manager.SoundManager.*
import com.sca.rab.que.stgame.game.manager.SpriteManager
import com.sca.rab.que.stgame.game.manager.SpriteManager.*
import com.sca.rab.que.stgame.game.utils.TIME_ANIM
import com.sca.rab.que.stgame.game.utils.actor.animShow
import com.sca.rab.que.stgame.game.utils.actor.setBounds
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedScreen
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedStage
import com.sca.rab.que.stgame.game.utils.region
import com.sca.rab.que.stgame.game.utils.runGDX
import com.sca.rab.que.stgame.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.sca.rab.que.stgame.game.utils.Layout.Splash as LS

class LoadScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val pharaImg     by lazy { Image(game.loadAssets.phara) }
    private val progrImg     by lazy { Image(game.loadAssets.progress_back) }
    private val textImg      by lazy { Image(game.loadAssets.loading) }
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
        addPhara()
        addProgress()
        addLoading()
        addText()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPhara() {
        addActor(pharaImg)
        pharaImg.setBounds(LS.phara)
    }

    private fun AdvancedStage.addProgress() {
        addActor(progrImg)
        progrImg.setBounds(LS.progres)
    }

    private fun AdvancedStage.addLoading() {
        addActor(loading)
        loading.setBounds(LS.loading)
    }

    private fun AdvancedStage.addText() {
        addActor(textImg)
        textImg.setBounds(LS.loaring)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(
                EnumTexture.background.data,
                EnumTexture.loader_mask.data,
                EnumTexture.loading.data,
                EnumTexture.phara.data,
                EnumTexture.progress.data,
                EnumTexture.progress_back.data,
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

                    //delay((20..50).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

//            game.musicUtil.apply { music = PUZZLE_MUSIC.apply { isLooping = true } }
            animHideScreen { game.navigationManager.navigate(MenuScreen::class.java.name) }
        }
    }


}