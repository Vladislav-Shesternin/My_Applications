package rainbowriches.lucky.start.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import rainbowriches.lucky.start.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          protected fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.SPLASH.data.atlas.findRegion(name)

          val CURSOR      = getRegion("cursor")
          val FRONT_BAR   = getRegion("front_bar")
          val LEPRECHAUN  = getRegion("leprechaun")
          val OUTSIDE_BAR = getRegion("outside_bar")

          val ASK_MY_NAME = SpriteManager.EnumTexture.ASK_MY_NAME.data.texture
          val BLACKWOOD   = SpriteManager.EnumTexture.BLACKWOOD.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val ARMORE            = getRegion("armore")
          val FRAME_FOR_ARMORE  = getRegion("frame_for_armore")
          val GEAR_DEF          = getRegion("gear_def")
          val GEAR_DIS          = getRegion("gear_dis")
          val L_EASY            = getRegion("l_easy")
          val L_HARD            = getRegion("l_hard")
          val L_NORMAL          = getRegion("l_normal")
          val LEVEL             = getRegion("level")
          val MENU_DEF          = getRegion("menu_def")
          val MENU_DIS          = getRegion("menu_dis")
          val MUSIC_DEF         = getRegion("music_def")
          val MUSIC_DIS         = getRegion("music_dis")
          val PAPER_DEF         = getRegion("paper_def")
          val PAPER_DIS         = getRegion("paper_dis")
          val PAUSE_DEF         = getRegion("pause_def")
          val PAUSE_DIS         = getRegion("pause_dis")
          val RULES             = getRegion("rules")
          val SEASON_FRAME_BACK = getRegion("season_frame_back")
          val SEASON_GREEN      = getRegion("season_green")
          val SEASON_TIME       = getRegion("season_time")
          val SETTINGS          = getRegion("settings")
          val SOUND_MUSIC       = getRegion("sound_music")
          val X                 = getRegion("x")
          val X_DEF             = getRegion("x_def")
          val X_DIS             = getRegion("x_dis")
          val YOU_LOSE          = getRegion("you_lose")
          val YOU_WIN           = getRegion("you_win")

          val PANEL_GREEN     = SpriteManager.EnumTexture.PANEL_GREEN.data.texture
          val PANEL_PLAY_GAME = SpriteManager.EnumTexture.PANEL_PLAY_GAME.data.texture
          val PLAY_DEF        = SpriteManager.EnumTexture.PLAY_DEF.data.texture
          val PLAY_DIS        = SpriteManager.EnumTexture.PLAY_DIS.data.texture
          val PLAYGROUND      = SpriteManager.EnumTexture.PLAYGROUND.data.texture
          val SEASON          = SpriteManager.EnumTexture.SEASON.data.texture
          val WIN_LOSE_BLOOR  = SpriteManager.EnumTexture.WIN_LOSE_BLOOR.data.texture
          val test  = SpriteManager.EnumTexture.test.data.texture

          private val _1 = SpriteManager.EnumTexture._1.data.texture
          private val _2 = SpriteManager.EnumTexture._2.data.texture
          private val _3 = SpriteManager.EnumTexture._3.data.texture
          private val _4 = SpriteManager.EnumTexture._4.data.texture
          private val _5 = SpriteManager.EnumTexture._5.data.texture
          private val _6 = SpriteManager.EnumTexture._6.data.texture
          private val _7 = SpriteManager.EnumTexture._7.data.texture
          private val _8 = SpriteManager.EnumTexture._8.data.texture
          private val _9 = SpriteManager.EnumTexture._9.data.texture

          val puzzleList = listOf(_1,_2,_3,_4,_5,_6,_7,_8,_9)
     }

}