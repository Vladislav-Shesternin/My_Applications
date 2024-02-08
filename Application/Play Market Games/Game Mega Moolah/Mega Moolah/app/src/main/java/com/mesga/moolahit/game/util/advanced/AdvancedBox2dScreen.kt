package com.mesga.moolahit.game.util.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import com.mesga.moolahit.game.util.Size
import com.mesga.moolahit.game.util.SizeConverter
import com.mesga.moolahit.game.util.disposeAll
import com.mesga.moolahit.game.box2d.WorldUtil

abstract class AdvancedBox2dScreen(
    val uiW  : Float = 1400f,
    val uiH  : Float = 700f,
    val boxW : Float = 100f,
    val boxH : Float = 50f,
): AdvancedScreen(uiW, uiH) {

    private val viewportBox2d by lazy { FitViewport(boxW, boxH) }

    val sizeConverterUIToBox = SizeConverter(Size(uiW, uiH), Size(boxW, boxH))
    val sizeConverterBoxToUI = SizeConverter(Size(boxW, boxH), Size(uiW, uiH))



    override fun resize(width: Int, height: Int) {
        viewportBox2d.update(width, height, true)
    }

    override fun render(delta: Float) {
        super.render(delta)
        WorldUtil.update(delta)
        WorldUtil.debug(viewportBox2d.camera.combined)
    }

    override fun dispose() {
        super.dispose()
        disposeAll(WorldUtil)
    }

}