package com.srata.financialguru.game.actors.checkbox

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.srata.financialguru.game.manager.SpriteManager
import com.srata.financialguru.game.utils.TextureEmpty
import com.srata.financialguru.game.utils.region

data class ACheckBoxStyle(
    val default: TextureRegion,
    val checked: TextureRegion,
) {
    companion object {
        val triangle_circ get() = ACheckBoxStyle(
            default = SpriteManager.GameRegion.US.region,
            checked = SpriteManager.GameRegion.AN.region,
        )

        val MON get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.MON.region,
        )
        val TUE get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.TUE.region,
        )
        val WED get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.WED.region,
        )
        val THU get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.THU.region,
        )
        val FRI get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.FRI.region,
        )
        val SAT get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.SAT.region,
        )
        val SUN get() = ACheckBoxStyle(
            default = TextureEmpty.region,
            checked = SpriteManager.GameRegion.SUN.region,
        )
    }
}