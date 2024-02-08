package com.wlfe.astiener.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.wlfe.astiener.game.manager.SpriteManager

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val music get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.off.region,
            checked = SpriteManager.GameRegion.on.region,
        )
    }
}