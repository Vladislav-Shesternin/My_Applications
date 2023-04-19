package com.violette.quiz.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.violette.quiz.game.manager.SpriteManager

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val cb get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.DEF.region,
            checked = SpriteManager.GameRegion.CHECK.region,
        )
    }
}