package com.hello.piramidka.game.actors.button

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hello.piramidka.game.manager.SpriteManager
import com.hello.piramidka.game.screens.themeUtil
import com.hello.piramidka.game.utils.TextureEmpty
import com.hello.piramidka.game.utils.region

data class AButtonStyle(
    val default : TextureRegion,
    val pressed : TextureRegion,
    val disabled: TextureRegion? = null,
) {
    
    companion object {
        val pbt get() = AButtonStyle(
            default = SpriteManager.GameRegion.DEFFFF.region,
            pressed = SpriteManager.GameRegion.PRESSS.region,
            disabled = SpriteManager.GameRegion.PRESSS.region,
        )
        val back get() = AButtonStyle(
            default = themeUtil.BACK,
            pressed = TextureEmpty.region,
            disabled = themeUtil.BACK,
        )
    }
    
}