package com.danila.cryptotracker.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.danila.cryptotracker.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
//        val contex get() = AButtonStyle(
//            default = SpriteManager.GameRegion.CONTINUE_PURPLE.region,
//            pressed = SpriteManager.GameRegion.CONTINUE_GRAY.region,
//            disabled = SpriteManager.GameRegion.CONTINUE_GRAY.region,
//        )
    }
    
}