package com.elastices.platferma.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
//        val btn get() = AButtonStyle(
//            default = SpriteManager.GameRegion.BTN_DEFF.region,
//            pressed = SpriteManager.GameRegion.BTN_PRESS.region,
//            disabled = SpriteManager.GameRegion.BTN_PRESS.region,
//        )
    }
    
}