package com.huge.casine.slyts.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.huge.casine.slyts.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val game get() = AButtonStyle(
            default = SpriteManager.GameRegion.GAM_DF.region,
            pressed = SpriteManager.GameRegion.GAM_PS.region,
            disabled = SpriteManager.GameRegion.GAM_PS.region,
        )
        val exit get() = AButtonStyle(
            default = SpriteManager.GameRegion.EXIT_DF.region,
            pressed = SpriteManager.GameRegion.EXIT_PS.region,
            disabled = SpriteManager.GameRegion.EXIT_PS.region,
        )
        val start get() = AButtonStyle(
            default = SpriteManager.GameRegion.ST.region,
            pressed = SpriteManager.GameRegion.TS.region,
            disabled = SpriteManager.GameRegion.TS.region,
        )
    }
    
}