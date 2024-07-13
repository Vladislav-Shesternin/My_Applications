package com.earlymorningstudio.championsofa.game.screens

import com.earlymorningstudio.championsofa.game.LibGDXGame
import com.earlymorningstudio.championsofa.game.manager.MusicManager
import com.earlymorningstudio.championsofa.game.manager.SoundManager
import com.earlymorningstudio.championsofa.game.manager.SpriteManager
import com.earlymorningstudio.championsofa.game.utils.TIME_ANIM
import com.earlymorningstudio.championsofa.game.utils.actor.animHide
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedScreen
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedStage
import com.earlymorningstudio.championsofa.game.utils.region
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoadingScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false

    override fun show() {
        loadSplashAssets()
        setBackBackground(game.SRAKA.PUNDIC.region)
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
            loadableTexturesList = mutableListOf(SpriteManager.EnumTexture.pundic.data)
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

                    delay((8..14).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress) {
            isFinishProgress = false

            game.musicUtil.apply { music = musikali.apply {
                isLooping = true
                volumeLevelFlow.value = 25f
            } }

            stageUI.root.animHide(TIME_ANIM) {
                game.activity.lottie.hide()
                val screenName = if (game.prefsDialog.contains("arjadasjdk")) MenuScreen::class.java.name else FortepianoPrivet::class.java.name

                game.navigationManager.navigate(screenName)
            }
        }
    }


}