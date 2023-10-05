package crupp.tsk.learnn.inggg.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import crupp.tsk.learnn.inggg.game.manager.SpriteManager
import crupp.tsk.learnn.inggg.game.utils.region

class SpriteUtil {

    fun regionGAME(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

//    interface SplashAtlas {
//        val BACKGROUND : TextureRegion
//        val ZEFS       : TextureRegion
//    }

    interface GameAtlas {
        val BATTAM  : TextureRegion
        val BOBIK   : TextureRegion
        val BOTOMOK : TextureRegion
        val CIBCEK  : TextureRegion
        val CIBDEF  : TextureRegion
        val CIFERIKK: TextureRegion
        val D7      : TextureRegion
        val DDD     : TextureRegion
        val ETETIK  : TextureRegion
        val KIPTIK  : TextureRegion
        val LOGOTYPE: TextureRegion
        val M1      : TextureRegion
        val M6      : TextureRegion
        val PDEF    : TextureRegion
        val PK      : TextureRegion
        val PPRES   : TextureRegion
        val RNK     : TextureRegion
        val TEXTS   : TextureRegion
        val TOPIK   : TextureRegion
        val Y1      : TextureRegion

        val p_LIST : List<TextureRegion>
        val d_LIST : List<TextureRegion>
        val e_LIST : List<TextureRegion>
    }

//    val SPLASH by lazy { object: SplashAtlas {
//        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture.region
//        override val ZEFS       = SpriteManager.EnumTexture.ZEFS.data.texture.region
//    } }

    val GAME by lazy { object: GameAtlas {
        override val BATTAM   = regionGAME("battam")
        override val BOBIK    = regionGAME("bobik")
        override val BOTOMOK  = regionGAME("botomok")
        override val CIBCEK   = regionGAME("cibcek")
        override val CIBDEF   = regionGAME("cibdef")
        override val CIFERIKK = regionGAME("ciferikk")
        override val D7       = regionGAME("d7")
        override val DDD      = regionGAME("ddd")
        override val ETETIK   = regionGAME("etetik")
        override val KIPTIK   = regionGAME("kiptik")
        override val LOGOTYPE = regionGAME("logotype")
        override val M1       = regionGAME("m1")
        override val M6       = regionGAME("m6")
        override val PDEF     = regionGAME("pdef")
        override val PK       = regionGAME("pk")
        override val PPRES    = regionGAME("ppres")
        override val RNK      = regionGAME("rnk")
        override val TEXTS    = regionGAME("texts")
        override val TOPIK    = regionGAME("topik")
        override val Y1       = regionGAME("y1")

        override val p_LIST  = List(5) { regionGAME("p${it.inc()}") }
        override val d_LIST  = List(4) { regionGAME("d${it.inc()}") }
        override val e_LIST  = List(3) { regionGAME("e${it.inc()}") }
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