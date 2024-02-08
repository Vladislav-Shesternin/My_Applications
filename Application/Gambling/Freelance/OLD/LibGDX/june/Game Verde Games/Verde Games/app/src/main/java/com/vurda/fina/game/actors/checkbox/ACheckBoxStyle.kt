package com.vurda.fina.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vurda.fina.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val music get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.MUZON.region,
            checked = SpriteManager.GameRegion.MUSISISID.region,
        )
    }
}