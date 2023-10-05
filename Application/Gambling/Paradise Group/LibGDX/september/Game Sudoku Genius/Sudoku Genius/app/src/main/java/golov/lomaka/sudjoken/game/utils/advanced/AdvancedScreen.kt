package golov.lomaka.sudjoken.game.utils.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import golov.lomaka.sudjoken.game.LibGDXGame
import golov.lomaka.sudjoken.game.utils.HEIGHT_UI
import golov.lomaka.sudjoken.game.utils.ShapeDrawerUtil
import golov.lomaka.sudjoken.game.utils.WIDTH_UI
import golov.lomaka.sudjoken.game.utils.actor.animHiden
import golov.lomaka.sudjoken.game.utils.addProcessors
import golov.lomaka.sudjoken.game.utils.disposeAll
import golov.lomaka.sudjoken.game.utils.runGDX
import golov.lomaka.sudjoken.util.cancelCoroutinesAll
import golov.lomaka.sudjoken.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AdvancedScreen(
    val WIDTH : Float = WIDTH_UI,
    val HEIGHT: Float = HEIGHT_UI
) : ScreenAdapter(), AdvancedInputProcessor {

    abstract val game: LibGDXGame
    val name: String = javaClass.name

    private val viewportBack by lazy { FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) }
    private val stageBack    by lazy { AdvancedStage(viewportBack) }

    val viewportUI by lazy { FitViewport(WIDTH, HEIGHT) }
    val stageUI    by lazy { AdvancedStage(viewportUI) }

    val inputMultiplexer    = InputMultiplexer()
    val backBackgroundImage = Image()
    val uiBackgroundImage   = Image()

    var coroutine: CoroutineScope? = CoroutineScope(Dispatchers.Default)
        private set

    val drawerUtil   by lazy { ShapeDrawerUtil(stageUI.batch) }


    override fun show() {
        game.activity.webViewFragment.also { web -> web.backBlock = {
            if (web.isVisible) web.goneWebView() else runGDX { stageUI.root.animHiden(0.3f) { game.navigationManager.back() } }
        } }

        stageBack.addActor(backBackgroundImage)
        stageUI.addActor(uiBackgroundImage)

        stageUI.addActorsOnStageUI()

        Gdx.input.inputProcessor = inputMultiplexer.apply { addProcessors(this@AdvancedScreen, stageUI) }
        //Gdx.input.setCatchKey(Input.Keys.BACK, true)
    }

    override fun resize(width: Int, height: Int) {
        viewportBack.update(width, height, true)
        viewportUI.update(width, height, true)
    }

    override fun render(delta: Float) {
        stageBack.render()
        stageUI.render()
        drawerUtil.update()
    }

    override fun dispose() {
        log("dispose AdvancedScreen")
        cancelCoroutinesAll(coroutine)
        coroutine = null
        disposeAll(stageBack, stageUI, drawerUtil)
        inputMultiplexer.clear()
    }

//    override fun keyDown(keycode: Int): Boolean {
//        if (keycode == Input.Keys.BACK) {
//            if (game.navigationManager.isBackStackEmpty()) game.navigationManager.exit()
//            else stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.back() }
//        }
//        return super.keyDown(keycode)
//    }

    open fun AdvancedStage.addActorsOnStageUI() {}


    fun setBackBackground(region: TextureRegion) {
        backBackgroundImage.apply {
            drawable = TextureRegionDrawable(region)
            setSize(viewportBack.worldWidth, viewportBack.worldHeight)
        }
    }

    fun setUIBackground(texture: TextureRegion) {
        uiBackgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(WIDTH, HEIGHT)
        }
    }

    fun setBackgrounds(backRegion: TextureRegion, uiRegion: TextureRegion = backRegion) {
        setBackBackground(backRegion)
        setUIBackground(uiRegion)
    }

}