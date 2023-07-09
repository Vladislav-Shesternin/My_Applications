package com.srata.financialguru.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.srata.financialguru.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val nex get() = AButtonStyle(
            default = SpriteManager.GameRegion.NEX.region,
            pressed = SpriteManager.GameRegion.PEX.region,
            disabled = SpriteManager.GameRegion.PEX.region,
        )
    }
    
}