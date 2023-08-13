package com.shapovalovd.financecomitet.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.shapovalovd.financecomitet.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val sw get() = AButtonStyle(
            default = SpriteManager.GameRegion.W.region,
            pressed = SpriteManager.GameRegion.S.region,
            disabled = SpriteManager.GameRegion.S.region,
        )
    }
    
}