package com.zeuse.zeusjackpotjamboree.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.zeuse.zeusjackpotjamboree.game.manager.SpriteManager
import com.zeuse.zeusjackpotjamboree.game.utils.region

class SpriteUtil {

    fun regionGAME(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

    // Game
    interface ITextureRegionContainer {
        val BLACK    : TextureRegion
        val EXIT     : TextureRegion
        val PLAY     : TextureRegion
        val SCORE    : TextureRegion
        val SETTINGS : TextureRegion
        val WHITE    : TextureRegion
        val ZEUS     : TextureRegion
        val ITEM_LIST: List<TextureRegion>

        val BACKGROUND: TextureRegion
    }

    abstract inner class CommonRegion: ITextureRegionContainer {
        override val BLACK     = regionGAME("black")
        override val EXIT      = regionGAME("exit")
        override val PLAY      = regionGAME("play")
        override val SCORE     = regionGAME("score")
        override val SETTINGS  = regionGAME("settings")
        override val WHITE     = regionGAME("white")
        override val ZEUS      = regionGAME("zeus")

        override val ITEM_LIST = List(7) { regionGAME("${it.inc()}") }
    }

    inner class BlackRegion: CommonRegion() {
        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND_BLACK.data.texture.region
    }

    inner class WhiteRegion: CommonRegion() {
        override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND_WHITE.data.texture.region
    }

//    enum class Game9Path(val ninePatch: NinePatch) {
//        JOINT(EnumAtlas.GAME.data.atlas.createPatch("joint")),
//    }

    //    enum class ListRegion(val regionList: List<TextureRegion>) {
    //        LOADER( List(101) { EnumAtlas.LOADER.data.atlas.findRegion("$it") }),
    //    }

}