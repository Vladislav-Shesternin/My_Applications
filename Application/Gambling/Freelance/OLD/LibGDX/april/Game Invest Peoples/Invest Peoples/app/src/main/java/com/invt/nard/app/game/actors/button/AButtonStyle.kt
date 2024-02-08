package com.invt.nard.app.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.invt.nard.app.game.manager.SpriteManager
import com.invt.nard.app.game.utils.TextureEmpty
import com.invt.nard.app.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val play get() = AButtonStyle(
            default = SpriteManager.GameRegion.PLAY_D.region,
            pressed = SpriteManager.GameRegion.PLAY_P.region,
            disabled = SpriteManager.GameRegion.PLAY_P.region,
        )
    }
    
}