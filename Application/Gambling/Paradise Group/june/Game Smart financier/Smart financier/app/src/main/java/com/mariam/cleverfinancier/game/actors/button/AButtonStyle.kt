package com.mariam.cleverfinancier.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mariam.cleverfinancier.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val vkl get() = AButtonStyle(
            default = SpriteManager.GameRegion.UVIMKNENO.region,
            pressed = SpriteManager.GameRegion.OTKL.region,
            disabled = SpriteManager.GameRegion.OTKL.region,
        )
    }
    
}