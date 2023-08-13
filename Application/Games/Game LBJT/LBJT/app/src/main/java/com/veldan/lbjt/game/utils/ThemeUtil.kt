package com.veldan.lbjt.game.utils

import com.veldan.lbjt.R
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.util.SpriteUtil

class ThemeUtil(val spriteUtil: SpriteUtil) {

    var type: Type = Type.WHITE
        private set
    var trc: SpriteUtil.ITextureRegionContainer = spriteUtil.WhiteRegion()
        private set
    var navBarColor = R.color.yan
        private set
    var backgroundColor = GameColor.yan
        private set



    fun update(type: Type) {
        this.type = type
        when(type) {
            Type.BLACK -> {
                trc             = spriteUtil.BlackRegion()
                navBarColor     = R.color.yin
                backgroundColor = GameColor.yin
            }
            Type.WHITE -> {
                trc             = spriteUtil.WhiteRegion()
                navBarColor     = R.color.yan
                backgroundColor = GameColor.yan

            }
        }
    }



    enum class Type {
        BLACK, WHITE
    }

}