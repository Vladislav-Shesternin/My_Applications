package com.jungle.jumping.bird.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.jungle.jumping.bird.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val play get() = AButtonStyle(
            default = SpriteManager.GameRegion.PLAY_DEF.region,
            pressed = SpriteManager.GameRegion.PLAY_PRESS.region,
        )
    }
    
}