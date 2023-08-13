package com.veldan.lbjt.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.utils.region

class SpriteUtil {

    fun regionGAME(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

    interface ITextureRegionContainer {
        val CIRCLE_BLUE                : TextureRegion
        val FRAME_LANGUAGE             : TextureRegion
        val HAND_HELLO                 : TextureRegion
        val HAND_HINT                  : TextureRegion
        val LANGUAGE_EN                : TextureRegion
        val LANGUAGE_UK                : TextureRegion
        val REGULAR_BTN_DEF            : TextureRegion
        val REGULAR_BTN_PRESS          : TextureRegion
        val ROD_LOUDER                 : TextureRegion
        val ROD_QUIET                  : TextureRegion
        val USER                       : TextureRegion
        val VOLUME_LOUDER              : TextureRegion
        val VOLUME_QUIET               : TextureRegion
        val YAN                        : TextureRegion
        val YIN_YAN_LIGHT              : TextureRegion
        val YIN                        : TextureRegion
        val ADS                        : TextureRegion
        val DESCRIPTION_PANEL          : TextureRegion
        val EL_DAN                     : TextureRegion
        val GIFT                       : TextureRegion
        val HAND_V                     : TextureRegion
        val MONETIZATION_BTN_DEF       : TextureRegion
        val MONETIZATION_BTN_PRESS     : TextureRegion
        val MONETIZATION_MEGA_BTN_DEF  : TextureRegion
        val MONETIZATION_MEGA_BTN_PRESS: TextureRegion
        val PS                         : TextureRegion
        val THANKS_FRAME               : TextureRegion

        val BACKGROUND: TextureRegion
    }

    abstract inner class CommonRegion: ITextureRegionContainer {
        override val CIRCLE_BLUE                 = regionGAME("circle_blue")
        override val FRAME_LANGUAGE              = regionGAME("frame_language")
        override val HAND_HELLO                  = regionGAME("hand_hello")
        override val HAND_HINT                   = regionGAME("hand_hint")
        override val LANGUAGE_EN                 = regionGAME("language_en")
        override val LANGUAGE_UK                 = regionGAME("language_uk")
        override val REGULAR_BTN_DEF             = regionGAME("regular_btn_def")
        override val REGULAR_BTN_PRESS           = regionGAME("regular_btn_press")
        override val ROD_LOUDER                  = regionGAME("rod_louder")
        override val ROD_QUIET                   = regionGAME("rod_quiet")
        override val USER                        = regionGAME("user")
        override val VOLUME_LOUDER               = regionGAME("volume_louder")
        override val VOLUME_QUIET                = regionGAME("volume_quiet")
        override val YAN                         = regionGAME("yan")
        override val YIN_YAN_LIGHT               = regionGAME("yin_yan_light")
        override val YIN                         = regionGAME("yin")
        override val ADS                         = regionGAME("ads")
        override val DESCRIPTION_PANEL           = regionGAME("description_panel")
        override val EL_DAN                      = regionGAME("el_dan")
        override val GIFT                        = regionGAME("gift")
        override val HAND_V                      = regionGAME("hand_v")
        override val MONETIZATION_BTN_DEF        = regionGAME("monetization_btn_def")
        override val MONETIZATION_BTN_PRESS      = regionGAME("monetization_btn_press")
        override val MONETIZATION_MEGA_BTN_DEF   = regionGAME("monetization_mega_btn_def")
        override val MONETIZATION_MEGA_BTN_PRESS = regionGAME("monetization_mega_btn_press")
        override val PS                          = regionGAME("ps")
        override val THANKS_FRAME                = regionGAME("thanks_frame")
    }

    inner class BlackRegion: CommonRegion() {
        override val BACKGROUND = SpriteManager.EnumTexture.YIN_BACKGROUND.data.texture.region
    }

    inner class WhiteRegion: CommonRegion() {
        override val BACKGROUND = SpriteManager.EnumTexture.YAN_BACKGROUND.data.texture.region
    }

//    enum class Game9Path(val ninePatch: NinePatch) {
//        JOINT(EnumAtlas.GAME.data.atlas.createPatch("joint")),
//    }

    //    enum class ListRegion(val regionList: List<TextureRegion>) {
    //        LOADER( List(101) { EnumAtlas.LOADER.data.atlas.findRegion("$it") }),
    //    }

}