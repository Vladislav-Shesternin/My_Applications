package com.bango.weld.androit.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bango.weld.androit.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val music get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.MUSIC_PLAY.region,
            checked = SpriteManager.GameRegion.MUSIC_STOP.region,
        )
    }
}