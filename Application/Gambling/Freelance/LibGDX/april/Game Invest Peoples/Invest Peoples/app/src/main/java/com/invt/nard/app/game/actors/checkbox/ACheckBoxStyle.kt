package com.invt.nard.app.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.invt.nard.app.game.manager.SpriteManager
import com.invt.nard.app.game.utils.TextureEmpty
import com.invt.nard.app.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val current get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.CURRENT.region,
        )
        val music get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.MUSIC_D.region,
            checked = SpriteManager.GameRegion.MUSIC_NO.region,
        )
    }
}