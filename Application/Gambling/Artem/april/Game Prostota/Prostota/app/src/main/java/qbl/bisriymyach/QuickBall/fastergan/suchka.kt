package qbl.bisriymyach.QuickBall.fastergan

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import qbl.bisriymyach.QuickBall.LibGDXGame
import qbl.bisriymyach.QuickBall.hotvils.hrom
import qbl.bisriymyach.QuickBall.hotvils.Fronoton
import qbl.bisriymyach.QuickBall.tidams.cancelCoroutinesAll
import qbl.bisriymyach.QuickBall.tidams.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import qbl.bisriymyach.QuickBall.tidams.seichasiki
import qbl.bisriymyach.QuickBall.tidams.zavtra_v_shskull
import qbl.bisriymyach.QuickBall.tidams.piza
import qbl.bisriymyach.QuickBall.tidams.yatayaaaya
import qbl.bisriymyach.QuickBall.tidams.liza

abstract class suchka(
    val moNa: Float = liza,
    val Goga: Float = piza
) : ScreenAdapter(), seichasiki {

    abstract val game: LibGDXGame

    private val viewportBack by lazy {
        FillViewport(
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat()
        )
    }
    private val stageBack by lazy { zavtra_v_shskull(viewportBack) }

    val viewportUI by lazy { FitViewport(moNa, Goga) }
    val stageUI by lazy { zavtra_v_shskull(viewportUI) }

    fun setBackgrounds(backRegion: TextureRegion, uiRegion: TextureRegion = backRegion) {
        setBackBackground(backRegion)
        setUIBackground(uiRegion)
    }

    val inputMultiplexer = InputMultiplexer()

    val drawerUtil by lazy { fradel(stageUI.batch) }

    val fontGenerator_Jaldi = Fronoton(Fronoton.Companion.yyyyAAoap.Jaldi)

    override fun resize(width: Int, height: Int) {
        viewportBack.update(width, height, true)
        viewportUI.update(width, height, true)
    }


    override fun show() {
        stageBack.addAndFillActor(backBackgroundImage)
        stageUI.addAndFillActor(uiBackgroundImage)

        stageUI.addActorsOnStageUI()

        Gdx.input.inputProcessor = inputMultiplexer.apply { papkaproc(this@suchka, stageUI) }
        Gdx.input.setCatchKey(Input.Keys.BACK, true)
    }

    val backBackgroundImage = Image()
    val uiBackgroundImage = Image()
    val disposableSet = mutableSetOf<Disposable>()
    var coroutine: CoroutineScope? = CoroutineScope(Dispatchers.Default)
        private set

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.BACK -> {
                if (game.navigationManager.isBackStackEmpty()) game.navigationManager.exit()
                else stageUI.root.hrom(yatayaaaya) { game.navigationManager.back() }
            }
        }
        return true
    }

    open fun zavtra_v_shskull.addActorsOnStageUI() {}


    fun setBackBackground(region: TextureRegion) {
        backBackgroundImage.drawable = TextureRegionDrawable(region)
    }


    override fun render(delta: Float) {
        stageBack.render()
        stageUI.render()
        drawerUtil.update()
    }

    override fun dispose() {
        log("dispose AdvancedScreen: ${this::class.java.name.substringAfterLast('.')}")
        hshshshJ(
            stageBack, stageUI, drawerUtil, fontGenerator_Jaldi
        )
        disposableSet.hshshshJ()
        inputMultiplexer.clear()
        cancelCoroutinesAll(coroutine)
        coroutine = null
    }

    fun setUIBackground(texture: TextureRegion) {
        uiBackgroundImage.drawable = TextureRegionDrawable(texture)
    }


}