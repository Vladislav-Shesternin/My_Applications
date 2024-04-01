package com.hellhot.competition.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hellhot.competition.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val right get() = AButtonStyle(
            default = SpriteManager.GameRegion.RIGHT_DEF.region,
            pressed = SpriteManager.GameRegion.RIGHT_PRESS.region,
        )
        val left get() = AButtonStyle(
            default = SpriteManager.GameRegion.LEFT_DEF.region,
            pressed = SpriteManager.GameRegion.LEFT_PRESS.region,
        )
    }
    
}