package com.bandagames.mpuzzle.g.game.screens

import com.bandagames.mpuzzle.g.game.LibGDXGame
import com.bandagames.mpuzzle.g.game.manager.MusicManager
import com.bandagames.mpuzzle.g.game.manager.SoundManager
import com.bandagames.mpuzzle.g.game.manager.SpriteManager
import com.bandagames.mpuzzle.g.game.utils.TIME_ANIM
import com.bandagames.mpuzzle.g.game.utils.actor.animHide
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedScreen
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedStage
import com.bandagames.mpuzzle.g.game.utils.region
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoadingScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false

    override fun show() {
        loadSplashAssets()
        setBackBackground(game.Astart.magi.region)
        super.show()
        loadAssets()
        collectProgress()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }

    override fun AdvancedStage.addActorsOnStageUI() {}

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTexturesList = mutableListOf(SpriteManager.EnumTexture.magi.data)
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTeture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.entries.map { it.data }.toMutableList()
            loadAtlas()
            loadableTexturesList = SpriteManager.EnumTexture.entries.map { it.data }.toMutableList()
            loadTexture()
        }
        with(game.musicManager) {
            loadableMusicList = MusicManager.EnumMusic.entries.map { it.data }.toMutableList()
            load()
        }
        with(game.soundManager) {
            loadableSoundList = SoundManager.EnumSound.entries.map { it.data }.toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlas()
        game.spriteManager.initTeture()
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
//                    if (progress % 85 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((10..12).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress) {
            isFinishProgress = false

            game.musicUtil.apply { music = titanium.apply {
                isLooping = true
                volumeLevelFlow.value = 23f
            } }

            stageUI.root.animHide(TIME_ANIM) {
                game.activity.lottie.hide()
                val screenName = if (game.prefsDialog.contains("biba")) MenuScreen::class.java.name else SkoroLifeBudeGood::class.java.name

                game.navigationManager.navigate(screenName)
            }
        }
    }


}