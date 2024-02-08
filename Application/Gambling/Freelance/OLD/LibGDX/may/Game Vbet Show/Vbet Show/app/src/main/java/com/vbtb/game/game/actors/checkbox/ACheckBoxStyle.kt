package com.vbtb.game.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vbtb.game.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val music get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.MD.region,
            checked = SpriteManager.GameRegion.MP.region,
        )
    }
}