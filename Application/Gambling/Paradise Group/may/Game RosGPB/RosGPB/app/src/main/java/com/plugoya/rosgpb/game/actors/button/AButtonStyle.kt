package com.plugoya.rosgpb.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.plugoya.rosgpb.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val pibk get() = AButtonStyle(
            default = SpriteManager.GameRegion.BACK.region,
            pressed = SpriteManager.GameRegion.UN_BACK.region,
            disabled = SpriteManager.GameRegion.UN_BACK.region,
        )
        val picavcy get() = AButtonStyle(
            default = SpriteManager.GameRegion.DEFLAKO.region,
            pressed = SpriteManager.GameRegion.CHIKOBOKA.region,
            disabled = SpriteManager.GameRegion.CHIKOBOKA.region,
        )
        val miua get() = AButtonStyle(
            default = SpriteManager.GameRegion.MAN.region,
            pressed = SpriteManager.GameRegion.GIRL.region,
            disabled = SpriteManager.GameRegion.GIRL.region,
        )
    }
    
}