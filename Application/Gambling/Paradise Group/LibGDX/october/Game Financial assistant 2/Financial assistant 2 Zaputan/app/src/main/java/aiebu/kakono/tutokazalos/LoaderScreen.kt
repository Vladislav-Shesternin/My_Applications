package aiebu.kakono.tutokazalos

import aiebu.kakono.tutokazalos.parmengo.DataStoreManager
import aiebu.kakono.tutokazalos.soloha.pisoha.TIME_ANIM_SCREEN_ALPHA
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.SpriteManager
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.animHide
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedScreen
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedStage
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.setOnClickListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow = MutableStateFlow(0f)
    private var isFinishLoading = false
    private var isFinishProgress = false
    private var isFinishAnim = false

//    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
//    private val parameter = FreeTypeFontParameter()

//    private val progressLabel by lazy { Label("", Label.LabelStyle(generator.generateFont(parameter.setCharacters(NUMBERS.chars+"%").setSize(58)), Color.BLUE)) }

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

//    override fun dispose() {
//        super.dispose()
//        generator.dispose()
//    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgress() {
//        addActor(progressLabel)
//        progressLabel.apply {
//            setBounds(aiebu.kakono.tutokazalos.game.utils.Layout.Loader.progress)
//            setAlignment(Align.center)
//        }
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
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
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
//                    runGDX { progressLabel.setText("$progress%") }
//                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((5..10).random().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.activity.lottie.hideLoader()

            coroutine?.launch(Dispatchers.IO) {
                if (DataStoreManager.IsDialog.get() == true) {
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                        game.navigationManager.navigate(VstrechaScreen::class.java.name)

                    }
                } else {
                    addImki()
                }

            }
        }
    }

    private fun addImki() {
        val ggA = Image(game.spriteUtil.gg1)
        val xxB = Image(game.spriteUtil.popa)

        stageUI.addActors(ggA, xxB)
        xxB.apply {
            touchable = Touchable.disabled
            setBounds(1f, 152f, 720f, 1445f)
            addAction(Actions.alpha(0f))
            setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch { DataStoreManager.IsDialog.update { true } }

                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(VstrechaScreen::class.java.name)
                }
            }
        }

        ggA.apply {
            setBounds(1f, 152f, 720f, 1445f)
            setOnClickListener {
                ggA.addAction(
                    Actions.sequence(
                        Actions.fadeOut(0.3f),
                        Actions.run {
                            touchable = Touchable.disabled
                            xxB.addAction(Actions.fadeIn(0.5f))
                            xxB.touchable = Touchable.enabled
                        }
                    ))
            }
        }
    }

}