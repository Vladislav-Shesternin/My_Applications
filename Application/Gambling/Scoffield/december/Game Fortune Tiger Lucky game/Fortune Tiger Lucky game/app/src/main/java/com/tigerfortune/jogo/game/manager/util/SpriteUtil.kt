package com.tigerfortune.jogo.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tigerfortune.jogo.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.SPLASH.data.atlas.findRegion(name)

          val progress     = getRegion("progress")
          val progress_bar = getRegion("progress_bar")

          val TigerLoading  = SpriteManager.EnumTexture.TigerLoading.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val bomb        = getRegion("bomb")
          val cb_check    = getRegion("cb_check")
          val cb_def      = getRegion("cb_def")
          val menu_def    = getRegion("menu_def")
          val menu_press  = getRegion("menu_press")
          val music_check = getRegion("music_check")
          val music_def   = getRegion("music_def")
          val ok_def      = getRegion("ok_def")
          val ok_press    = getRegion("ok_press")
          val panel       = getRegion("panel")
          val pause_check = getRegion("pause_check")
          val pause_def   = getRegion("pause_def")
          val time_panel  = getRegion("time_panel")
          val volume      = getRegion("volume")
          val tiger_1     = getRegion("tiger-1")
          val tiger_2     = getRegion("tiger-2")

          val items = List(7) { getRegion("${it.inc()}") }

          val FAIL           = SpriteManager.EnumTexture.FAIL.data.texture
          val PANEL_MENU     = SpriteManager.EnumTexture.PANEL_MENU.data.texture
          val PANEL_RULES    = SpriteManager.EnumTexture.PANEL_RULES.data.texture
          val PANEL_SETTINGS = SpriteManager.EnumTexture.PANEL_SETTINGS.data.texture
          val TIGERCHOOSE    = SpriteManager.EnumTexture.TIGERCHOOSE.data.texture
          val TWO_TIGER      = SpriteManager.EnumTexture.TWO_TIGER.data.texture
          val WIN            = SpriteManager.EnumTexture.WIN.data.texture
     }

}