package com.prochenkoa.businessplanner.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.prochenkoa.businessplanner.game.manager.SpriteManager

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val cici get() = ACheckBoxStyle(
            default = SpriteManager.Kedr.EMPTE.region,
            checked = SpriteManager.GameRegion.CICI.region,
        )
        val mesac get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.SOLS.region,
            checked = SpriteManager.GameRegion.INT_CHK.region,
        )
        val god get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.SAR.region,
            checked = SpriteManager.GameRegion.FLT_CHK.region,
        )
        val vse get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.DERTO.region,
            checked = SpriteManager.GameRegion.LONG_CHK.region,
        )
    }
}