package hekeur.buzzniess.megaglorr.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import hekeur.buzzniess.megaglorr.game.LibGDXGame
import hekeur.buzzniess.megaglorr.game.utils.TIME_ANIM_SCREEN_ALPHA
import hekeur.buzzniess.megaglorr.game.utils.actor.animHide
import hekeur.buzzniess.megaglorr.game.utils.actor.setBounds
import hekeur.buzzniess.megaglorr.game.utils.advanced.AdvancedScreen
import hekeur.buzzniess.megaglorr.game.utils.advanced.AdvancedStage
import hekeur.buzzniess.megaglorr.game.utils.font.CharType.*
import hekeur.buzzniess.megaglorr.game.utils.font.FontPath
import hekeur.buzzniess.megaglorr.game.utils.font.setCharacters
import hekeur.buzzniess.megaglorr.game.utils.font.setSize
import hekeur.buzzniess.megaglorr.game.utils.runGDX
import hekeur.buzzniess.megaglorr.game.manager.SpriteManager
import hekeur.buzzniess.megaglorr.game.utils.Layout.Flatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.ExtraBold))
    private val parameter = FreeTypeFontParameter()
    private val font      = generator.generateFont(parameter.setCharacters(NUMBERS.chars+"%").setSize(40))

    private val progressLabel = Label("", Label.LabelStyle(font, Color.WHITE))

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

    override fun dispose() {
        super.dispose()
        generator.dispose()
        font.dispose()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(Flatter.gomerus)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
//        with(game.fontTTFManager) {
//            loadableFontList = mutableListOf(game.fontTTFUtil.font_Inter_ExtraBold_100)
//            load()
//        }
//        game.assetManager.finishLoading()
//        game.fontTTFManager.init()
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
    }

    private fun initAssets() {
        game.spriteManager.initTexture()
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.assetManager.update(17)) {
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
                    runGDX { progressLabel.setText("$progress%") }
//                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((8..14).random().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(BrabusekScreen::class.java.name)
            }
        }
    }

}