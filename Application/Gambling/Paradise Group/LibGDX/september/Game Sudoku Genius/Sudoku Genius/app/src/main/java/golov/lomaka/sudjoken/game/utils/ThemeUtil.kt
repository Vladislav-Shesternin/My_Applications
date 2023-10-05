//package golov.lomaka.sudjoken.game.utils
//
//import golov.lomaka.sudjoken.R
//import golov.lomaka.sudjoken.game.manager.SpriteManager
//import golov.lomaka.sudjoken.game.manager.util.SpriteUtil
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