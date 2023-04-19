package com.hk.stck.nord.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hk.stck.nord.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val big get() = AButtonStyle(
            default = SpriteManager.GameRegion.BIG_DEF.region,
            pressed = SpriteManager.GameRegion.BIG_PRESS.region,
            disabled = SpriteManager.GameRegion.BIG_PRESS.region,
        )
        val mini get() = AButtonStyle(
            default = SpriteManager.GameRegion.MINI_DEF.region,
            pressed = SpriteManager.GameRegion.MINI_PRESS.region,
            disabled = SpriteManager.GameRegion.MINI_PRESS.region,
        )
        val stock get() = AButtonStyle(
            default = SpriteManager.GameRegion.STOCK_DEFF.region,
            pressed = SpriteManager.GameRegion.STOCK_PRESS.region,
            disabled = SpriteManager.GameRegion.STOCK_PRESS.region,
        )
    }
    
}