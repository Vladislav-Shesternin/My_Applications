package com.invt.nard.app.game.utils.advanced

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport
import com.invt.nard.app.game.utils.Size
import com.invt.nard.app.game.utils.SizeConverter
import com.invt.nard.app.game.utils.disposeAll
import com.invt.nard.app.game.box2d.WorldUtil

abstract class AdvancedBox2dScreen(
    val worldUtil: WorldUtil,
    val uiW  : Float = 1470f,
    val uiH  : Float = 742f,
    val boxW : Float = 19.81f,
    val boxH : Float = 10f,
): AdvancedScreen(uiW, uiH) {

    private val viewportBox2d by lazy { FitViewport(boxW, boxH) }

    val sizeConverterUIToBox = SizeConverter(Size(uiW, uiH), Size(boxW, boxH))
    val sizeConverterBoxToUI = SizeConverter(Size(boxW, boxH), Size(uiW, uiH))

    val Size.toBoxSize   : Size    get() = sizeConverterUIToBox.getSize(this)
    val Vector2.toBoxSize: Vector2 get() = sizeConverterUIToBox.getSize(this)



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