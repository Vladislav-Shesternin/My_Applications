//package com.lohina.titantreasuretrove.game.utils
//
//import com.lohina.titantreasuretrove.R
//import com.lohina.titantreasuretrove.game.manager.SpriteManager
//import com.lohina.titantreasuretrove.game.manager.util.SpriteUtil
//
//class ThemeUtil(val spriteUtil: SpriteUtil) {
//
//    var type: Type = Type.WHITE
//        private set
//    var trc: SpriteUtil.ITextureRegionContainer = spriteUtil.WhiteRegion()
//        private set
//
//
//
////    fun update(type: Type) {
////        this.type = type
////        when(type) {
////            Type.BLACK -> {
////                trc             = spriteUtil.BlackRegion()
////                navBarColor     = R.color.white
////                backgroundColor = GameColor.black
////            }
////            Type.WHITE -> {
////                trc             = spriteUtil.WhiteRegion()
////                navBarColor     = R.color.white
////                backgroundColor = GameColor.white
////
////            }
////        }
////    }
//
//
//
//    enum class Type {
//        BLACK, WHITE
//    }
//
//}