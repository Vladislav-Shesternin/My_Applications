package com.gen.bettermeditatio.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.gen.bettermeditatio.game.manager.SpriteManager

data class ButtonClickableStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val spin get() = ButtonClickableStyle(
            default = SpriteManager.GameRegion.SPIN_DEF.region,
            pressed = SpriteManager.GameRegion.SPIN_DIS.region,
            disabled = SpriteManager.GameRegion.SPIN_DIS.region,
        )
    }
    
}