package newyearpuz.lessons.forditky.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import newyearpuz.lessons.forditky.game.LibGDXGame
import newyearpuz.lessons.forditky.game.manager.MusicManager
import newyearpuz.lessons.forditky.game.manager.SoundManager
import newyearpuz.lessons.forditky.game.manager.SpriteManager
import newyearpuz.lessons.forditky.game.utils.actor.setBounds
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedScreen
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedStage
import newyearpuz.lessons.forditky.game.utils.font.FontParameter
import newyearpuz.lessons.forditky.game.utils.runGDX
import newyearpuz.lessons.forditky.util.log
import newyearpuz.lessons.forditky.game.utils.Layout.Sepelesesr as LL

class PuzzleSplashScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val progressLabel  by lazy { Label("", Label.LabelStyle(fPokerka.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS.chars+"%").setSize(135)), Color.WHITE)) }

    override fun show() {
        loadSplashAssets()
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
        addActor(progressLabel)
        progressLabel.setBounds(LL.porog)
        progressLabel.setAlignment(Align.right)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(game.spriteManager) {
//            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.LOADING.data)
//            loadAtlas()
//            loadableTextureList = mutableListOf(
//                SpriteManager.EnumTexture.PROGIGRESS_MASKARADJA.data,
//                SpriteManager.EnumTexture.SUPER_PUPER_BACKGROUND.data,
//            )
//            loadTexture()
//        }
//        game.assetManager.finishLoading()
//        game.spriteManager.initAtlasAndTexture()
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
//                        loading.setProgressPercent(progress.toFloat())
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

            game.activity.lottie.hideLoader()
            game.musicUtil.apply { music = musika.apply { isLooping = true } }
            game.navigationManager.navigate(PuzzleMenuScreen::class.java.name)
        }
    }


}