package com.dankom.financialtracker.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.dankom.financialtracker.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val agree get() = AButtonStyle(
            default = SpriteManager.GameRegion.SINI.region,
            pressed = SpriteManager.GameRegion.SERI.region,
            disabled = SpriteManager.GameRegion.SERI.region,
        )
    }
    
}