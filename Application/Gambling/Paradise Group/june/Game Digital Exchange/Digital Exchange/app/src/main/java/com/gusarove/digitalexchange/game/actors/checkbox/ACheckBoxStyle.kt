package com.gusarove.digitalexchange.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.gusarove.digitalexchange.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val cbix get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.CBFAK.region,
            checked = SpriteManager.GameRegion.CBGUIK.region,
        )
    }
}