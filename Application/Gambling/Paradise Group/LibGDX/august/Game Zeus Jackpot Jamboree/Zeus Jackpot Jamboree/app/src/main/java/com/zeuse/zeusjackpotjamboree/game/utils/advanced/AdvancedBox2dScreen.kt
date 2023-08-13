package com.zeuse.zeusjackpotjamboree.game.utils.advanced

import com.badlogic.gdx.utils.viewport.FitViewport
import com.zeuse.zeusjackpotjamboree.game.box2d.WorldUtil
import com.zeuse.zeusjackpotjamboree.game.utils.HEIGHT_BOX2D
import com.zeuse.zeusjackpotjamboree.game.utils.HEIGHT_UI
import com.zeuse.zeusjackpotjamboree.game.utils.WIDTH_BOX2D
import com.zeuse.zeusjackpotjamboree.game.utils.WIDTH_UI
import com.zeuse.zeusjackpotjamboree.game.utils.disposeAll
import com.zeuse.zeusjackpotjamboree.util.log

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