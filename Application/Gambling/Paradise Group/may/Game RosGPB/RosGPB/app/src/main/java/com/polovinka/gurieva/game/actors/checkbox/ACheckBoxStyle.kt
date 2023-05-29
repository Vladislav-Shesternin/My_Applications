package com.polovinka.gurieva.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.polovinka.gurieva.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val rivac get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.CBIX_DID.region,
            checked = SpriteManager.GameRegion.CBIX_PRIS.region,
        )
//        val darkCB get() = ACheckBoxStyle(
//            default = SpriteManager.DarkRegion.CB_DEF_D.region,
//            checked = SpriteManager.DarkRegion.CB_PRESS_D.region,
//        )
//        val lightCB get() = ACheckBoxStyle(
//            default = SpriteManager.LightRegion.CB_DEF_L.region,
//            checked = SpriteManager.LightRegion.CB_PRESS_L.region,
//        )
    }
}