package golov.lomaka.sudjoken.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import golov.lomaka.sudjoken.game.manager.SpriteManager
import golov.lomaka.sudjoken.game.utils.region

class SpriteUtil {

    fun regionGAME(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

//    interface SplashAtlas {
//        val BACKGROUND : TextureRegion
//        val ZEFS       : TextureRegion
//    }

    interface GameAtlas {
        val DEF : TextureRegion
        val CHK : TextureRegion
        val BLACK_BACKGROUND    : TextureRegion
        val SETTINGS_BACKGROUND : TextureRegion
        val THEME_BACKGROUND    : TextureRegion
        val WHITE_BACKGROUND    : TextureRegion

        val w_LIST : List<TextureRegion>
        val b_LIST : List<TextureRegion>
    }

//    val SPLASH by lazy { object: SplashAtlas {
//        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture.region
//        override val ZEFS       = SpriteManager.EnumTexture.ZEFS.data.texture.region
//    } }

    val GAME by lazy { object: GameAtlas {
        override val DEF = regionGAME("def")
        override val CHK = regionGAME("chk")
        override val BLACK_BACKGROUND    = SpriteManager.EnumTexture.BLACK_BACKGROUND.data.texture.region
        override val SETTINGS_BACKGROUND = SpriteManager.EnumTexture.SETTINGS_BACKGROUND.data.texture.region
        override val THEME_BACKGROUND    = SpriteManager.EnumTexture.THEME_BACKGROUND.data.texture.region
        override val WHITE_BACKGROUND    = SpriteManager.EnumTexture.WHITE_BACKGROUND.data.texture.region

        override val w_LIST  = List(9) { regionGAME("w${it.inc()}") }
        override val b_LIST  = List(9) { regionGAME("b${it.inc()}") }
    } }


//    inner class BlackRegion: CommonRegion() {
//        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND_BLACK.data.texture.region
//    }
//
//    inner class WhiteRegion: CommonRegion() {
//        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND_WHITE.data.texture.region
//    }

//    enum class Game9Path(val ninePatch: NinePatch) {
//        JOINT(EnumAtlas.GAME.data.atlas.createPatch("joint")),
//    }

    //    enum class ListRegion(val regionList: List<TextureRegion>) {
    //        LOADER( List(101) { EnumAtlas.LOADER.data.atlas.findRegion("$it") }),
    //    }

}