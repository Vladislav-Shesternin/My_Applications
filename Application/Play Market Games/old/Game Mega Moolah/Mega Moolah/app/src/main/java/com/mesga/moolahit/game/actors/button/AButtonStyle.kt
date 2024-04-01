package com.mesga.moolahit.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mesga.moolahit.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val spin get() = AButtonStyle(
            default = SpriteManager.GameRegion.SPIN_DEF.region,
            pressed = SpriteManager.GameRegion.SPIN_PRESS.region,
            disabled = SpriteManager.GameRegion.SPIN_DIS.region,
        )
        val plus get() = AButtonStyle(
            default = SpriteManager.GameRegion.PLUS_DEF.region,
            pressed = SpriteManager.GameRegion.PLUS_PRESS.region,
            disabled = SpriteManager.GameRegion.PLUS_DIS.region,
        )
        val minus get() = AButtonStyle(
            default = SpriteManager.GameRegion.MINUS_DEF.region,
            pressed = SpriteManager.GameRegion.MINUS_PRESS.region,
            disabled = SpriteManager.GameRegion.MINUS_DIS.region,
        )
    }
    
}