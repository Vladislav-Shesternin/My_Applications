package crapto.makasinik.cryptoinsightspro.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import crapto.makasinik.cryptoinsightspro.game.manager.SpriteManager
import crapto.makasinik.cryptoinsightspro.game.utils.TextureEmpty
import crapto.makasinik.cryptoinsightspro.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val charodey get() = ACheckBoxStyle(
            default = SpriteManager.IgraRegion.CHECK_STAR.region,
            checked = SpriteManager.IgraRegion.CHECK_GALKA.region,
        )
    }
}