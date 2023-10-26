package com.bettilt.mobile.pt.game.utils.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import com.bettilt.mobile.pt.game.box2d.WorldUtil
import com.bettilt.mobile.pt.game.utils.HEIGHT_BOX2D
import com.bettilt.mobile.pt.game.utils.HEIGHT_UI
import com.bettilt.mobile.pt.game.utils.WIDTH_BOX2D
import com.bettilt.mobile.pt.game.utils.WIDTH_UI
import com.bettilt.mobile.pt.util.log

abstract class AdvancedBox2dScreen(
    val worldUtil: WorldUtil,
    val uiW  : Float = WIDTH_UI,
    val uiH  : Float = HEIGHT_UI,
    val boxW : Float = WIDTH_BOX2D,
    val boxH : Float = HEIGHT_BOX2D,
): AdvancedScreen(uiW, uiH) {

    private val viewportBox2d by lazy { FitViewport(boxW, boxH) }

    var worldStepTime: Float? = null

    override fun resize(width: Int, height: Int) {
        viewportBox2d.update(width, height, true)
        super.resize(width, height)
    }

    override fun render(delta: Float) {
        worldUtil.update(worldStepTime ?: delta)
        super.render(delta)
        worldUtil.debug(viewportBox2d.camera.combined)
    }

    override fun dispose() {
        log("dispose AdvancedBox2dScreen: ${this::class.java.name.substringAfterLast('.')}")
        worldUtil.dispose()
        super.dispose()
    }

}