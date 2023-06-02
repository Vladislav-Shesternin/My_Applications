package com.gpkhold.mamm.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.gpkhold.mamm.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val prterms get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.P_CB_D.region,
            checked = SpriteManager.GameRegion.P_CB_P.region,
        )
        val darkCB get() = ACheckBoxStyle(
            default = SpriteManager.DarkRegion.CB_DEF_D.region,
            checked = SpriteManager.DarkRegion.CB_PRESS_D.region,
        )
        val lightCB get() = ACheckBoxStyle(
            default = SpriteManager.LightRegion.CB_DEF_L.region,
            checked = SpriteManager.LightRegion.CB_PRESS_L.region,
        )
    }
}