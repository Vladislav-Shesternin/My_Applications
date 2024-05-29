package com.tutotoons.app.kpopsiescuteunicornpet.game.screens

import com.tutotoons.app.kpopsiescuteunicornpet.game.GDXGame
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.MusicManager
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.ParticleEffectManager
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.SoundManager
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.SpriteManager
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.Block
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedScreen
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.advanced.AdvancedStage
import com.tutotoons.app.kpopsiescuteunicornpet.game.utils.region
import com.tutotoons.app.kpopsiescuteunicornpet.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoaderScreen(override val game: GDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false

    override fun show() {
        loadSplashAssets()
        setBackBackground(game.assetsLoader.background_purple.region)
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
            loadableTexturesList = mutableListOf(SpriteManager.EnumTexture.background_purple.data)
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
                    if (progress % 75 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((12..14).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress) {
            isFinishProgress = false

            game.musicUtil.apply { music = main.apply {
                isLooping = true
                coff      = 0.15f
            } }

            game.activity.lottie.hide()
            game.navigationManager.navigate(MenuScreen::class.java.name)
        }
    }


}