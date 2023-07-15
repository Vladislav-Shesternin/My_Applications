package com.veldan.lbjt.game.utils.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.lbjt.game.box2d.WorldUtil
import com.veldan.lbjt.game.utils.HEIGHT_BOX2D
import com.veldan.lbjt.game.utils.HEIGHT_UI
import com.veldan.lbjt.game.utils.WIDTH_BOX2D
import com.veldan.lbjt.game.utils.WIDTH_UI
import com.veldan.lbjt.game.utils.disposeAll

abstract class AdvancedBox2dScreen(
    val worldUtil: WorldUtil,
    val uiW  : Float = WIDTH_UI,
    val uiH  : Float = HEIGHT_UI,
    val boxW : Float = WIDTH_BOX2D,
    val boxH : Float = HEIGHT_BOX2D,
): AdvancedScreen(uiW, uiH) {

    private val viewportBox2d by lazy { FitViewport(boxW, boxH) }


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