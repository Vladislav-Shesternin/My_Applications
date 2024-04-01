package com.cosmo.plinko.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import com.cosmo.plinko.game.LibGDXGame
import com.cosmo.plinko.game.manager.MusicManager
import com.cosmo.plinko.game.manager.SoundManager
import com.cosmo.plinko.game.manager.SpriteManager
import com.cosmo.plinko.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.cosmo.plinko.game.utils.actor.animHide
import com.cosmo.plinko.game.utils.actor.setBounds
import com.cosmo.plinko.game.utils.advanced.AdvancedScreen
import com.cosmo.plinko.game.utils.advanced.AdvancedStage
import com.cosmo.plinko.game.utils.font.FontParameter
import com.cosmo.plinko.game.utils.font.FontParameter.CharType
import com.cosmo.plinko.game.utils.runGDX
import com.cosmo.plinko.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.cosmo.plinko.game.actors.AKomete
import com.cosmo.plinko.game.actors.AMusor
import com.cosmo.plinko.game.actors.progress.AProgress
import com.cosmo.plinko.game.utils.region
import java.util.Random
import com.cosmo.plinko.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorMachineGunk.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"LOADING%").setSize(44)), Color.WHITE)) }
    private val progressIII   by lazy { AProgress(this@LoaderScreen) }

    override fun show() {
        loadSplashAssets()
        setBackgrounds(game.splashAssets.BACGROUND.region)
        game.activity.lottie.hideLoader()
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
        addProgressSees()
        addProgress()
        addPalanet()
        addKomete()
        addMusor()
        addLuna()
        addI()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addProgressSees() {
        addActor(progressIII)
        progressIII.setBounds(LS.prolog)
        progressIII.setProgressPercent(0f)
    }

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.progress)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedStage.addKomete() {
        val palanet = AKomete(this@LoaderScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMusor() {
        val palanet = AMusor(this@LoaderScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addPalanet() {
        val palanet = Image(game.splashAssets.PALANET)
        addActor(palanet)
        palanet.setBounds(LS.palanet)
    }

    private fun AdvancedStage.addLuna() {
        val palanet = Image(game.splashAssets.LUNA)
        addActor(palanet)
        palanet.setBounds(LS.luna)
        palanet.setOrigin(Align.center)
        palanet.addAction(Actions.forever(Actions.sequence(
            Actions.scaleBy(-0.2f, -0.2f, 0.4f),
            Actions.scaleBy(0.2f, 0.2f, 0.4f),
        )))
    }

    private fun AdvancedStage.addI() {
        game.splashAssets.I_LIST.onEachIndexed { index, textureRegion ->
            if (index != 1) {
                val i = Image(textureRegion)

                addActor(i)
                i.setBounds(LS.listI[index])
                i.setOrigin(Align.center)
                i.addAction(Actions.forever(Actions.rotateBy(if (Random().nextBoolean()) -360f else 360f, 5f)))
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.SPLASH.data)
            loadAtlas()
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.BACKGROUND.data,
                SpriteManager.EnumTexture.MASK.data,
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
                    runGDX {
                        progressIII.setProgressPercent(progress.toFloat())
                        progressLabel.setText("LOADING $progress%")
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

            game.musicUtil.apply { music = MUSIC.apply { isLooping = true } }

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.navigate(MenuScreen::class.java.name)
            }
        }
    }


}