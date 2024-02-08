package com.tmesfo.frtunes.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tmesfo.frtunes.game.manager.assets.SpriteManager

data class ButtonClickableStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val button_1 get() = ButtonClickableStyle(
            default  = SpriteManager.MenuRegion.BUTTON_DEFAULT_1.region,
            pressed  = SpriteManager.MenuRegion.BUTTON_PRESS_1.region,
        )
        val menu get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.MENU_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.MENU_PRESS.region,
        )
        val plus get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.PLUS_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.PLUS_PRESS.region,
            disabled = SpriteManager.GameRegion.PLUS_DISABLE.region,
        )
        val minus get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.MINUS_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.MINUS_PRESS.region,
            disabled = SpriteManager.GameRegion.MINUS_DISABLE.region,
        )
        val autoSpin get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.AUTOSPIN_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.AUTOSPIN_PRESS.region,
            disabled = SpriteManager.GameRegion.AUTOSPIN_DISABLE.region,
        )
        val spin get() = ButtonClickableStyle(
            default  = SpriteManager.GameRegion.SPIN_DEFAULT.region,
            pressed  = SpriteManager.GameRegion.SPIN_PRESS.region,
            disabled = SpriteManager.GameRegion.SPIN_DISABLE.region,
        )
    }
    
}