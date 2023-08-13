package com.leto.advancedcalculator.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.leto.advancedcalculator.game.manager.SpriteManager
import com.leto.advancedcalculator.game.utils.TextureEmpty
import com.leto.advancedcalculator.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val bb get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.BB.region,
        )
    }
}