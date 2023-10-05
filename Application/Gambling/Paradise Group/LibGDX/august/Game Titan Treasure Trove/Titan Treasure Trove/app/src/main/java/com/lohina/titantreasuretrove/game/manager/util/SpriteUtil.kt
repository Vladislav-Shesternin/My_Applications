package com.lohina.titantreasuretrove.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.lohina.titantreasuretrove.game.manager.SpriteManager
import com.lohina.titantreasuretrove.game.utils.region

class SpriteUtil {

    fun regionGAME(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

    interface SplashAtlas {
        val BACKGROUND : TextureRegion
        val ZEFS       : TextureRegion
    }

    interface GameAtlas {
        val EXIT      : TextureRegion
        val LIGHTNING : TextureRegion
        val PANEL     : TextureRegion
        val PLAY      : TextureRegion
        val TUTORIAL  : TextureRegion
        val UNDERSTOOD: TextureRegion
        val ITEM_LIST : List<TextureRegion>
    }

    val SPLASH by lazy { object: SplashAtlas {
        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture.region
        override val ZEFS       = SpriteManager.EnumTexture.ZEFS.data.texture.region
    } }

    val GAME by lazy { object: GameAtlas {
        override val EXIT       = regionGAME("exit")
        override val LIGHTNING  = regionGAME("lightning")
        override val PANEL      = regionGAME("panel")
        override val PLAY       = regionGAME("play")
        override val TUTORIAL   = regionGAME("tutorial")
        override val UNDERSTOOD = regionGAME("understood")

        override val ITEM_LIST  = List(5) { regionGAME("${it.inc()}") }
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