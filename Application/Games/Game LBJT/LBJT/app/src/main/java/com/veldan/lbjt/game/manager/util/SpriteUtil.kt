package com.veldan.lbjt.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.manager.SpriteManager.EnumTexture_GeneralInformation as GenInfo
import com.veldan.lbjt.game.manager.SpriteManager.EnumTexture_JointMouse as JMouse
import com.veldan.lbjt.game.manager.SpriteManager.EnumTexture_JointDistance as JDistance
import com.veldan.lbjt.game.manager.SpriteManager.EnumTexture_JointRevolute as JRevolute
import com.veldan.lbjt.game.manager.SpriteManager.EnumTexture_JointPrismatic as JPrismatic

class SpriteUtil {

     interface AllAssets {
          val CIRCLE_BLUE                  : TextureRegion
          val FRAME_LANGUAGE               : TextureRegion
          val HAND_HELLO                   : TextureRegion
          val HAND_HINT                    : TextureRegion
          val LANGUAGE_EN                  : TextureRegion
          val LANGUAGE_UK                  : TextureRegion
          val REGULAR_BTN_DEF              : TextureRegion
          val REGULAR_BTN_PRESS            : TextureRegion
          val REGULAR_BTN_DISABLE          : TextureRegion
          val ROD_LOUDER                   : TextureRegion
          val ROD_QUIET                    : TextureRegion
          val USER                         : TextureRegion
          val VOLUME_LOUDER                : TextureRegion
          val VOLUME_QUIET                 : TextureRegion
          val YAN                          : TextureRegion
          val YIN_YAN_LIGHT                : TextureRegion
          val YIN                          : TextureRegion
          val ADS                          : TextureRegion
          val DESCRIPTION_PANEL            : TextureRegion
          val EL_DAN                       : TextureRegion
          val GIFT                         : TextureRegion
          val HAND_V                       : TextureRegion
          val MONETIZATION_BTN_DEF         : TextureRegion
          val MONETIZATION_BTN_PRESS       : TextureRegion
          val MONETIZATION_MEGA_BTN_DEF    : TextureRegion
          val MONETIZATION_MEGA_BTN_PRESS  : TextureRegion
          val PS                           : TextureRegion
          val THANKS_FRAME                 : TextureRegion
          val DEBUG_BOX_DEF                : TextureRegion
          val DEBUG_BOX_CHECK              : TextureRegion
          val LOADER                       : TextureRegion
          val NO_WIFI                      : TextureRegion
          val FRAME_ICON                   : TextureRegion
          val ICON_DEF                     : TextureRegion
          val FRAME_ICON_EMPTY             : TextureRegion
          val C_DYNAMIC                    : TextureRegion
          val C_KINEMATIC                  : TextureRegion
          val C_STATIC                     : TextureRegion
          val H_DYNAMIC                    : TextureRegion
          val H_KINEMATIC                  : TextureRegion
          val H_STATIC                     : TextureRegion
          val V_DYNAMIC                    : TextureRegion
          val V_KINEMATIC                  : TextureRegion
          val V_STATIC                     : TextureRegion
          val LIFT_PLATFORM                : TextureRegion
          val LIFT_GEAR                    : TextureRegion
          val PRACTICAL_BTN                : TextureRegion
          val PRACTICAL_SETTINGS           : TextureRegion
          val PRACTICAL_DONE               : TextureRegion
          val PRACTICAL_PROGRESS_ARM       : TextureRegion
          val PRACTICAL_PROGRESS           : TextureRegion
          val PRACTICAL_PROGRESS_BACKGROUND: TextureRegion
          val PRACTICAL_PROGRESS_POINT     : TextureRegion
          val ANCHOR_POINT                 : TextureRegion
          val PRACTICAL_FALSE              : TextureRegion
          val PRACTICAL_TRUE               : TextureRegion
          val RESET_DEF                    : TextureRegion
          val RESET_PRESS                  : TextureRegion
          val UPDATE_BTN_DEF               : TextureRegion
          val UPDATE_BTN_PRESS             : TextureRegion
          val UPDATE_LIGHT                 : TextureRegion
          val UPDATE_X_DEF                 : TextureRegion
          val UPDATE_X_PRESS               : TextureRegion

