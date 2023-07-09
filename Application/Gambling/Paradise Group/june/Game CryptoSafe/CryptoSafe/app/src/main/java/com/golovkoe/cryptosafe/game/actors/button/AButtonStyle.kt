package com.golovkoe.cryptosafe.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.golovkoe.cryptosafe.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val buttonchick get() = AButtonStyle(
            default = SpriteManager.GameRegion.DEFFIC.region,
            pressed = SpriteManager.GameRegion.PRESSIC.region,
            disabled = SpriteManager.GameRegion.PRESSIC.region,
        )
    }
    
}