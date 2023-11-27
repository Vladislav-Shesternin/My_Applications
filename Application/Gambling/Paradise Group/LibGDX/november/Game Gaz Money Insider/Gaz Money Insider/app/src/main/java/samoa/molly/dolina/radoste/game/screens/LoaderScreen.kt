package samoa.molly.dolina.radoste.game.screens

import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import samoa.molly.dolina.radoste.game.LibGDXGame
import samoa.molly.dolina.radoste.game.manager.SpriteManager
import samoa.molly.dolina.radoste.game.utils.TIME_ANIM_SCREEN_ALPHA
import samoa.molly.dolina.radoste.game.utils.actor.animHide
import samoa.molly.dolina.radoste.game.utils.actor.setOnClickListener
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedScreen
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedStage
import samoa.molly.dolina.radoste.util.DataStoreManager
import samoa.molly.dolina.radoste.util.log

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    override fun show() {
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
//        addProgress()
        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

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
                    if (progress % 100 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    //delay(16)
                    //delay((2..5).shuffled().first().toLong())
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
                        game.navigationManager.navigate(MenuScreen::class.java.name)

                    }
                } else {
                    addXoxoxo()
                }

            }
        }
    }

    private fun addXoxoxo() {
        val ggA = Image(game.assets.cList[0])
        val xxB = Image(game.assets.cList[1])

        stageUI.addActors(ggA, xxB)
        xxB.apply {
            touchable = Touchable.disabled
            setBounds(86f,559f, 453f, 270f)
            addAction(Actions.alpha(0f))
            setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch { DataStoreManager.IsDialog.update { true } }

                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(MenuScreen::class.java.name)
                }
            }
        }

        ggA.apply {
            setBounds(86f,559f, 453f, 270f)
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