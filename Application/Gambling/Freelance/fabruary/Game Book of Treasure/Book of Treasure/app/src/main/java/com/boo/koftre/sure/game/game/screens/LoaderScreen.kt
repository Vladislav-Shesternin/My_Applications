package com.boo.koftre.sure.game.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.boo.koftre.sure.game.game.LibGDXGame
import com.boo.koftre.sure.game.game.actors.progress.ALoader
import com.boo.koftre.sure.game.game.manager.MusicManager
import com.boo.koftre.sure.game.game.manager.SoundManager
import com.boo.koftre.sure.game.game.manager.SpriteManager
import com.boo.koftre.sure.game.game.screens.template_screen.MenuScreen
import com.boo.koftre.sure.game.game.utils.actor.setBounds
import com.boo.koftre.sure.game.game.utils.advanced.AdvancedScreen
import com.boo.koftre.sure.game.game.utils.advanced.AdvancedStage
import com.boo.koftre.sure.game.game.utils.region
import com.boo.koftre.sure.game.game.utils.runGDX
import com.boo.koftre.sure.game.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.boo.koftre.sure.game.game.utils.Layout.Loader as LL

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val Coboy    by lazy { Image(game.loadingAssets.coboy) }
    private val Bloading by lazy { Image(game.loadingAssets.bloading) }
    private val Loader   by lazy { ALoader(this) }


    override fun show() {
        //stageUI.root.color.a = 0f
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackBackground(game.loadingAssets.matrix.region)
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
        addCoboy()
        addBolding()
        addLoader()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addCoboy() {
        addActor(Coboy)
        Coboy.setBounds(LL.coboy)
    }

    private fun AdvancedStage.addBolding() {
        addActor(Bloading)
        Bloading.setBounds(86f, 1326f, 908f, 87f)
    }

    private fun AdvancedStage.addLoader() {
        addActor(Loader)
        Loader.setBounds(101f, 1297f, 913f, 140f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.Loading.data)
            loadAtlas()
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.fulikula.data,
                SpriteManager.EnumTexture.matrix.data,
            )
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initAtlasAndTexture()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
        with(game.musicManager) {
            loadableMusicList = MusicManager.EnumMusic.values().map { it.data }.toMutableList()
            load()
        }
        with(game.soundManager) {
            loadableSoundList = SoundManager.EnumSound.values().map { it.data }.toMutableList()
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
                    runGDX { Loader.setProgressPercent(progress.toFloat()) }
                    if (progress % 100 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((15..30).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = EgyptEthnicArabic.apply { isLooping = true } }
            game.navigationManager.navigate(MenuScreen::class.java.name)
        }
    }


}