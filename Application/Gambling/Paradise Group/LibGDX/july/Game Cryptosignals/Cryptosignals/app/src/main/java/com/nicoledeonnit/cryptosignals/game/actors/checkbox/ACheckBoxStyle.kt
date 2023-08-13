package com.nicoledeonnit.cryptosignals.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.nicoledeonnit.cryptosignals.game.manager.SpriteManager
import com.nicoledeonnit.cryptosignals.game.utils.TextureEmpty
import com.nicoledeonnit.cryptosignals.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val piktogramma get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.CHEKER.region,
        )

        val p24h get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.P24HOUR.region,
        )
        val hot get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.HOT.region,
        )
        val pribil get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.PRIBIL.region,
        )
        val podim get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.PODIM.region,
        )
        val poteri get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.POTERI.region,
        )

        val oh get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.OH.region,
        )
        val od get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.OD.region,
        )
        val ow get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.OW.region,
        )
        val om get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.OM.region,
        )
        val oy get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.OY.region,
        )
        val oa get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.Palas.ALL.region,
        )
    }
}