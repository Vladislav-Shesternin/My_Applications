package com.karpenkov.budgetgid.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.karpenkov.budgetgid.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val privatry get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.CBD.region,
            checked = SpriteManager.GameRegion.CBP.region,
        )
    }
}