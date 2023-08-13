package com.mariam.cleverfinancier.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mariam.cleverfinancier.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val boxidstka get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.DFF.region,
            checked = SpriteManager.GameRegion.CKK.region,
        )
    }
}