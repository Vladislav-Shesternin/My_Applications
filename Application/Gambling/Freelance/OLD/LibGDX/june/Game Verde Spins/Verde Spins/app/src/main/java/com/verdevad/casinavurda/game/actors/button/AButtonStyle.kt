package com.verdevad.casinavurda.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.verdevad.casinavurda.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val play get() = AButtonStyle(
            default = SpriteManager.GameRegion.PFG.region,
            pressed = SpriteManager.GameRegion.QWE.region,
            disabled = SpriteManager.GameRegion.QWE.region,
        )
        val exit get() = AButtonStyle(
            default = SpriteManager.GameRegion.WER.region,
            pressed = SpriteManager.GameRegion.JJJKSJ.region,
            disabled = SpriteManager.GameRegion.JJJKSJ.region,
        )
        val gogo get() = AButtonStyle(
            default = SpriteManager.GameRegion.LILI_PISI.region,
            pressed = SpriteManager.GameRegion.SOSALKA_MALA.region,
            disabled = SpriteManager.GameRegion.SOSALKA_MALA.region,
        )
        val bebka get() = AButtonStyle(
            default = SpriteManager.GameRegion.BABKA.region,
            pressed = SpriteManager.GameRegion.SERUHA.region,
            disabled = SpriteManager.GameRegion.SERUHA.region,
        )
    }
    
}