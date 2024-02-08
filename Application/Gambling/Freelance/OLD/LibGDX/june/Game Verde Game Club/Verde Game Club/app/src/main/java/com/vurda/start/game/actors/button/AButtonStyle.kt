package com.vurda.start.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.vurda.start.game.manager.SpriteManager
import com.vurda.start.game.utils.TextureEmpty
import com.vurda.start.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val aaa get() = AButtonStyle(
            default = SpriteManager.GameRegion.AAAA.region,
            pressed = SpriteManager.GameRegion.BTN_DIS.region,
            disabled = SpriteManager.GameRegion.BTN_DIS.region,
        )
        val bbb get() = AButtonStyle(
            default = SpriteManager.GameRegion.BBBB.region,
            pressed = SpriteManager.GameRegion.BTN_DIS.region,
            disabled = SpriteManager.GameRegion.BTN_DIS.region,
        )
    }
    
}