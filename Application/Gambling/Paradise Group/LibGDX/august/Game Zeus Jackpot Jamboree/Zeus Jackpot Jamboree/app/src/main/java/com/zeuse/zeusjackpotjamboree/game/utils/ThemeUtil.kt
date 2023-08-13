package com.zeuse.zeusjackpotjamboree.game.utils

import com.zeuse.zeusjackpotjamboree.R
import com.zeuse.zeusjackpotjamboree.game.manager.SpriteManager
import com.zeuse.zeusjackpotjamboree.game.manager.util.SpriteUtil

class ThemeUtil(val spriteUtil: SpriteUtil) {

    var type: Type = Type.WHITE
        private set
    var trc: SpriteUtil.ITextureRegionContainer = spriteUtil.WhiteRegion()
        private set
    var navBarColor = R.color.white_purple
        private set
    var backgroundColor = GameColor.white
        private set



    fun update(type: Type) {
        this.type = type
        when(type) {
            Type.BLACK -> {
                trc             = spriteUtil.BlackRegion()
                navBarColor     = R.color.black_purple
                backgroundColor = GameColor.black
            }
            Type.WHITE -> {
                trc             = spriteUtil.WhiteRegion()
                navBarColor     = R.color.white_purple
                backgroundColor = GameColor.white

            }
        }
    }



    enum class Type {
        BLACK, WHITE
    }

}