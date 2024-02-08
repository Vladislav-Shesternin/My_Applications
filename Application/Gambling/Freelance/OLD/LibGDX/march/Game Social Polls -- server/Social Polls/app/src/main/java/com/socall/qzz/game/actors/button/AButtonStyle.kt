package com.socall.qzz.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.socall.qzz.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTN_DEF.region,
            pressed = SpriteManager.GameRegion.BTN_PRESS.region,
            disabled = SpriteManager.GameRegion.BTN_PRESS.region,
        )
    }
    
}