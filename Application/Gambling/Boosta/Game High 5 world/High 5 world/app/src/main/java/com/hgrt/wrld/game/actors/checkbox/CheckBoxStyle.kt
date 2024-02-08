package com.hgrt.wrld.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hgrt.wrld.game.manager.SpriteManager

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val bet get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.CB_DEFF.region,
            checked = SpriteManager.GameRegion.CB_CHECK.region,
        )
        val music get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.ON.region,
            checked = SpriteManager.GameRegion.OFF.region,
        )
    }
}