package com.sca.rab.que.stgame.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.sca.rab.que.stgame.game.LibGDXGame
import com.sca.rab.que.stgame.game.actors.progress.ALoading
import com.sca.rab.que.stgame.game.manager.MusicManager
import com.sca.rab.que.stgame.game.utils.actor.animShow
import com.sca.rab.que.stgame.game.utils.actor.setBounds
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedScreen
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedStage
import com.sca.rab.que.stgame.game.utils.font.FontParameter
import com.sca.rab.que.stgame.game.utils.region
import com.sca.rab.que.stgame.game.utils.runGDX
import com.sca.rab.que.stgame.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.sca.rab.que.stgame.game.utils.Layout.Splash as LS

class LoaLoadScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val centerImg     by lazy { Image(game.splashAssets.LOA_LOADING).apply { color.a = 0f } }
    private val loading       by lazy { ALoading(this).apply { color.a = 0f } }
    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorLionKing.generateFont(parameter.setCharacters(
        FontParameter.CharType.NUMBERS.chars+"%").setSize(36)), Color.WHITE)) }


    override fun show() {
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackgrounds(game.splashAssets.LOALOAD_MAIN.region)
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
        addLoa()
        addLoading()
        addProgress()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addLoa() {
        addActor(centerImg)
        centerImg.apply {
            setBounds(LS.loa)
            animShow(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
        }
    }

    private fun AdvancedStage.addLoading() {
        addActor(loading)
        loading.apply {
            setBounds(LS.loading)
            animShow(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
        }
    }

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.progres)
            setAlignment(Align.right)
            animShow(com.sca.rab.que.stgame.game.utils.TIME_ANIM_SCREEN_ALPHA)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(
                com.sca.rab.que.stgame.game.manager.SpriteManager.EnumTexture.LOA_LOAD.data,
                com.sca.rab.que.stgame.game.manager.SpriteManager.EnumTexture.LOA_LOADING.data,
                com.sca.rab.que.stgame.game.manager.SpriteManager.EnumTexture.LOA_MASK.data,
                com.sca.rab.que.stgame.game.manager.SpriteManager.EnumTexture.LOALOAD_MAIN.data,
            )
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTexture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = com.sca.rab.que.stgame.game.manager.SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = com.sca.rab.que.stgame.game.manager.SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
        with(game.musicManager) {
            loadableMusicList = MusicManager.EnumMusic.values().map { it.data }.toMutableList()
            load()
        }
        with(game.soundManager) {
            loadableSoundList = com.sca.rab.que.stgame.game.manager.SoundManager.EnumSound.values().map { it.data }.toMutableList()
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
                        progressLabel.setText("$progress%")
                    }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((20..50).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = PUZZLE_MUSIC.apply { isLooping = true } }
            animHideScreen { game.navigationManager.navigate(LoaMenuScreen::class.java.name) }
        }
    }


}