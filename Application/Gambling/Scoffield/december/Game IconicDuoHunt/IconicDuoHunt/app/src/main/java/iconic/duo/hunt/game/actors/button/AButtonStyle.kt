package iconic.duo.hunt.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import iconic.duo.hunt.game.manager.SpriteManager
import iconic.duo.hunt.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val btn get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTN_DEF.region,
            pressed = SpriteManager.GameRegion.BTN_DIS.region,
            disabled = SpriteManager.GameRegion.BTN_DIS.region,
        )
    }
    
}