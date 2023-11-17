package italodisco.fernando.lucherano.iopartew.pppp098.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FitViewport
import italodisco.fernando.lucherano.iopartew.opOPa
import italodisco.fernando.lucherano.iopartew.sandes.Olopo
import italodisco.fernando.lucherano.iopartew.pppp098.ShapeDrawerUtil
import italodisco.fernando.lucherano.iopartew.sandes.KoloVo
import italodisco.fernando.lucherano.iopartew.sandes.sarana
import italodisco.fernando.lucherano.iopartew.sandes.pistro.paLetRa
import italodisco.fernando.lucherano.iopartew.pppp098.actor.animHide
import italodisco.fernando.lucherano.iopartew.pppp098.addProcessors
import italodisco.fernando.lucherano.iopartew.pppp098.YUAUAUAUAUUURR4
import italodisco.fernando.lucherano.iopartew.pppp098.runGDX
import italodisco.fernando.lucherano.iopartew.sandes.pistro.cancelCoroutinesAll
import italodisco.fernando.lucherano.iopartew.sandes.pistro.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AdvancedScreen(
    val WIDTH : Float = sarana,
    val HEIGHT: Float = Olopo
) : ScreenAdapter(), AdvancedInputProcessor {

    abstract val game: opOPa

    val viewportUI by lazy { FitViewport(WIDTH, HEIGHT) }
    val stageUI    by lazy { paLetRa(viewportUI) }

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
        YUAUAUAUAUUURR4(stageUI, drawerUtil)
        disposableSet.YUAUAUAUAUUURR4()
        inputMultiplexer.clear()
        cancelCoroutinesAll(coroutine)
        coroutine = null
    }

    override fun keyDown(keycode: Int): Boolean {
        when(keycode) {
            Input.Keys.BACK -> {
                if (game.YTARAT.isBackStackEmpty()) game.YTARAT.exit()
                else stageUI.root.animHide(KoloVo) { game.YTARAT.back() }
            }
        }
        return true
    }

    open fun paLetRa.addActorsOnStageUI() {}

    fun setBackground(texture: TextureRegion) {
        uiBackgroundImage.apply {
            drawable = TextureRegionDrawable(texture)
            setSize(WIDTH, HEIGHT)
        }
    }

}