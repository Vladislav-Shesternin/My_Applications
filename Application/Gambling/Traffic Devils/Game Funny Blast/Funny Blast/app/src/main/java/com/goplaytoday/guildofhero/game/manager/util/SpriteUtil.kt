package com.goplaytoday.guildofhero.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.goplaytoday.guildofhero.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
     }

     class CommonAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val barabans     = getRegion("barabans")
          val exit_def     = getRegion("exit_def")
          val exit_press   = getRegion("exit_press")
          val go_def       = getRegion("go_def")
          val go_press     = getRegion("go_press")
          val menu_def     = getRegion("menu_def")
          val menu_press   = getRegion("menu_press")
          val minus_def    = getRegion("minus_def")
          val minus_press  = getRegion("minus_press")
          val music_check  = getRegion("music_check")
          val music_def    = getRegion("music_def")
          val play_def     = getRegion("play_def")
          val play_press   = getRegion("play_press")
          val plusic_def   = getRegion("plusic_def")
          val plusic_press = getRegion("plusic_press")
          val sett_def     = getRegion("sett_def")
          val sett_press   = getRegion("sett_press")
          val sound_check  = getRegion("sound_check")
          val sound_def    = getRegion("sound_def")
          val svetlo       = getRegion("svetlo")

          val items = List(9) { getRegion("${it.inc()}") }
     }

}