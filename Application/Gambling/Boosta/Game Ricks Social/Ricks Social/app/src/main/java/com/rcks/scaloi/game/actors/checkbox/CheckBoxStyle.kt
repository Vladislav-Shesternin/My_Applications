package com.rcks.scaloi.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.rcks.scaloi.game.manager.SpriteManager

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val stavka get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.STAVKA_DEF.region,
            checked = SpriteManager.GameRegion.STAFKA_PIRS.region,
        )
        val music get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.MUS_ON.region,
            checked = SpriteManager.GameRegion.MUS_OFF.region,
        )
        val sound get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.SOUN_ON.region,
            checked = SpriteManager.GameRegion.SOUN_OFF.region,
        )
    }
}