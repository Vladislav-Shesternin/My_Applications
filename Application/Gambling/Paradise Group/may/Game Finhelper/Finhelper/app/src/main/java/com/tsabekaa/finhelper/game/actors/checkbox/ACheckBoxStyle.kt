package com.tsabekaa.finhelper.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tsabekaa.finhelper.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val privacy get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.BIX.region,
            checked = SpriteManager.GameRegion.BIXIKID.region,
        )
    }
}