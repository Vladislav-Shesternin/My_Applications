package com.gusarove.digitalexchange.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.gusarove.digitalexchange.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val pol get() = AButtonStyle(
            default = SpriteManager.GameRegion.POL_PRESS.region,
            pressed = SpriteManager.GameRegion.POL_DEF.region,
            disabled = SpriteManager.GameRegion.POL_DEF.region,
        )
    }
    
}