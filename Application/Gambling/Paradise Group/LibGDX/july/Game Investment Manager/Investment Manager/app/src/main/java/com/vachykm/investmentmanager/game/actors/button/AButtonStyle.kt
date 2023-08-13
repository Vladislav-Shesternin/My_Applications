package com.vachykm.investmentmanager.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vachykm.investmentmanager.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn_otkl get() = AButtonStyle(
            default = SpriteManager.GameRegion.BUTTONS.region,
            pressed = SpriteManager.GameRegion.BUTTONS_OTKL.region,
            disabled = SpriteManager.GameRegion.BUTTONS_OTKL.region,
        )
        val buyna get() = AButtonStyle(
            default = SpriteManager.GameRegion.BUY.region,
            pressed = SpriteManager.GameRegion.BUY_D.region,
            disabled = SpriteManager.GameRegion.BUY_D.region,
        )
        val selodka get() = AButtonStyle(
            default = SpriteManager.GameRegion.SELL.region,
            pressed = SpriteManager.GameRegion.SELL_D.region,
            disabled = SpriteManager.GameRegion.SELL_D.region,
        )
    }
    
}