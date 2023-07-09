package com.uchimenkoe.financecounter.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.uchimenkoe.financecounter.game.manager.SpriteManager
import com.uchimenkoe.financecounter.game.utils.TextureEmpty
import com.uchimenkoe.financecounter.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val soglasie get() = AButtonStyle(
            default = SpriteManager.GameRegion.BERDAS.region,
            pressed = SpriteManager.GameRegion.ASKAR.region,
            disabled = SpriteManager.GameRegion.ASKAR.region,
        )
        val elik get() = AButtonStyle(
            default = TextureEmpty.region,
            pressed = SpriteManager.GameRegion.ELIK.region,
            disabled = SpriteManager.GameRegion.ELIK.region,
        )
    }
    
}