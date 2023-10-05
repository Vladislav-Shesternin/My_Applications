package com.lohina.titantreasuretrove.game.utils.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import com.lohina.titantreasuretrove.game.box2d.WorldUtil
import com.lohina.titantreasuretrove.game.utils.HEIGHT_BOX2D
import com.lohina.titantreasuretrove.game.utils.HEIGHT_UI
import com.lohina.titantreasuretrove.game.utils.WIDTH_BOX2D
import com.lohina.titantreasuretrove.game.utils.WIDTH_UI
import com.lohina.titantreasuretrove.game.utils.disposeAll
import com.lohina.titantreasuretrove.util.log

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

    override fun dispose() {
        log("dispose AdvancedBox2dScreen")
        super.dispose()
        worldUtil.dispose()
    }

}