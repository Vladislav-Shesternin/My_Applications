package com.shapovalovd.financecomitet.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.shapovalovd.financecomitet.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val huy get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.PIPKA.region,
            checked = SpriteManager.GameRegion.HUY.region,
        )
    }
}