package crapto.quantem.ao.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import crapto.quantem.ao.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val boxik get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.CB_DIFUSOR.region,
            checked = SpriteManager.GameRegion.CB_PARAMOUNT.region,
        )
    }
}