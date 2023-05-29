package com.ukracc.finproject.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.ukracc.finproject.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val privacy get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.CB_DEF.region,
            checked = SpriteManager.GameRegion.CB_CHECK.region,
        )
    }
}