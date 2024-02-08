package com.hgrt.wrld.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hgrt.wrld.game.manager.SpriteManager

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
    }
    
}