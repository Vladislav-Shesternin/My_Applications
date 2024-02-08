package com.littleman.andball.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
//        val cb get() = CheckBoxStyle(
//            default = SpriteManager.GameRegion.CB_D.region,
//            checked = SpriteManager.GameRegion.CB_CH.region,
//        )
    }
}