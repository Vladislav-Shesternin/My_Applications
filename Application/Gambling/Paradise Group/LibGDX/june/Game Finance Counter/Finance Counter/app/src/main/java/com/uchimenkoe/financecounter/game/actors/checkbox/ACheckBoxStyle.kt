package com.uchimenkoe.financecounter.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.uchimenkoe.financecounter.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val galochka get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.DSA.region,
            checked = SpriteManager.GameRegion.REDF.region,
        )
    }
}