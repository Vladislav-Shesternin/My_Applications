package com.zaykoa.investmentanalyzer.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val go_to_work get() = AButtonStyle(
            default = SpriteManager.GameRegion.START_WORK.region,
            pressed = SpriteManager.GameRegion.STARTED_WORK.region,
            disabled = SpriteManager.GameRegion.STARTED_WORK.region,
        )
        val begin get() = AButtonStyle(
            default = SpriteManager.GameRegion.BEGIN.region,
            pressed = SpriteManager.GameRegion.BEGINED.region,
            disabled = SpriteManager.GameRegion.BEGINED.region,
        )
        val kup get() = AButtonStyle(
            default = SpriteManager.GameRegion.SEALE.region,
            pressed = SpriteManager.GameRegion.SEALED.region,
            disabled = SpriteManager.GameRegion.SEALED.region,
        )
        val prod get() = AButtonStyle(
            default = SpriteManager.GameRegion.PRODE.region,
            pressed = SpriteManager.GameRegion.PRODED.region,
            disabled = SpriteManager.GameRegion.PRODED.region,
        )
    }
    
}