package com.tmesfo.frtunes.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tmesfo.frtunes.game.manager.assets.SpriteManager

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
//        val music get() = CheckBoxStyle(
//            default = SpriteManager.GameRegion.MUSIC_ENABLE.region,
//            checked = SpriteManager.GameRegion.MUSIC_DISABLE.region,
//        )
    }
}