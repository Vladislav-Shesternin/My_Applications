package com.veldan.bigwinslots777.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.bigwinslots777.manager.assets.SpriteManager

data class ButtonClickableStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val plus get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.PLUS_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.PLUS_PRESS.region,
            disabled = SpriteManager.GameRegion.PLUS_DISABLE.region,
        )
        val minus get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.MINUS_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.MINUS_PRESS.region,
            disabled = SpriteManager.GameRegion.MINUS_DISABLE.region,
        )
        val autoSpin get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.AUTO_SPIN_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.AUTO_SPIN_PRESS.region,
            disabled = SpriteManager.GameRegion.AUTO_SPIN_DISABLE.region,
        )
        val spin get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.SPIN_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.SPIN_PRESS.region,
            disabled = SpriteManager.GameRegion.SPIN_DISABLE.region,
        )
    }
    
}