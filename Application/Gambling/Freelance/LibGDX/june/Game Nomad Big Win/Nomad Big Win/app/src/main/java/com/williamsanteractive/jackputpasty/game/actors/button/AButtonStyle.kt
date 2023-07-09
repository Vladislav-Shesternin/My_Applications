package com.williamsanteractive.jackputpasty.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.williamsanteractive.jackputpasty.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val play get() = AButtonStyle(
            default = SpriteManager.GameRegion.PL_DE.region,
            pressed = SpriteManager.GameRegion.PREDISABLE.region,
            disabled = SpriteManager.GameRegion.PREDISABLE.region,
        )
        val exit get() = AButtonStyle(
            default = SpriteManager.GameRegion.XITE.region,
            pressed = SpriteManager.GameRegion.PREDISABLE.region,
            disabled = SpriteManager.GameRegion.PREDISABLE.region,
        )
        val GO get() = AButtonStyle(
            default = SpriteManager.GameRegion.GO_GO.region,
            pressed = SpriteManager.GameRegion.SO_SO.region,
            disabled = SpriteManager.GameRegion.SO_SO.region,
        )
    }
    
}