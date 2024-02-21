package com.elastices.platferma.game.utils.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import com.elastices.platferma.game.utils.Size
import com.elastices.platferma.game.utils.SizeConverter
import com.elastices.platferma.game.utils.disposeAll
import com.elastices.platferma.game.box2d.WorldUtil

abstract class AdvancedBox2dScreen(
    val worldUtil: WorldUtil,
    val uiW  : Float = 1400f,
    val uiH  : Float = 700f,
    val boxW : Float = 50f,
    val boxH : Float = 25f,
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