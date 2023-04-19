package com.veldan.boost.and.clean.simpleapp.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.boost.and.clean.simpleapp.game.manager.SpriteManager
import com.veldan.boost.and.clean.simpleapp.game.utils.TextureEmpty
import com.veldan.boost.and.clean.simpleapp.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn get() = AButtonStyle(
            default = SpriteManager.CommonRegion.BTN.region,
            pressed = TextureEmpty.region,
            disabled = TextureEmpty.region,
        )
    }
    
}