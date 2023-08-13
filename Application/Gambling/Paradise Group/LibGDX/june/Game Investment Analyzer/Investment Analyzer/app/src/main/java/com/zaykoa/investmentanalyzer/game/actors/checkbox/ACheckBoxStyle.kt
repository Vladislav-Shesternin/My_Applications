package com.zaykoa.investmentanalyzer.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.zaykoa.investmentanalyzer.game.manager.SpriteManager
import com.zaykoa.investmentanalyzer.game.utils.TextureEmpty
import com.zaykoa.investmentanalyzer.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val checkerao get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.CHECK_CIRCLE.region,
            checked = SpriteManager.GameRegion.CHECK_DONE.region,
        )
        val POP get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.POP.region,
        )
        val GLOB get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.GLOB.region,
        )
        val LUC get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.LUC.region,
        )
        val AVT get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.AVT.region,
        )
        val ZDO get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.ZDO.region,
        )
        val PUT get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.PUT.region,
        )
        val PRO get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.PRO.region,
        )
        val OBU get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.OBU.region,
        )
        val KRA get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.KRA.region,
        )

        val gb get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.GRAY.region,
            checked = SpriteManager.GameRegion.BLUE.region,
        )
    }
}