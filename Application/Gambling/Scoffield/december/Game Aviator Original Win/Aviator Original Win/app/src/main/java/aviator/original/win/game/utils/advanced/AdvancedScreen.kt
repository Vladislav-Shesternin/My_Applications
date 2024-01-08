package aviator.original.win.game.utils.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import aviator.original.win.game.LibGDXGame
import aviator.original.win.game.utils.HEIGHT_UI
import aviator.original.win.game.utils.ShapeDrawerUtil
import aviator.original.win.game.utils.TIME_ANIM_SCREEN_ALPHA
import aviator.original.win.game.utils.WIDTH_UI
import aviator.original.win.game.utils.actor.animHide
import aviator.original.win.game.utils.addProcessors
import aviator.original.win.game.utils.disposeAll
import aviator.original.win.game.utils.font.FontGenerator
import aviator.original.win.game.utils.font.FontGenerator.Companion.FontPath
import aviator.original.win.game.utils.runGDX
import aviator.original.win.util.cancelCoroutinesAll
import aviator.original.win.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AdvancedScreen(
    val WIDTH : Float = WIDTH_UI,
    val HEIGHT: Float = HEIGHT_UI
) : ScreenAdapter(), AdvancedInputProcessor {

    abstract val game: LibGDXGame

    private val viewportBack by lazy { FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()) }
    private val stageBack    by lazy { AdvancedStage(viewportBack) }

    val viewportUI by lazy { FitViewport(WIDTH, HEIGHT) }
    val stageUI    by lazy { AdvancedStage(viewportUI) }

    val inputMultiplexer    = InputMultiplexer()
    val backBackgroundImage = Image()
    val uiBackgroundImage   = Image()
    val disposableSet       = mutableSetOf<Disposable>()
    var coroutine: CoroutineScope? = CoroutineScope(Dispatchers.Default)
        private set

    val drawerUtil by lazy { ShapeDrawerUtil(stageUI.batch) }

    val fontGenerator_Bold      = FontGenerator(FontPath.NeueMachinaBold)
    val fontGenerator_UltraBold = FontGenerator(FontPath.NeueMachinaUltrabold)

    override fun resize(width: Int, height: Int) {
        viewportBack.update(width, height, true)
        viewportUI.update(width, height, true)
    }

    override fun show() {
        game.activity.webViewFragment.backBlock = { runGDX { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }}}

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
            stageBack, stageUI, drawerUtil,
            fontGenerator_Bold, fontGenerator_UltraBold
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


    fun setBackBackground(region: TextureRegion) {
        backBackgroundImage.drawable = TextureRegionDrawable(region)
    }

    fun setUIBackground(texture: TextureRegion) {
        uiBackgroundImage.drawable = TextureRegionDrawable(texture)
    }

    fun setBackgrounds(backRegion: TextureRegion, uiRegion: TextureRegion = backRegion) {
        setBackBackground(backRegion)
        setUIBackground(uiRegion)
    }

}