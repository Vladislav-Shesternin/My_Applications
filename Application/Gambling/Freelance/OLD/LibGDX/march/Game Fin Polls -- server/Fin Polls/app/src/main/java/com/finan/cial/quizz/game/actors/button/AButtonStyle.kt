package com.finan.cial.quizz.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.finan.cial.quizz.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTN.region,
            pressed = SpriteManager.GameRegion.MTN.region,
            disabled = SpriteManager.GameRegion.MTN.region,
        )
    }
    
}