package com.roshevasternin.rozval.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.roshevasternin.rozval.game.manager.SpriteManager

class SpriteUtil {

     class Loader {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.LOADER.data.atlas.findRegion(name)

          val loader = getRegion("loader")

          val builderList = List(4) { getRegion("builder-${it.inc()}") }
     }

     class All {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)
//          private fun getNinePath(name: String): NinePatch = SpriteManager.EnumAtlas.all.data.atlas.createPatch(name)

          val bonus_game_def      = getRegion("bonus_game_def")
          val bonus_game_press    = getRegion("bonus_game_press")
          val exit_def            = getRegion("exit_def")
          val exit_press          = getRegion("exit_press")
          val key                 = getRegion("key")
          val menu_def            = getRegion("menu_def")
          val menu_press          = getRegion("menu_press")
          val music_check         = getRegion("music_check")
          val music_def           = getRegion("music_def")
          val sound_check         = getRegion("sound_check")
          val sound_def           = getRegion("sound_def")
          val star                = getRegion("star")
          val apply_def           = getRegion("apply_def")
          val apply_press         = getRegion("apply_press")
          val progress_arm        = getRegion("progress_arm")
          val progress_background = getRegion("progress_background")
          val progress_progress   = getRegion("progress_progress")

//          val items = List(8) { getRegion("${it.inc()}") }

          val PANEL         = SpriteManager.EnumTexture.PANEL.data.texture
          val PROGRESS_MASK = SpriteManager.EnumTexture.PROGRESS_MASK.data.texture

//          val visible_area = getNinePath("visible_area")

     }

}