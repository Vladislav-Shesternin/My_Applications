package com.bezdrodovam.cryptoinsightspro.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bezdrodovam.cryptoinsightspro.game.manager.SpriteManager
import com.bezdrodovam.cryptoinsightspro.game.utils.TextureEmpty
import com.bezdrodovam.cryptoinsightspro.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val charodey get() = ACheckBoxStyle(
            default = SpriteManager.IgraRegion.CHECK_STAR.region,
            checked = SpriteManager.IgraRegion.CHECK_GALKA.region,
        )

        val AH get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.IgraRegion.AH.region,
        )
        val AD get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.IgraRegion.AD.region,
        )
        val AW get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.IgraRegion.AW.region,
        )
        val AM get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.IgraRegion.AM.region,
        )
        val AY get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.IgraRegion.AY.region,
        )
    }
}