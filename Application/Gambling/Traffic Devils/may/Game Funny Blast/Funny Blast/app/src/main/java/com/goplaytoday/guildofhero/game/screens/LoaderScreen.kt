package com.goplaytoday.guildofhero.game.screens

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.goplaytoday.guildofhero.game.LGDXGame
import com.goplaytoday.guildofhero.game.manager.SpriteManager
import com.goplaytoday.guildofhero.game.utils.TIME_ANIM_ALPHA
import com.goplaytoday.guildofhero.game.utils.actor.animHide
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedScreen
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedStage
import com.goplaytoday.guildofhero.game.utils.region
import com.goplaytoday.guildofhero.util.log

var staticMusic: Music? = null

var staticSound_Click: Sound? = null

class LoaderScreen(override val game: LGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    override fun show() {
        loadSplashAssets()
        setBackground(game.loaderAssets.BACKGROUND.region)
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
        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND.data)
            loadTexture()

            game.assetManager.finishLoading()

            initTexture()
        }
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.entries.map { it.data }.toMutableList()
            loadAtlas()
        }

        game.assetManager.load("Run-Amok.ogg", Music::class.java)
        game.assetManager.load("mixkit-classic-click.wav", Sound::class.java)
    }

    private fun initAssets() {
        game.spriteManager.initAtlas()

        staticMusic = game.assetManager["Run-Amok.ogg", Music::class.java].apply {
            isLooping = true
            play()
        }
        staticSound_Click = game.assetManager["mixkit-classic-click.wav", Sound::class.java]
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
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((7..12).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_ALPHA) {
                game.activity.lottie.hide()
                game.navigationManager.navigate(MenuScreen::class.java.name)
            }
        }
    }


}