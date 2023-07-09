package com.veldan.lbjt.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.utils.TextureEmpty
import com.veldan.lbjt.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val regular get() = AButtonStyle(
            default = SpriteManager.GameRegion.REGULAR_BTN_DEF.region,
            pressed = SpriteManager.GameRegion.REGULAR_BTN_PRESS.region,
            disabled = SpriteManager.GameRegion.REGULAR_BTN_PRESS.region,
        )
    }
    
}