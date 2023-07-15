package com.danila.cryptotracker.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.danila.cryptotracker.game.manager.SpriteManager
import com.danila.cryptotracker.game.utils.TextureEmpty
import com.danila.cryptotracker.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val patron get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.UGU.region,
        )
    }
}