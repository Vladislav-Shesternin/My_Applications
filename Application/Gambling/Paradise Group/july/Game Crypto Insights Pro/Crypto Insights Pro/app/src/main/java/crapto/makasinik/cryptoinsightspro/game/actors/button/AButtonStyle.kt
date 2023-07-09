package crapto.makasinik.cryptoinsightspro.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import crapto.makasinik.cryptoinsightspro.game.manager.SpriteManager

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val contex get() = AButtonStyle(
            default = SpriteManager.IgraRegion.CONTINUE_PURPLE.region,
            pressed = SpriteManager.IgraRegion.CONTINUE_GRAY.region,
            disabled = SpriteManager.IgraRegion.CONTINUE_GRAY.region,
        )
    }
    
}