package mange.yourse.finnnance.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import mange.yourse.finnnance.game.manager.SpriteManager
import mange.yourse.finnnance.game.utils.region

class SpriteUtil {

    fun regionGAME(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

//    interface SplashAtlas {
//        val BACKGROUND : TextureRegion
//        val ZEFS       : TextureRegion
//    }

    interface GameAtlas {
        val LIMITQWQ  : TextureRegion
        val RASHOD    : TextureRegion
        val REZUME    : TextureRegion
        val ITEM_LIST : List<TextureRegion>
        val BIGI_LIST : List<TextureRegion>
    }

//    val SPLASH by lazy { object: SplashAtlas {
//        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture.region
//        override val ZEFS       = SpriteManager.EnumTexture.ZEFS.data.texture.region
//    } }

    val GAME by lazy { object: GameAtlas {
        override val LIMITQWQ = SpriteManager.EnumTexture.LIMITQWQ.data.texture.region
        override val RASHOD   = SpriteManager.EnumTexture.RASHOD.data.texture.region
        override val REZUME   = SpriteManager.EnumTexture.REZUME.data.texture.region

        override val ITEM_LIST  = List(6) { regionGAME("i${it.inc()}") }
        override val BIGI_LIST  = List(3) { regionGAME("b${it.inc()}") }
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