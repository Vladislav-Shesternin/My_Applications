package com.doradogames.conflictnations.worldwar.game.screens

import com.doradogames.conflictnations.worldwar.game.GDXGame
import com.doradogames.conflictnations.worldwar.game.manager.MusicManager
import com.doradogames.conflictnations.worldwar.game.manager.ParticleEffectManager
import com.doradogames.conflictnations.worldwar.game.manager.SoundManager
import com.doradogames.conflictnations.worldwar.game.manager.SpriteManager
import com.doradogames.conflictnations.worldwar.game.utils.Block
import com.doradogames.conflictnations.worldwar.game.utils.advanced.AdvancedScreen
import com.doradogames.conflictnations.worldwar.game.utils.advanced.AdvancedStage
import com.doradogames.conflictnations.worldwar.game.utils.region
import com.doradogames.conflictnations.worldwar.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoaderScreen(override val game: GDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false

    override fun show() {
        game.activity.lottie.show()

        loadSplashAssets()
        setBackBackground(game.assetsLoader.MAIN.region)
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
    override fun hideScreen(block: Block) {}

    // Logic ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTexturesList = mutableListOf(SpriteManager.EnumTexture.MAIN.data)
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTexture()
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
        with(game.particleEffectManager) {
            loadableParticleEffectList = ParticleEffectManager.EnumParticleEffect.entries.map { it.data }.toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
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
                    if (progress % 88 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((7..10).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress) {
            isFinishProgress = false

            game.musicUtil.apply { music = musicA.apply {
                isLooping = true
                coff      = 0.223f
            } }

            game.activity.lottie.hide()
            game.navigationManager.navigate(MenuScreen::class.java.name)
        }
    }


}