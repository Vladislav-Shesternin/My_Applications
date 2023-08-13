package com.vitaliyi.financeanalizator.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vitaliyi.financeanalizator.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val contik get() = AButtonStyle(
            default = SpriteManager.GameRegion.DEF.region,
            pressed = SpriteManager.GameRegion.PRS.region,
            disabled = SpriteManager.GameRegion.PRS.region,
        )
    }
    
}