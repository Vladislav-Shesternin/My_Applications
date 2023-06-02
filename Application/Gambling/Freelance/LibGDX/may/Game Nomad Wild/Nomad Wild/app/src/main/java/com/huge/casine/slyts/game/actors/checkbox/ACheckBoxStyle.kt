package com.huge.casine.slyts.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.huge.casine.slyts.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val stg get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.C1.region,
            checked = SpriteManager.GameRegion.C2.region,
        )
    }
}