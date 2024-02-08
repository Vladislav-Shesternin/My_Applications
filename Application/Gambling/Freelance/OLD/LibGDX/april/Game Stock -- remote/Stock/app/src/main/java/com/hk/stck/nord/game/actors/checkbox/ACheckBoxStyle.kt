package com.hk.stck.nord.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
//    companion object {
//        val music get() = ACheckBoxStyle(
//            default = SpriteManager.GameRegion.MUSIC_CHECK.region,
//            checked = SpriteManager.GameRegion.MUSIC_DEF.region,
//        )
//    }
}