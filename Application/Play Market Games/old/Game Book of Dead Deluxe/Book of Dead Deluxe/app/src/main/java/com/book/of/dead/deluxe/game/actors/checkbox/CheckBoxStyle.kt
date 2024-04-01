package com.book.of.dead.deluxe.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
//        val right get() = CheckBoxStyle(
//            default = SpriteManager.GameRegion.CB_RIGHT_DEFF.region,
//            checked = SpriteManager.GameRegion.CB_RIGHT_CHECK.region,
//        )
    }
}