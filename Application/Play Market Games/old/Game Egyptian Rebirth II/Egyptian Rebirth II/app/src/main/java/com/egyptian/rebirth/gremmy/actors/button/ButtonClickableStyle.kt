package com.egyptian.rebirth.gremmy.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.egyptian.rebirth.gremmy.manager.SpriteManager

data class ButtonClickableStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val spin get() = ButtonClickableStyle(
            default = SpriteManager.GameRegion.SPIN_DEF.region,
            pressed = SpriteManager.GameRegion.SPIN_PRESS.region,
            disabled = SpriteManager.GameRegion.SPIN_DIS.region,
        )
        val plus get() = ButtonClickableStyle(
            default = SpriteManager.GameRegion.PLUS_DEF.region,
            pressed = SpriteManager.GameRegion.PLUS_DIS.region,
            disabled = SpriteManager.GameRegion.PLUS_DIS.region,
        )
        val minus get() = ButtonClickableStyle(
            default = SpriteManager.GameRegion.MINUS_DEF.region,
            pressed = SpriteManager.GameRegion.MINUS_DIS.region,
            disabled = SpriteManager.GameRegion.MINUS_DIS.region,
        )
    }
    
}