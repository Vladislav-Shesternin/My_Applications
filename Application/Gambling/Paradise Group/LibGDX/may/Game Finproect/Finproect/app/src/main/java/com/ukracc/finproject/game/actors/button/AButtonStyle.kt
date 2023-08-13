package com.ukracc.finproject.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.ukracc.finproject.game.manager.SpriteManager
import com.ukracc.finproject.game.utils.TextureEmpty
import com.ukracc.finproject.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val pBtn get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTN.region,
            pressed = TextureEmpty.region,
            disabled = TextureEmpty.region,
        )
        val btn get() = AButtonStyle(
            default = SpriteManager.GameRegion.BTN_DEF.region,
            pressed = SpriteManager.GameRegion.BTN_PRESS.region,
            disabled = SpriteManager.GameRegion.BTN_DIS.region,
        )
        val back get() = AButtonStyle(
            default = SpriteManager.SettingsRegion.BACK.region,
            pressed = TextureEmpty.region,
            disabled = TextureEmpty.region,
        )
    }
    
}