package com.jettylucketjet1wincasino.onewinslots1win.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.SpriteManager
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.TextureEmpty
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTN.region,
            pressed = SpriteManager.GameRegion.NBTN.region,
            disabled = SpriteManager.GameRegion.NBTN.region,
        )
        val nbtn get() = AButtonStyle(
            default = SpriteManager.GameRegion.NBTN.region,
            pressed = SpriteManager.GameRegion.BTN.region,
            disabled = SpriteManager.GameRegion.BTN.region,
        )
    }
    
}