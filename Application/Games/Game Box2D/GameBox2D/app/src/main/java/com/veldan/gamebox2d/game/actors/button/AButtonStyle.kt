package com.veldan.gamebox2d.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.gamebox2d.game.manager.SpriteManager
import com.veldan.gamebox2d.game.utils.TextureEmpty
import com.veldan.gamebox2d.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
//        val btn get() = AButtonStyle(
//            default = SpriteManager.GameRegion.BTN_DEFF.region,
//            pressed = SpriteManager.GameRegion.BTN_PRESS.region,
//            disabled = SpriteManager.GameRegion.BTN_PRESS.region,
//        )
    }
    
}