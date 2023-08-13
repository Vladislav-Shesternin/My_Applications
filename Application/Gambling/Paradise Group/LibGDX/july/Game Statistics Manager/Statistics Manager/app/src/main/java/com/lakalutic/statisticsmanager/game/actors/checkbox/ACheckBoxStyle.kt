package com.lakalutic.statisticsmanager.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.lakalutic.statisticsmanager.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val kek get() = ACheckBoxStyle(
            default = SpriteManager.Sedro.EMPTE.region,
            checked = SpriteManager.GameRegion.KEK.region,
        )

        val cb1 get() = ACheckBoxStyle(
            default = SpriteManager.ListRegion.BOX_DEF.regionList[0],
            checked = SpriteManager.ListRegion.BOX_CHECK.regionList[0],
        )
        val cb2 get() = ACheckBoxStyle(
            default = SpriteManager.ListRegion.BOX_DEF.regionList[1],
            checked = SpriteManager.ListRegion.BOX_CHECK.regionList[1],
        )
        val cb3 get() = ACheckBoxStyle(
            default = SpriteManager.ListRegion.BOX_DEF.regionList[2],
            checked = SpriteManager.ListRegion.BOX_CHECK.regionList[2],
        )
        val cb4 get() = ACheckBoxStyle(
            default = SpriteManager.ListRegion.BOX_DEF.regionList[3],
            checked = SpriteManager.ListRegion.BOX_CHECK.regionList[3],
        )
        val cb5 get() = ACheckBoxStyle(
            default = SpriteManager.ListRegion.BOX_DEF.regionList[4],
            checked = SpriteManager.ListRegion.BOX_CHECK.regionList[4],
        )
        val cb6 get() = ACheckBoxStyle(
            default = SpriteManager.ListRegion.BOX_DEF.regionList[5],
            checked = SpriteManager.ListRegion.BOX_CHECK.regionList[5],
        )
        val cb7 get() = ACheckBoxStyle(
            default = SpriteManager.ListRegion.BOX_DEF.regionList[6],
            checked = SpriteManager.ListRegion.BOX_CHECK.regionList[6],
        )
    }
}