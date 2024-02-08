package aer.com.gamesas.mobile.slot.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import aer.com.gamesas.mobile.slot.game.manager.SpriteManager

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
        val exit get() = AButtonStyle(
            default = SpriteManager.GameRegion.EXIT_D.region,
            pressed = SpriteManager.GameRegion.EXIT_P.region,
            disabled = SpriteManager.GameRegion.EXIT_P.region,
        )
        val go get() = AButtonStyle(
            default = SpriteManager.GameRegion.GO_D.region,
            pressed = SpriteManager.GameRegion.GO_P.region,
            disabled = SpriteManager.GameRegion.GO_P.region,
        )
    }
    
}