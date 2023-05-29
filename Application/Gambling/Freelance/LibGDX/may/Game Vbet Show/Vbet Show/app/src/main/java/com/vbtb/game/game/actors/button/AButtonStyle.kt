package com.vbtb.game.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vbtb.game.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTN_DEFFFFFF.region,
            pressed = SpriteManager.GameRegion.BTN_PRPRPR.region,
            disabled = SpriteManager.GameRegion.BTN_PRPRPR.region,
        )
    }
    
}