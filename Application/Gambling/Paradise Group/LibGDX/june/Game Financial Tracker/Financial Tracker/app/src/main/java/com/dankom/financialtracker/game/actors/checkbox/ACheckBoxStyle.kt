package com.dankom.financialtracker.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.dankom.financialtracker.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val agree get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.DEFOLT.region,
            checked = SpriteManager.GameRegion.INFOLT.region,
        )
    }
}