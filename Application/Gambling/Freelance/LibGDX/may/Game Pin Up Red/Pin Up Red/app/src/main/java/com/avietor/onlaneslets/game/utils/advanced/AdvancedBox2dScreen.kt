package com.avietor.onlaneslets.game.utils.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import com.avietor.onlaneslets.game.utils.Size
import com.avietor.onlaneslets.game.utils.SizeConverter
import com.avietor.onlaneslets.game.utils.disposeAll
import com.avietor.onlaneslets.game.box2d.WorldUtil

abstract class AdvancedBox2dScreen(
    val worldUtil: WorldUtil,
    val uiW  : Float = 701f,
    val uiH  : Float = 1401f,
    val boxW : Float = 30f,
    val boxH : Float = 60f,
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