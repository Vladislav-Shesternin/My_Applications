package com.hsr.bkm.mobile.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hsr.bkm.mobile.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val def get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTN_DEF.region,
            pressed = SpriteManager.GameRegion.BTN_PRESS.region,
            disabled = SpriteManager.GameRegion.BTN_PRESS.region,
        )
        val send get() = AButtonStyle(
            default = SpriteManager.GameRegion.SEND_DEF.region,
            pressed = SpriteManager.GameRegion.SEND_PRESS.region,
            disabled = SpriteManager.GameRegion.SEND_PRESS.region,
        )
        val home get() = AButtonStyle(
            default = SpriteManager.GameRegion.HOME_DEF.region,
            pressed = SpriteManager.GameRegion.HOME_PRESS.region,
            disabled = SpriteManager.GameRegion.HOME_PRESS.region,
        )
        val favorit get() = AButtonStyle(
            default = SpriteManager.GameRegion.FAVORIT_DEF.region,
            pressed = SpriteManager.GameRegion.FAVORIT_PRESS.region,
            disabled = SpriteManager.GameRegion.FAVORIT_PRESS.region,
        )
        val info get() = AButtonStyle(
            default = SpriteManager.GameRegion.INFO_DEF.region,
            pressed = SpriteManager.GameRegion.INFO_PRESS.region,
            disabled = SpriteManager.GameRegion.INFO_PRESS.region,
        )
    }
    
}