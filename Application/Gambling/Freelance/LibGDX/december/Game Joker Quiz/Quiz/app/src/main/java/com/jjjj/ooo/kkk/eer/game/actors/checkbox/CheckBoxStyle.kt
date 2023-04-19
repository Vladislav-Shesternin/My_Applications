package com.jjjj.ooo.kkk.eer.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.jjjj.ooo.kkk.eer.game.manager.SpriteManager

data class CheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val yes get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.YES_DEF.region,
            checked = SpriteManager.GameRegion.YES_PRESS.region,
        )
        val not get() = CheckBoxStyle(
            default = SpriteManager.GameRegion.NOT_DEF.region,
            checked = SpriteManager.GameRegion.NOT_PRESS.region,
        )
    }
}