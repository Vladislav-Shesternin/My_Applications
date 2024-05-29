package com.minimuffin.gardenstor.game.screens

import com.minimuffin.gardenstor.game.SuberGame
import com.minimuffin.gardenstor.game.manager.MusicManager
import com.minimuffin.gardenstor.game.manager.SoundManager
import com.minimuffin.gardenstor.game.manager.SpriteManager
import com.minimuffin.gardenstor.game.utils.actor.animHide
import com.minimuffin.gardenstor.game.utils.advanced.AdvancedScreen
import com.minimuffin.gardenstor.game.utils.advanced.AdvancedStage
import com.minimuffin.gardenstor.game.utils.region
import com.minimuffin.gardenstor.game.utils.vremia_ANIM
import com.minimuffin.gardenstor.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ZagruzonScreen(override val game: SuberGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false

    override fun show() {
        loadSplashAssets()
        setBackBackground(game.fisters.zagruzon.region)
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
            loadableTexturesList = SpriteManager.EnumTexture.entries.map { it.data }.toMutableList()
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTeture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.entries.map { it.data }.toMutableList()
            loadAtlas()
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
                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay(3)
                    delay((2..5).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress) {
            isFinishProgress = false

            game.musicUtil.apply { music = Journey.apply {
                isLooping = true
                volumeLevelFlow.value = 50f
            } }

            stageUI.root.animHide(vremia_ANIM) {
                game.activity.lottie.hide()
                game.navigationManager.navigate(MunhenesiScreen::class.java.name)
            }
        }
    }


}