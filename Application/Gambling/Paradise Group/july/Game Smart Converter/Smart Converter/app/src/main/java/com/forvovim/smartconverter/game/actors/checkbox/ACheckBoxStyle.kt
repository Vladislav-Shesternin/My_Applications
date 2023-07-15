package com.forvovim.smartconverter.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.forvovim.smartconverter.game.manager.SpriteManager
import com.forvovim.smartconverter.game.utils.TextureEmpty
import com.forvovim.smartconverter.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val porivokaLOKA get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.SHTUCHKE.region,
        )
    }
}