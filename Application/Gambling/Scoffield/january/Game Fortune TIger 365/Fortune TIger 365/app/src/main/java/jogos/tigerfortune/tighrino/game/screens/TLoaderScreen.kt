package jogos.tigerfortune.tighrino.game.screens

import jogos.tigerfortune.tighrino.game.LibGDXGame
import jogos.tigerfortune.tighrino.game.actors.progress.AProgressBar
import jogos.tigerfortune.tighrino.game.manager.MusicManager
import jogos.tigerfortune.tighrino.game.manager.SoundManager
import jogos.tigerfortune.tighrino.game.manager.SpriteManager
import jogos.tigerfortune.tighrino.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogos.tigerfortune.tighrino.game.utils.actor.animHide
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedStage
import jogos.tigerfortune.tighrino.game.utils.region
import jogos.tigerfortune.tighrino.game.utils.runGDX
import jogos.tigerfortune.tighrino.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TLoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val progressBar by lazy { AProgressBar(this) }

    override fun show() {
        loadSplashAssets()
        setBackgrounds(game.splashAssets.TLOAD.region)
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
        addProgress()

        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgress() {
        addActor(progressBar)
        progressBar.setBounds(77f, 1114f, 927f, 79f)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
//            loadableAtlasList   = mutableListOf(SpriteManager.EnumAtlas.SPLASH.data)
//            loadAtlas()
            loadableTextureList = mutableListOf(
                SpriteManager.EnumTexture.TLOAD.data,
                SpriteManager.EnumTexture.LOADER.data,
                SpriteManager.EnumTexture.MASK.data,
            )
            loadTexture()
        }
        game.assetManager.finishLoading()
        game.spriteManager.initTexture()
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
                        progressBar.setProgressPercent(progress.toFloat())
//                        progressLbl.setText("$progress%")
                    }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

//                    delay((15..30).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.musicUtil.apply { music = MUSIC.apply { isLooping = true } }

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.navigate(TMenuScreen::class.java.name)
            }
        }
    }


}