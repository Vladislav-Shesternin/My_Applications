package com.avietor.onlaneslets.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.avietor.onlaneslets.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val music get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.MUS.region,
            checked = SpriteManager.GameRegion.MIS.region,
        )
    }
}