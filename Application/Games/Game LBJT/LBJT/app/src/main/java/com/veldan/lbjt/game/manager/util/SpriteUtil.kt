package com.veldan.lbjt.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.lbjt.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val CIRCLE_BLUE                 : TextureRegion
          val FRAME_LANGUAGE              : TextureRegion
          val HAND_HELLO                  : TextureRegion
          val HAND_HINT                   : TextureRegion
          val LANGUAGE_EN                 : TextureRegion
          val LANGUAGE_UK                 : TextureRegion
          val REGULAR_BTN_DEF             : TextureRegion
          val REGULAR_BTN_PRESS           : TextureRegion
          val REGULAR_BTN_DISABLE         : TextureRegion
          val ROD_LOUDER                  : TextureRegion
          val ROD_QUIET                   : TextureRegion
          val USER                        : TextureRegion
          val VOLUME_LOUDER               : TextureRegion
          val VOLUME_QUIET                : TextureRegion
          val YAN                         : TextureRegion
          val YIN_YAN_LIGHT               : TextureRegion
          val YIN                         : TextureRegion
          val ADS                         : TextureRegion
          val DESCRIPTION_PANEL           : TextureRegion
          val EL_DAN                      : TextureRegion
          val GIFT                        : TextureRegion
          val HAND_V                      : TextureRegion
          val MONETIZATION_BTN_DEF        : TextureRegion
          val MONETIZATION_BTN_PRESS      : TextureRegion
          val MONETIZATION_MEGA_BTN_DEF   : TextureRegion
          val MONETIZATION_MEGA_BTN_PRESS : TextureRegion
          val PS                          : TextureRegion
          val THANKS_FRAME                : TextureRegion
          val DEBUG_BOX_DEF               : TextureRegion
          val DEBUG_BOX_CHECK             : TextureRegion
          val LOADER                      : TextureRegion
          val NO_WIFI                     : TextureRegion
          val FRAME_ICON                  : TextureRegion
          val ICON_DEF                    : TextureRegion
          val FRAME_ICON_EMPTY            : TextureRegion

          val PANEL                  : NinePatch
          val CURSOR                 : NinePatch
          val SELECT                 : NinePatch
          val PANEL_WITH_LIGHT_WHITE : NinePatch
          val PANEL_WITH_LIGHT_RED   : NinePatch

          val BACKGROUND : Texture
          val MASK_ICON  : Texture
          val ICON_VELDAN: Texture

     }

     abstract class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)
          protected fun getGamePath(name: String): NinePatch = SpriteManager.EnumAtlas.GAME.data.atlas.createPatch(name)

          override val CIRCLE_BLUE                 = getGameRegion("circle_blue")
          override val FRAME_LANGUAGE              = getGameRegion("frame_language")
          override val HAND_HELLO                  = getGameRegion("hand_hello")
          override val HAND_HINT                   = getGameRegion("hand_hint")
          override val LANGUAGE_EN                 = getGameRegion("language_en")
          override val LANGUAGE_UK                 = getGameRegion("language_uk")
          override val REGULAR_BTN_DEF             = getGameRegion("regular_btn_def")
          override val REGULAR_BTN_PRESS           = getGameRegion("regular_btn_press")
          override val REGULAR_BTN_DISABLE         = getGameRegion("regular_btn_disable")
          override val ROD_LOUDER                  = getGameRegion("rod_louder")
          override val ROD_QUIET                   = getGameRegion("rod_quiet")
          override val USER                        = getGameRegion("user")
          override val VOLUME_LOUDER               = getGameRegion("volume_louder")
          override val VOLUME_QUIET                = getGameRegion("volume_quiet")
          override val YAN                         = getGameRegion("yan")
          override val YIN_YAN_LIGHT               = getGameRegion("yin_yan_light")
          override val YIN                         = getGameRegion("yin")
          override val ADS                         = getGameRegion("ads")
          override val DESCRIPTION_PANEL           = getGameRegion("description_panel")
          override val EL_DAN                      = getGameRegion("el_dan")
          override val GIFT                        = getGameRegion("gift")
          override val HAND_V                      = getGameRegion("hand_v")
          override val MONETIZATION_BTN_DEF        = getGameRegion("monetization_btn_def")
          override val MONETIZATION_BTN_PRESS      = getGameRegion("monetization_btn_press")
          override val MONETIZATION_MEGA_BTN_DEF   = getGameRegion("monetization_mega_btn_def")
          override val MONETIZATION_MEGA_BTN_PRESS = getGameRegion("monetization_mega_btn_press")
          override val PS                          = getGameRegion("ps")
          override val THANKS_FRAME                = getGameRegion("thanks_frame")
          override val DEBUG_BOX_DEF               = getGameRegion("debug_box_def")
          override val DEBUG_BOX_CHECK             = getGameRegion("debug_box_check")
          override val LOADER                      = getGameRegion("loader")
          override val NO_WIFI                     = getGameRegion("no_wifi")
          override val FRAME_ICON                  = getGameRegion("frame_icon")
          override val ICON_DEF                    = getGameRegion("icon_def")
          override val FRAME_ICON_EMPTY            = getGameRegion("frame_icon_empty")

          override val PANEL                  = getGamePath("panel")
          override val CURSOR                 = getGamePath("cursor")
          override val SELECT                 = getGamePath("select")
          override val PANEL_WITH_LIGHT_WHITE = getGamePath("panel_with_light_white")
          override val PANEL_WITH_LIGHT_RED   = getGamePath("panel_with_light_red")

          override val MASK_ICON   = SpriteManager.EnumTexture.MASK_ICON.data.texture
          override val ICON_VELDAN = SpriteManager.EnumTexture.VELDAN_ICON.data.texture
     }

     class YanAssets: CommonAssets() {
          override val BACKGROUND = SpriteManager.EnumTexture.YAN_BACKGROUND.data.texture
     }

     class YinAssets: CommonAssets() {
          override val BACKGROUND = SpriteManager.EnumTexture.YIN_BACKGROUND.data.texture
     }
}