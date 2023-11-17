package aiebu.kakono.tutokazalos.soloha.pisoha.front

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FitViewport
import aiebu.kakono.tutokazalos.LibGDXGame
import aiebu.kakono.tutokazalos.soloha.pisoha.HEIGHT_UI
import aiebu.kakono.tutokazalos.soloha.pisoha.ShapeDrawerUtil
import aiebu.kakono.tutokazalos.soloha.pisoha.TIME_ANIM_SCREEN_ALPHA
import aiebu.kakono.tutokazalos.soloha.pisoha.WIDTH_UI
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.AdvancedInputProcessor
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.animHide
import aiebu.kakono.tutokazalos.soloha.pisoha.addProcessors
import aiebu.kakono.tutokazalos.soloha.pisoha.disposeAll
import aiebu.kakono.tutokazalos.soloha.pisoha.runGDX
import aiebu.kakono.tutokazalos.parmengo.cancelCoroutinesAll
import aiebu.kakono.tutokazalos.parmengo.log
import com.badlogic.gdx.Input
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


        stageUI.addActor(uiBackgroundImage)

        stageUI.addActorsOnStageUI()

        Gdx.input.inputProcessor = inputMultiplexer.apply { addProcessors(this@AdvancedScreen, stageUI) }
        Gdx.input.setCatchKey(Input.Keys.BACK, true)

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

    override fun keyDown(keycode: Int): Boolean {
        when(keycode) {
            Input.Keys.BACK -> {
                if (game.navigationManager.isBackStackEmpty()) game.navigationManager.exit()
                else stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
            }
        }
        return true
    }

    open fun AdvancedStage.addActorsOnStageUI() {}

    fun setBackground(texture: TextureRegion) {
        uiBackgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(WIDTH, HEIGHT)
        }
    }

}