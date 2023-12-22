package mobile.jogodobicho.lucky.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mobile.jogodobicho.lucky.game.LibGDXGame
import mobile.jogodobicho.lucky.game.actors.progress.ALoadingLoading
import mobile.jogodobicho.lucky.game.manager.MusicManager
import mobile.jogodobicho.lucky.game.manager.SoundManager
import mobile.jogodobicho.lucky.game.manager.SpriteManager
import mobile.jogodobicho.lucky.game.utils.TIME_ANIM_SCREEN_ALPHA
import mobile.jogodobicho.lucky.game.utils.actor.animHide
import mobile.jogodobicho.lucky.game.utils.actor.animShow
import mobile.jogodobicho.lucky.game.utils.actor.setBounds
import mobile.jogodobicho.lucky.game.utils.actor.setOnClickListener
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedStage
import mobile.jogodobicho.lucky.game.utils.font.FontParameter
import mobile.jogodobicho.lucky.game.utils.region
import mobile.jogodobicho.lucky.game.utils.runGDX
import mobile.jogodobicho.lucky.util.log
import mobile.jogodobicho.lucky.game.utils.Layout.Loading as LL

class BullLoadingScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val progressLabel  by lazy { Label("", Label.LabelStyle(fontJejuHallasan_Regular.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS.chars+"%").setSize(73)), Color.WHITE)) }
    private val babochkeImg    by lazy { Image(game.splashAssets.loading_babochke) }
    private val bullImg        by lazy { Image(game.splashAssets.bull) }
    private val playImg        by lazy { Image(game.splashAssets.loadingPlay).apply { color.a = 0f } }
    private val loading        by lazy { ALoadingLoading(this) }


    override fun show() {
        loadSplashAssets()
        game.activity.lottie.hideLoader()
        setBackgrounds(game.splashAssets.SUPER_PUPER_BACKGROUND.region)
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
        addBab()
        addBull()
        addPlay()
        addLoading()
        addProgress()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBab() {
        addActor(babochkeImg)
        babochkeImg.setBounds(LL.bab)
    }

    private fun AdvancedStage.addBull() {
        addActor(bullImg)
        bullImg.setBounds(LL.bull)
    }

    private fun AdvancedStage.addPlay() {
        addActor(playImg)
        playImg.setBounds(LL.play)
        playImg.setOnClickListener {
            if (isFinishProgress) {
                game.musicUtil.apply { music = simple_piano_melody.apply { isLooping = true } }

                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(BullMenuScreen::class.java.name)
                }
            }
        }
    }

    private fun AdvancedStage.addLoading() {
        addActor(loading)
        loading.setBounds(LL.loading)
    }

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.setBounds(LL.progres)
        progressLabel.setAlignment(Align.right)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.LOADING.data)
            loadAtlas()
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.PROGIGRESS_MASKARADJA.data,
                SpriteManager.EnumTexture.SUPER_PUPER_BACKGROUND.data,
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

            playImg.animShow(TIME_ANIM_SCREEN_ALPHA)
        }
    }


}