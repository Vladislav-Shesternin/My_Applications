package novibet.leoforos.irakloiu.office.helper.game.utils.advanced

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import novibet.leoforos.irakloiu.office.helper.game.LibGDXGame
import novibet.leoforos.irakloiu.office.helper.game.utils.HEIGHT_UI
import novibet.leoforos.irakloiu.office.helper.game.utils.ShapeDrawerUtil
import novibet.leoforos.irakloiu.office.helper.game.utils.TIME_ANIM_SCREEN_ALPHA
import novibet.leoforos.irakloiu.office.helper.game.utils.WIDTH_UI
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animHide
import novibet.leoforos.irakloiu.office.helper.game.utils.addProcessors
import novibet.leoforos.irakloiu.office.helper.game.utils.disposeAll
import novibet.leoforos.irakloiu.office.helper.game.utils.font.FontGenerator
import novibet.leoforos.irakloiu.office.helper.game.utils.font.FontGenerator.FontPath
import novibet.leoforos.irakloiu.office.helper.util.cancelCoroutinesAll
import novibet.leoforos.irakloiu.office.helper.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import novibet.leoforos.irakloiu.office.helper.game.utils.runGDX

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

    val fontGeneratorInter_Black           = FontGenerator(FontPath.Inter_Black)
    val fontGeneratorLilitaOne_Regular     = FontGenerator(FontPath.LilitaOne_Regular)
    val fontGeneratorLondrinaSolid_Regular = FontGenerator(FontPath.LondrinaSolid_Regular)

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
            fontGeneratorInter_Black, fontGeneratorLilitaOne_Regular, fontGeneratorLondrinaSolid_Regular
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