package com.cosmo3v1.cosmoinc.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.cosmo3v1.cosmoinc.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    object Game_2 {
        val bet get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion_2.BET_DEF.region,
            checked = SpriteManager.GameRegion_2.BET_CHECK.region,
        )
    }
}