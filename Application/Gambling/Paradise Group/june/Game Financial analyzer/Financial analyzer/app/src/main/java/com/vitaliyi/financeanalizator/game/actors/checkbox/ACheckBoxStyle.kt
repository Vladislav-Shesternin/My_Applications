package com.vitaliyi.financeanalizator.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vitaliyi.financeanalizator.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val checkedlistener get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.DEFFAULT.region,
            checked = SpriteManager.GameRegion.CHEEKED.region,
        )
    }
}