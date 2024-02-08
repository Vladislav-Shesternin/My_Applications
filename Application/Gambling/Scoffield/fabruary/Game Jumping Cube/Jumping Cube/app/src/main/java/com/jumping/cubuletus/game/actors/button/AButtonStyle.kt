package com.jumping.cubuletus.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
//        val right get() = AButtonStyle(
//            default = SpriteManager.GameRegion.RIGHT_DEF.region,
//            pressed = SpriteManager.GameRegion.RIGHT_PRESS.region,
//        )
    }
    
}