package com.williamsanteractive.jackputpasty.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.williamsanteractive.jackputpasty.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val music get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.ONNA.region,
            checked = SpriteManager.GameRegion.OFFA.region,
        )
    }
}