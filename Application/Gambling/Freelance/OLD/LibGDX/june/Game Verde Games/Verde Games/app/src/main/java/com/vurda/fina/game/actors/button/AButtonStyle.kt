package com.vurda.fina.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
//        val default get() = AButtonStyle(
//            default = SpriteManager.GameRegion.DEF.region,
//            pressed = SpriteManager.GameRegion.DIS.region,
//            disabled = SpriteManager.GameRegion.DIS.region,
//        )
    }
    
}