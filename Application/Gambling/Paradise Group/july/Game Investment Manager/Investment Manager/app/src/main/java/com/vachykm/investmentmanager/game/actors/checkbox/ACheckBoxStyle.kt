package com.vachykm.investmentmanager.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vachykm.investmentmanager.game.manager.SpriteManager
import com.vachykm.investmentmanager.game.utils.TextureEmpty
import com.vachykm.investmentmanager.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val rect_circ get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.RECTIKD.region,
            checked = SpriteManager.GameRegion.CHECKCLE.region,
        )
        val depoz get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.DEPOZ.region,
        )
        val otziv get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.OTZIV.region,
        )

        val oill get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.OILL.region,
        )
        val sp500 get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.SP500.region,
        )
        val forex get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.FOREX.region,
        )
        val indeX get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.INDEX.region,
        )
        val stock get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.STOCK.region,
        )

        val _1h get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion._1H.region,
        )
        val _6h get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion._6H.region,
        )
        val _24h get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion._24H.region,
        )
        val _1w get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion._1W.region,
        )
        val _1m get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion._1M.region,
        )
        val _1y get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion._1Y.region,
        )
    }
}