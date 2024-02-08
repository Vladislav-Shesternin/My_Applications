package com.finan.cial.quizz.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.finan.cial.quizz.game.manager.SpriteManager

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