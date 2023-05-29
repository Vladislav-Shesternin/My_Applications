package com.hello.piramidka.game.utils

import android.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hello.piramidka.game.manager.SpriteManager

typealias spriteDark  = SpriteManager.DarkRegion
typealias spriteLight = SpriteManager.LightRegion
class ThemeUtil {

    lateinit var BACK     : TextureRegion private set
    lateinit var CB_DEF   : TextureRegion private set
    lateinit var CB_PRESS : TextureRegion private set
    lateinit var CONVER   : TextureRegion private set
    lateinit var MEN      : TextureRegion private set
    lateinit var FRAME    : TextureRegion private set

    var currentTheme = ThemeEnum.DARK
        private set

    var color = GameColor.dark


    fun setTheme(theme: ThemeEnum) {
        currentTheme = theme

        when(theme) {
            ThemeEnum.DARK -> {
                BACK     = spriteDark.BACK_D.region
                CB_DEF   = spriteDark.CB_DEF_D.region
                CB_PRESS = spriteDark.CB_PRESS_D.region
                CONVER   = spriteDark.CONVER_D.region
                MEN      = spriteDark.MEN_D.region
                FRAME    = spriteDark.FRAME_D.region

                color = GameColor.dark
            }
            ThemeEnum.LIGHT -> {
                BACK     = spriteLight.BACK_L.region
                CB_DEF   = spriteLight.CB_DEF_L.region
                CB_PRESS = spriteLight.CB_PRESS_L.region
                CONVER   = spriteLight.CONVER_L.region
                MEN      = spriteLight.MEN_L.region
                MEN      = spriteLight.MEN_L.region
                FRAME    = spriteLight.FRAME_L.region

                color = GameColor.light
            }
        }
    }



    enum class ThemeEnum {
        DARK, LIGHT
    }

}