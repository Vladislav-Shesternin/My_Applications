package com.rcks.scaloi.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.rcks.scaloi.game.manager.SpriteManager

data class ButtonClickableStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val knopa get() = ButtonClickableStyle(
            default = SpriteManager.GameRegion.KNOPA_BUM.region,
            pressed = SpriteManager.GameRegion.KILO_BAM.region,
            disabled = SpriteManager.GameRegion.KILO_BAM.region,
        )
    }
    
}