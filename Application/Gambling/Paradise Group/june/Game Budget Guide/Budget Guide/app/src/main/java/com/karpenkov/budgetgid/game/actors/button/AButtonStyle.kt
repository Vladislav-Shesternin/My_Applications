package com.karpenkov.budgetgid.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.karpenkov.budgetgid.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val backower get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTY_WHITER.region,
            pressed = SpriteManager.GameRegion.BTY_CLICK.region,
            disabled = SpriteManager.GameRegion.BTY_CLICK.region,
        )
    }
    
}