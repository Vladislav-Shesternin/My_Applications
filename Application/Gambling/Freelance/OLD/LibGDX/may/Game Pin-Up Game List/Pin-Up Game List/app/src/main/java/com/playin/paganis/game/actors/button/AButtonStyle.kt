package com.playin.paganis.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
//        val menu get() = AButtonStyle(
//            default = SpriteManager.GameRegion.MENU_D.region,
//            pressed = SpriteManager.GameRegion.MENU_P.region,
//            disabled = SpriteManager.GameRegion.MENU_P.region,
//        )
    }
    
}