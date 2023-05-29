package com.tsabekaa.finhelper.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.utils.TextureEmpty
import com.tsabekaa.finhelper.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTNZDF.region,
            pressed = SpriteManager.GameRegion.BTN_DFP_CLICK.region,
            disabled = SpriteManager.GameRegion.BTN_DFP_CLICK.region,
        )
    }
    
}