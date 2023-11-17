package rateflow.procurrency.exchelonmaster.game.utils.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FitViewport
import rateflow.procurrency.exchelonmaster.game.LibGDXGame
import rateflow.procurrency.exchelonmaster.game.utils.HEIGHT_UI
import rateflow.procurrency.exchelonmaster.game.utils.ShapeDrawerUtil
import rateflow.procurrency.exchelonmaster.game.utils.TIME_ANIM_SCREEN_ALPHA
import rateflow.procurrency.exchelonmaster.game.utils.WIDTH_UI
import rateflow.procurrency.exchelonmaster.game.utils.actor.animHide
import rateflow.procurrency.exchelonmaster.game.utils.addProcessors
import rateflow.procurrency.exchelonmaster.game.utils.disposeAll
import rateflow.procurrency.exchelonmaster.game.utils.runGDX
import rateflow.procurrency.exchelonmaster.util.cancelCoroutinesAll
import rateflow.procurrency.exchelonmaster.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AdvancedScreen(
    val WIDTH : Float = WIDTH_UI,
    val HEIGHT: Float = HEIGHT_UI
) : ScreenAdapter(), AdvancedInputProcessor {

    abstract val game: LibGDXGame

    val viewportUI by lazy { FitViewport(WIDTH, HEIGHT) }
    val stageUI    by lazy { AdvancedStage(viewportUI) }

    val inputMultiplexer    = InputMultiplexer()
    val uiBackgroundImage   = Image()
    val disposableSet       = mutableSetOf<Disposable>()

    var coroutine: CoroutineScope? = CoroutineScope(Dispatchers.Default)
        private set

    val drawerUtil by lazy { ShapeDrawerUtil(stageUI.batch) }

    override fun resize(width: Int, height: Int) {
        viewportUI.update(width, height, true)
    }

    override fun show() {
//        game.activity.rebView.also { web -> web.backBlock = {
//            if (web.isVisible) web.goneWebView() else runGDX {
//                if (game.navigationManager.isBackStackEmpty()) game.navigationManager.exit()
//                else stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
//            }
//        } }

        stageUI.addActor(uiBackgroundImage)

        stageUI.addActorsOnStageUI()

        Gdx.input.inputProcessor = inputMultiplexer.apply { addProcessors(this@AdvancedScreen, stageUI) }
        //Gdx.input.setCatchKey(Input.Keys.BACK, true)

        viewportUI.apply(true)
    }

    override fun render(delta: Float) {
        stageUI.render()
        drawerUtil.update()
    }

    override fun dispose() {
        log("dispose AdvancedScreen: ${this::class.java.name}")
        disposeAll(stageUI, drawerUtil)
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

    fun setBackground(texture: TextureRegion) {
        uiBackgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(WIDTH, HEIGHT)
        }
    }

}