          val NUMBER_LIST : List<TextureRegion>

          val PANEL                  : NinePatch
          val CURSOR                 : NinePatch
          val SELECT                 : NinePatch
          val PANEL_WITH_LIGHT_WHITE : NinePatch
          val PANEL_WITH_LIGHT_RED   : NinePatch
          val BORDERS_BLUE           : NinePatch
          val PANEL_CODE             : NinePatch
          val PRACTICAL_FRAME_WHITE  : NinePatch

          val BACKGROUND  : Texture
          val MASK_ICON   : Texture
          val ICON_VELDAN : Texture
          val UPDATE_PANEL: Texture

          val PRACTICAL_PROGRESS_MASK: Texture
     }

     abstract class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)
          protected fun getGamePath(name: String): NinePatch = SpriteManager.EnumAtlas.GAME.data.atlas.createPatch(name)

          override val CIRCLE_BLUE                   = getGameRegion("circle_blue")
          override val FRAME_LANGUAGE                = getGameRegion("frame_language")
          override val HAND_HELLO                    = getGameRegion("hand_hello")
          override val HAND_HINT                     = getGameRegion("hand_hint")
          override val LANGUAGE_EN                   = getGameRegion("language_en")
          override val LANGUAGE_UK                   = getGameRegion("language_uk")
          override val REGULAR_BTN_DEF               = getGameRegion("regular_btn_def")
          override val REGULAR_BTN_PRESS             = getGameRegion("regular_btn_press")
          override val REGULAR_BTN_DISABLE           = getGameRegion("regular_btn_disable")
          override val ROD_LOUDER                    = getGameRegion("rod_louder")
          override val ROD_QUIET                     = getGameRegion("rod_quiet")
          override val USER                          = getGameRegion("user")
          override val VOLUME_LOUDER                 = getGameRegion("volume_louder")
          override val VOLUME_QUIET                  = getGameRegion("volume_quiet")
          override val YAN                           = getGameRegion("yan")
          override val YIN_YAN_LIGHT                 = getGameRegion("yin_yan_light")
          override val YIN                           = getGameRegion("yin")
          override val ADS                           = getGameRegion("ads")
          override val DESCRIPTION_PANEL             = getGameRegion("description_panel")
          override val EL_DAN                        = getGameRegion("el_dan")
          override val GIFT                          = getGameRegion("gift")
          override val HAND_V                        = getGameRegion("hand_v")
          override val MONETIZATION_BTN_DEF          = getGameRegion("monetization_btn_def")
          override val MONETIZATION_BTN_PRESS        = getGameRegion("monetization_btn_press")
          override val MONETIZATION_MEGA_BTN_DEF     = getGameRegion("monetization_mega_btn_def")
          override val MONETIZATION_MEGA_BTN_PRESS   = getGameRegion("monetization_mega_btn_press")
          override val PS                            = getGameRegion("ps")
          override val THANKS_FRAME                  = getGameRegion("thanks_frame")
          override val DEBUG_BOX_DEF                 = getGameRegion("debug_box_def")
          override val DEBUG_BOX_CHECK               = getGameRegion("debug_box_check")
          override val LOADER                        = getGameRegion("loader")
          override val NO_WIFI                       = getGameRegion("no_wifi")
          override val FRAME_ICON                    = getGameRegion("frame_icon")
          override val ICON_DEF                      = getGameRegion("icon_def")
          override val FRAME_ICON_EMPTY              = getGameRegion("frame_icon_empty")
          override val C_DYNAMIC                     = getGameRegion("c_dynamic")
          override val C_KINEMATIC                   = getGameRegion("c_kinematic")
          override val C_STATIC                      = getGameRegion("c_static")
          override val H_DYNAMIC                     = getGameRegion("h_dynamic")
          override val H_KINEMATIC                   = getGameRegion("h_kinematic")
          override val H_STATIC                      = getGameRegion("h_static")
          override val V_DYNAMIC                     = getGameRegion("v_dynamic")
          override val V_KINEMATIC                   = getGameRegion("v_kinematic")
          override val V_STATIC                      = getGameRegion("v_static")
          override val LIFT_PLATFORM                 = getGameRegion("lift_platform")
          override val LIFT_GEAR                     = getGameRegion("lift_gear")
          override val PRACTICAL_BTN                 = getGameRegion("practical_btn")
          override val PRACTICAL_SETTINGS            = getGameRegion("practical_settings")
          override val PRACTICAL_DONE                = getGameRegion("practical_done")
          override val PRACTICAL_PROGRESS_ARM        = getGameRegion("practical_progress_arm")
          override val PRACTICAL_PROGRESS            = getGameRegion("practical_progress")
          override val PRACTICAL_PROGRESS_BACKGROUND = getGameRegion("practical_progress_background")
          override val PRACTICAL_PROGRESS_POINT      = getGameRegion("practical_progress_point")
          override val ANCHOR_POINT                  = getGameRegion("anchor_point")
          override val PRACTICAL_FALSE               = getGameRegion("practical_false")
          override val PRACTICAL_TRUE                = getGameRegion("practical_true")
          override val RESET_DEF                     = getGameRegion("reset_def")
          override val RESET_PRESS                   = getGameRegion("reset_press")
          override val UPDATE_BTN_DEF                = getGameRegion("update_btn_def")
          override val UPDATE_BTN_PRESS              = getGameRegion("update_btn_press")
          override val UPDATE_LIGHT                  = getGameRegion("update_light")
          override val UPDATE_X_DEF                  = getGameRegion("update_x_def")
          override val UPDATE_X_PRESS                = getGameRegion("update_x_press")

          override val NUMBER_LIST = List(9) { getGameRegion("number ${it.inc()}") }

          override val PANEL                  = getGamePath("panel")
          override val CURSOR                 = getGamePath("cursor")
          override val SELECT                 = getGamePath("select")
          override val PANEL_WITH_LIGHT_WHITE = getGamePath("panel_with_light_white")
          override val PANEL_WITH_LIGHT_RED   = getGamePath("panel_with_light_red")
          override val BORDERS_BLUE           = getGamePath("borders_blue")
          override val PANEL_CODE             = getGamePath("panel_code")
          override val PRACTICAL_FRAME_WHITE  = getGamePath("practical_frame_white")

          override val MASK_ICON   = SpriteManager.EnumTexture.MASK_ICON.data.texture
          override val ICON_VELDAN = SpriteManager.EnumTexture.VELDAN_ICON.data.texture

          override val PRACTICAL_PROGRESS_MASK = SpriteManager.EnumTexture.PRACTICAL_PROGRESS_MASK.data.texture
          override val UPDATE_PANEL            = SpriteManager.EnumTexture.UPDATE_PANEL.data.texture
     }

     class YanAssets: CommonAssets() {
          override val BACKGROUND = SpriteManager.EnumTexture.YAN_BACKGROUND.data.texture
     }

     class YinAssets: CommonAssets() {
          override val BACKGROUND = SpriteManager.EnumTexture.YIN_BACKGROUND.data.texture
     }

     // ---------------------------------------------------
     // Tutorials
     // ---------------------------------------------------

     class GeneralInformation {
          private fun getRegion(name: String, atlasData: SpriteManager.AtlasData): TextureRegion = atlasData.atlas.findRegion(name)

          val animObama = List<TextureRegion>(63) { getRegion("anim_obama (${it.inc()})", SpriteManager.EnumAtlas_GeneralInformation.ANIM_OBAMA.data) }

          val I1  = GenInfo.I1.data.texture
          val I2  = GenInfo.I2.data.texture
          val I3  = GenInfo.I3.data.texture
          val I4  = GenInfo.I4.data.texture
          val I5  = GenInfo.I5.data.texture
          val I6  = GenInfo.I6.data.texture
          val I7  = GenInfo.I7.data.texture
          val I8  = GenInfo.I8.data.texture
          val I9  = GenInfo.I9.data.texture
          val I10 = GenInfo.I10.data.texture
          val I11 = GenInfo.I11.data.texture
     }

     class JointMouse: TutorialsAssets {
          private fun getRegion(name: String, atlasData: SpriteManager.AtlasData): TextureRegion = atlasData.atlas.findRegion(name)

          private val animVideo_1_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_1_0.data) }
          private val animVideo_1_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_1_1.data) }
          private val animVideo_1_2 = List<TextureRegion>(78) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_1_2.data) }
          private val animVideo_1_3 = List<TextureRegion>(27) { getRegion("video (${it.inc()+234})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_1_3.data) }

          private val animVideo_2_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_2_0.data) }
          private val animVideo_2_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_2_1.data) }
          private val animVideo_2_2 = List<TextureRegion>(78) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_2_2.data) }
          private val animVideo_2_3 = List<TextureRegion>(78) { getRegion("video (${it.inc()+234})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_2_3.data) }
          private val animVideo_2_4 = List<TextureRegion>(78) { getRegion("video (${it.inc()+312})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_2_4.data) }
          private val animVideo_2_5 = List<TextureRegion>(58) { getRegion("video (${it.inc()+390})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_2_5.data) }

          private val animVideo_3_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_3_0.data) }
          private val animVideo_3_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_3_1.data) }
          private val animVideo_3_2 = List<TextureRegion>(78) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_3_2.data) }
          private val animVideo_3_3 = List<TextureRegion>(78) { getRegion("video (${it.inc()+234})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_3_3.data) }
          private val animVideo_3_4 = List<TextureRegion>(78) { getRegion("video (${it.inc()+312})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_3_4.data) }
          private val animVideo_3_5 = List<TextureRegion>(78) { getRegion("video (${it.inc()+390})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_3_5.data) }
          private val animVideo_3_6 = List<TextureRegion>(18) { getRegion("video (${it.inc()+468})", SpriteManager.EnumAtlas_JointMouse.ANIM_VIDEO_3_6.data) }

          private val mem_1 = List<TextureRegion>(78) { getRegion("mem (${it.inc()})", SpriteManager.EnumAtlas_JointMouse.MEM_1.data) }
          private val mem_2 = List<TextureRegion>(35) { getRegion("mem (${it.inc()+78})", SpriteManager.EnumAtlas_JointMouse.MEM_2.data) }

          val animVideo_1 = animVideo_1_0+animVideo_1_1+animVideo_1_2+animVideo_1_3
          val animVideo_2 = animVideo_2_0+animVideo_2_1+animVideo_2_2+animVideo_2_3+animVideo_2_4+animVideo_2_5
          val animVideo_3 = animVideo_3_0+animVideo_3_1+animVideo_3_2+animVideo_3_3+animVideo_3_4+animVideo_3_5+animVideo_3_6

          val mem = mem_1+mem_2

          val I1  = JMouse.I1.data.texture
     }

     class JointDistance: TutorialsAssets {
          private fun getRegion(name: String, atlasData: SpriteManager.AtlasData): TextureRegion = atlasData.atlas.findRegion(name)

          private val animVideo_1_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_1_0.data) }
          private val animVideo_1_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_1_1.data) }
          private val animVideo_1_2 = List<TextureRegion>(31) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_1_2.data) }

          private val animVideo_2_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_2_0.data) }
          private val animVideo_2_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_2_1.data) }
          private val animVideo_2_2 = List<TextureRegion>(45) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_2_2.data) }

          private val animVideo_3_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_3_0.data) }
          private val animVideo_3_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_3_1.data) }
          private val animVideo_3_2 = List<TextureRegion>(78) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_3_2.data) }
          private val animVideo_3_3 = List<TextureRegion>(35) { getRegion("video (${it.inc()+234})", SpriteManager.EnumAtlas_JointDistance.ANIM_VIDEO_3_3.data) }

          private val mem_1 = List<TextureRegion>(83) { getRegion("mem (${it.inc()})", SpriteManager.EnumAtlas_JointDistance.MEM_1.data) }
          private val mem_2 = List<TextureRegion>(29) { getRegion("mem (${it.inc()+83})", SpriteManager.EnumAtlas_JointDistance.MEM_2.data) }

          val animVideo_1 = animVideo_1_0+animVideo_1_1+animVideo_1_2
          val animVideo_2 = animVideo_2_0+animVideo_2_1+animVideo_2_2
          val animVideo_3 = animVideo_3_0+animVideo_3_1+animVideo_3_2+animVideo_3_3

          val mem = mem_1+mem_2

          val I1 = JDistance.I1.data.texture
          val I2 = JDistance.I2.data.texture
     }

     class JointRevolute: TutorialsAssets {
          private fun getRegion(name: String, atlasData: SpriteManager.AtlasData): TextureRegion = atlasData.atlas.findRegion(name)

          private val animVideo_1_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_1_0.data) }
          private val animVideo_1_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_1_1.data) }
          private val animVideo_1_2 = List<TextureRegion>(78) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_1_2.data) }
          private val animVideo_1_3 = List<TextureRegion>(13) { getRegion("video (${it.inc()+234})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_1_3.data) }

          private val animVideo_2_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_2_0.data) }
          private val animVideo_2_1 = List<TextureRegion>(61) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_2_1.data) }

          private val animVideo_3_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_3_0.data) }
          private val animVideo_3_1 = List<TextureRegion>(12) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_3_1.data) }

          private val animVideo_4_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_4_0.data) }
          private val animVideo_4_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_4_1.data) }
          private val animVideo_4_2 = List<TextureRegion>(78) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_4_2.data) }
          private val animVideo_4_3 = List<TextureRegion>(1) { getRegion("video (${it.inc()+234})", SpriteManager.EnumAtlas_JointRevolute.ANIM_VIDEO_4_3.data) }

          private val mem_1 = List<TextureRegion>(25) { getRegion("mem (${it.inc()})", SpriteManager.EnumAtlas_JointRevolute.MEM_1.data) }
          private val mem_2 = List<TextureRegion>(25) { getRegion("mem (${it.inc()+25})", SpriteManager.EnumAtlas_JointRevolute.MEM_2.data) }

          val animVideo_1 = animVideo_1_0+animVideo_1_1+animVideo_1_2+animVideo_1_3
          val animVideo_2 = animVideo_2_0+animVideo_2_1
          val animVideo_3 = animVideo_3_0+animVideo_3_1
          val animVideo_4 = animVideo_4_0+animVideo_4_1+animVideo_4_2+animVideo_4_3

          val mem = mem_1+mem_2

          val I1 = JRevolute.I1.data.texture
          val I2 = JRevolute.I2.data.texture
     }

     class JointPrismatic: TutorialsAssets {
          private fun getRegion(name: String, atlasData: SpriteManager.AtlasData): TextureRegion = atlasData.atlas.findRegion(name)

          private val animVideo_1_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_1_0.data) }
          private val animVideo_1_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_1_1.data) }
          private val animVideo_1_2 = List<TextureRegion>(7) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_1_2.data) }

          private val animVideo_2_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_2_0.data) }
          private val animVideo_2_1 = List<TextureRegion>(20) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_2_1.data) }

          private val animVideo_3_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_3_0.data) }
          private val animVideo_3_1 = List<TextureRegion>(13) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_3_1.data) }

          private val animVideo_4_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_4_0.data) }
          private val animVideo_4_1 = List<TextureRegion>(24) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_4_1.data) }

          private val animVideo_5_0 = List<TextureRegion>(78) { getRegion("video (${it.inc()})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_5_0.data) }
          private val animVideo_5_1 = List<TextureRegion>(78) { getRegion("video (${it.inc()+78})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_5_1.data) }
          private val animVideo_5_2 = List<TextureRegion>(49) { getRegion("video (${it.inc()+156})", SpriteManager.EnumAtlas_JointPrismatic.ANIM_VIDEO_5_2.data) }

          private val mem_1 = List<TextureRegion>(25) { getRegion("mem (${it.inc()})", SpriteManager.EnumAtlas_JointPrismatic.MEM_1.data) }

          val animVideo_1 = animVideo_1_0+animVideo_1_1+animVideo_1_2
          val animVideo_2 = animVideo_2_0+animVideo_2_1
          val animVideo_3 = animVideo_3_0+animVideo_3_1
          val animVideo_4 = animVideo_4_0+animVideo_4_1
          val animVideo_5 = animVideo_5_0+animVideo_5_1+animVideo_5_2

          val mem = mem_1

          val I1 = JPrismatic.I1.data.texture
          val I2 = JPrismatic.I2.data.texture
          val I3 = JPrismatic.I3.data.texture
          val I4 = JPrismatic.I4.data.texture
          val I5 = JPrismatic.I5.data.texture
     }

     interface TutorialsAssets
}