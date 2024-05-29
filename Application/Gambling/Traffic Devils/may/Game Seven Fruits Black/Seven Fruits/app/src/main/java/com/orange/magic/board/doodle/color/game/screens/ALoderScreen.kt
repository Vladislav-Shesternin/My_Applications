package com.orange.magic.board.doodle.color.game.screens

import com.orange.magic.board.doodle.color.game.LidaGame
import com.orange.magic.board.doodle.color.game.manager.MusicManager
import com.orange.magic.board.doodle.color.game.manager.ParticleEffectManager
import com.orange.magic.board.doodle.color.game.manager.SoundManager
import com.orange.magic.board.doodle.color.game.manager.SpriteManager
import com.orange.magic.board.doodle.color.game.utils.TIMI_TERNER
import com.orange.magic.board.doodle.color.game.utils.actor.animHide
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedScreen
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedStage
import com.orange.magic.board.doodle.color.game.utils.region
import com.orange.magic.board.doodle.color.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ALoderScreen(override val game: LidaGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false

    override fun show() {
        loadSplashAssets()
        setBackBackground(game.dgop.meduna.region)
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
        with(game.particleEffectManager) {
            loadableParticleEffectList = ParticleEffectManager.EnumParticleEffect.entries.map { it.data }.toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlas()
        game.musicManager.init()
        game.soundManager.init()
        game.particleEffectManager.init()
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
                    if (progress % 99 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((7..15).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress) {
            isFinishProgress = false

            game.musicUtil.apply { music = PeriTune.apply {
                isLooping = true
                volumeLevelFlow.value = 15f
            } }

            stageUI.root.animHide(TIMI_TERNER) {
                game.activity.lottie.hide()
                game.navigationManager.navigate(EManuelScreen::class.java.name)
            }
        }
    }


}