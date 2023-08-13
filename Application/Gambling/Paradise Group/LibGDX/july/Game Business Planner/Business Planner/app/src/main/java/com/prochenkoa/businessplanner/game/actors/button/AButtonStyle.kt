package com.prochenkoa.businessplanner.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.prochenkoa.businessplanner.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
//        val convet get() = AButtonStyle(
//            default = SpriteManager.GameRegion.KONVERTE_DEFE.region,
//            pressed = SpriteManager.GameRegion.KONE_PRESE.region,
//            disabled = SpriteManager.GameRegion.KONE_PRESE.region,
//        )
    }
    
}