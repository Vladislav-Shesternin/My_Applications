package com.playin.paganis.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
//        val sound get() = ACheckBoxStyle(
//            default = SpriteManager.GameRegion.SOUND.region,
//            checked = SpriteManager.GameRegion.SOUND_NO.region,
//        )
    }
}