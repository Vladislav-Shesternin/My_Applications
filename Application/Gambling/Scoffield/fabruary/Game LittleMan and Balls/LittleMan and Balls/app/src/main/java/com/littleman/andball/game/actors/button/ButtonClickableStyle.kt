package com.littleman.andball.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class ButtonClickableStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
//    companion object {
//        val knop get() = ButtonClickableStyle(
//            default = SpriteManager.GameRegion.MBLACK.region,
//            pressed = SpriteManager.GameRegion.MWHITE.region,
//        )
//
//    }
    
}