package com.metanit.metrixmanager.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.metanit.metrixmanager.game.manager.SpriteManager
import com.metanit.metrixmanager.game.utils.TextureEmpty
import com.metanit.metrixmanager.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val lord_G get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GamePlay.LORD_GABRIIL.region,
        )
        val doru get() = ACheckBoxStyle(
            default = SpriteManager.GamePlay.MONAKO.region,
            checked = SpriteManager.GamePlay.SUNTAFE.region,
        )

        val f1 get() = ACheckBoxStyle(
            default = SpriteManager.Bazuki.DEFI.regionList[0],
            checked = SpriteManager.Bazuki.CHKI.regionList[0],
        )
        val f2 get() = ACheckBoxStyle(
            default = SpriteManager.Bazuki.DEFI.regionList[1],
            checked = SpriteManager.Bazuki.CHKI.regionList[1],
        )
        val f3 get() = ACheckBoxStyle(
            default = SpriteManager.Bazuki.DEFI.regionList[2],
            checked = SpriteManager.Bazuki.CHKI.regionList[2],
        )
        val f4 get() = ACheckBoxStyle(
            default = SpriteManager.Bazuki.DEFI.regionList[3],
            checked = SpriteManager.Bazuki.CHKI.regionList[3],
        )
        val f5 get() = ACheckBoxStyle(
            default = SpriteManager.Bazuki.DEFI.regionList[4],
            checked = SpriteManager.Bazuki.CHKI.regionList[4],
        )
        val f6 get() = ACheckBoxStyle(
            default = SpriteManager.Bazuki.DEFI.regionList[5],
            checked = SpriteManager.Bazuki.CHKI.regionList[5],
        )
        val f7 get() = ACheckBoxStyle(
            default = SpriteManager.Bazuki.DEFI.regionList[6],
            checked = SpriteManager.Bazuki.CHKI.regionList[6],
        )
    }
}