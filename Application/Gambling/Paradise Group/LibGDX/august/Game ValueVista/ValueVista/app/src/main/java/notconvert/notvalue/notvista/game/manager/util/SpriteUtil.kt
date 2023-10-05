package notconvert.notvalue.notvista.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import notconvert.notvalue.notvista.game.manager.SpriteManager
import notconvert.notvalue.notvista.game.utils.region

class SpriteUtil {

    fun regionGAME(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

//    interface SplashAtlas {
//        val BACKGROUND : TextureRegion
//        val ZEFS       : TextureRegion
//    }

    interface GameAtlas {
        val DELAY     : TextureRegion
        val KUBA      : TextureRegion
        val PIRIVETKA : TextureRegion
        val i_LIST : List<TextureRegion>
        val a_LIST : List<TextureRegion>
    }

//    val SPLASH by lazy { object: SplashAtlas {
//        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture.region
//        override val ZEFS       = SpriteManager.EnumTexture.ZEFS.data.texture.region
//    } }

    val GAME by lazy { object: GameAtlas {
        override val DELAY     = SpriteManager.EnumTexture.DELAY.data.texture.region
        override val KUBA      = SpriteManager.EnumTexture.KUBA.data.texture.region
        override val PIRIVETKA = SpriteManager.EnumTexture.PIRIVETKA.data.texture.region

        override val i_LIST  = List(5) { regionGAME("${it.inc()}") }
        override val a_LIST  = List(4) { regionGAME("a${it.inc()}") }
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