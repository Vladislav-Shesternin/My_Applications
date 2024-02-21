package com.fellinger.yeasman.game.utils.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import com.fellinger.yeasman.game.utils.Size
import com.fellinger.yeasman.game.utils.SizeConverter
import com.fellinger.yeasman.game.utils.disposeAll
import com.fellinger.yeasman.game.box2d.WorldUtil

abstract class AdvancedBox2dScreen(
    val worldUtil: WorldUtil,
    val uiW  : Float = 1920f,
    val uiH  : Float = 1080f,
    val boxW : Float = 30f,
    val boxH : Float = 16.875f,
): AdvancedScreen(uiW, uiH) {

    private val viewportBox2d by lazy { FitViewport(boxW, boxH) }

    val sizeConverterUIToBox = SizeConverter(Size(uiW, uiH), Size(boxW, boxH))
    val sizeConverterBoxToUI = SizeConverter(Size(boxW, boxH), Size(uiW, uiH))



    override fun resize(width: Int, height: Int) {
        viewportBox2d.update(width, height, true)
    }

    override fun render(delta: Float) {
        super.render(delta)
        worldUtil.update(delta)
        worldUtil.debug(viewportBox2d.camera.combined)
    }

    override fun dispose() {
        super.dispose()
        disposeAll(worldUtil)
    }

}