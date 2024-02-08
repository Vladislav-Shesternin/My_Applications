package com.vbbb.time.game.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vbbb.time.game.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val sound get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.SOUND.region,
            checked = SpriteManager.GameRegion.SOUND_NO.region,
        )
    }
}