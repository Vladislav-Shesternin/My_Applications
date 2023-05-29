package aer.com.gamesas.mobile.slot.game.utils.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import aer.com.gamesas.mobile.slot.game.utils.Size
import aer.com.gamesas.mobile.slot.game.utils.SizeConverter
import aer.com.gamesas.mobile.slot.game.utils.disposeAll
import aer.com.gamesas.mobile.slot.game.box2d.WorldUtil

abstract class AdvancedBox2dScreen(
    val worldUtil: WorldUtil,
    val uiW  : Float = 708f,
    val uiH  : Float = 1540f,
    val boxW : Float = 10f,
    val boxH : Float = 21.7514f,
): AdvancedScreen(uiW, uiH) {

    private val viewportBox2d by lazy { FitViewport(boxW, boxH) }

    val sizeConverterUIToBox = SizeConverter(Size(uiW, uiH), Size(boxW, boxH))
    val sizeConverterBoxToUI = SizeConverter(Size(boxW, boxH), Size(uiW, uiH))



    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        viewportBox2d.update(width, height, true)
    }

    override fun render(delta: Float) {
        super.render(delta)
        worldUtil.update(delta)
        worldUtil.debug(viewportBox2d.camera.combined)
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        super.dispose()
        disposeAll(worldUtil)
    }

}