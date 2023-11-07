package mst.mysteryof.egyptest.game.utils.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import mst.mysteryof.egyptest.game.LibGDXGame
import mst.mysteryof.egyptest.game.utils.HEIGHT_UI
import mst.mysteryof.egyptest.game.utils.ShapeDrawerUtil
import mst.mysteryof.egyptest.game.utils.TIME_ANIM_SCREEN_ALPHA
import mst.mysteryof.egyptest.game.utils.WIDTH_UI
import mst.mysteryof.egyptest.game.utils.actor.animHide
import mst.mysteryof.egyptest.game.utils.addProcessors
import mst.mysteryof.egyptest.game.utils.disposeAll
import mst.mysteryof.egyptest.game.utils.font.FontGenerator
import mst.mysteryof.egyptest.game.utils.font.FontGenerator.FontPath
import mst.mysteryof.egyptest.util.cancelCoroutinesAll
import mst.mysteryof.egyptest.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import mst.mysteryof.egyptest.game.utils.runGDX

abstract class AdvancedScreen(
    val WIDTH : Float = WIDTH_UI,
    val HEIGHT: Float = HEIGHT_UI
) : ScreenAdapter(), AdvancedInputProcessor {

    abstract val game: LibGDXGame

    val viewportBack = FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    val stageBack    = AdvancedStage(viewportBack)

    val viewportUI = FitViewport(WIDTH, HEIGHT)
    val stageUI    = AdvancedStage(viewportUI)

    val inputMultiplexer    = InputMultiplexer()
    val backBackgroundImage = Image()
    val uiBackgroundImage   = Image()
    val disposableSet       = mutableSetOf<Disposable>()

    var coroutine: CoroutineScope? = CoroutineScope(Dispatchers.Default)
        private set

    val drawerUtil by lazy { ShapeDrawerUtil(stageUI.batch) }

    val fontGeneratorKaushanScript_Regular = FontGenerator(FontPath.KaushanScript_Regular)

    override fun resize(width: Int, height: Int) {
        viewportBack.update(width, height, true)
        viewportUI.update(width, height, true)
    }

    override fun show() {

        game.activity.webViewFragment.also { web -> web.backBlock = {
            if (web.isVisible) web.goneWebView() else runGDX {
                if (game.navigationManager.isBackStackEmpty()) game.navigationManager.exit()
                else stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
            }
        } }

        stageBack.addAndFillActor(backBackgroundImage)
        stageUI.addAndFillActor(uiBackgroundImage)
        stageUI.addActorsOnStageUI()

        Gdx.input.inputProcessor = inputMultiplexer.apply { addProcessors(this@AdvancedScreen, stageUI) }
//        Gdx.input.setCatchKey(Input.Keys.BACK, true)

    }

    override fun render(delta: Float) {
        stageBack.render()
        stageUI.render()
        drawerUtil.update()
    }

    override fun dispose() {
        log("dispose AdvancedScreen: ${this::class.java.name.substringAfterLast('.')}")
        disposeAll(
            stageUI, drawerUtil,
            fontGeneratorKaushanScript_Regular
        )
        disposableSet.disposeAll()
        inputMultiplexer.clear()
        cancelCoroutinesAll(coroutine)
        coroutine = null
    }

//    override fun keyDown(keycode: Int): Boolean {
//        when(keycode) {
//            Input.Keys.BACK -> {
//                if (game.navigationManager.isBackStackEmpty()) game.navigationManager.exit()
//                else stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
//            }
//        }
//        return true
//    }

    open fun AdvancedStage.addActorsOnStageUI() {}

    fun setBackground(region: TextureRegion) {
        backBackgroundImage.drawable = TextureRegionDrawable(region)
        uiBackgroundImage.drawable   = TextureRegionDrawable(region)
    }

}