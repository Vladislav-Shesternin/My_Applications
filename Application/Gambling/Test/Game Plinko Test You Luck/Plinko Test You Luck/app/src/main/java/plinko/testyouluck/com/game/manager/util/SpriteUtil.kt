package plinko.testyouluck.com.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import plinko.testyouluck.com.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.SPLASH.data.atlas.findRegion(name)

          val LOADER_BACKENDER = getRegion("loader_backender")
          val LOADER_PROGRESS  = getRegion("loader_progress")

          val PLINKO_BACKGROUND  = SpriteManager.EnumTexture.PLINKO_BACKGROUND.data.texture
          val COINS_AND_BOMB     = SpriteManager.EnumTexture.COINS_AND_BOMB.data.texture
          val LOADER_PLINKO_BALL = SpriteManager.EnumTexture.LOADER_PLINKO_BALL.data.texture
          val LOADER_MASK        = SpriteManager.EnumTexture.LOADER_MASK.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val BALL_5000      = getRegion("ball-5000")
          val BALL_8500      = getRegion("ball-8500")
          val BALL_10000     = getRegion("ball-10000")
          val BALL_DEFAULT   = getRegion("ball_default")
          val BOMB           = getRegion("bomb")
          val CB_SOUND       = getRegion("cb_sound")
          val CB_SOUND_NOT   = getRegion("cb_sound_not")
          val COINS_BAR      = getRegion("coins_bar")
          val DIVISION       = getRegion("division")
          val MENU_BTN       = getRegion("menu_btn")
          val PAUSE          = getRegion("pause")
          val PLAY           = getRegion("play")
          val PLAY_BTN       = getRegion("play_btn")
          val PLAY_BTN_PRESS = getRegion("play_btn_press")
          val TIMER_BAR      = getRegion("timer_bar")
          val X2             = getRegion("x2")
          val X5             = getRegion("x5")
          val PORTAL         = getRegion("portal")

          val MENU_BAR         = SpriteManager.EnumTexture.MENU_BAR.data.texture
          val PLINKO_PURPLE    = SpriteManager.EnumTexture.PLINKO_PURPLE.data.texture
          val PLINKO_RED       = SpriteManager.EnumTexture.PLINKO_RED.data.texture
          val PLINKO_RESULT    = SpriteManager.EnumTexture.PLINKO_RESULT.data.texture
          val RULES_BAR        = SpriteManager.EnumTexture.RULES_BAR.data.texture
          val SETTINGS_BAR     = SpriteManager.EnumTexture.SETTINGS_BAR.data.texture
          val SHOP_BAR         = SpriteManager.EnumTexture.SHOP_BAR.data.texture
          val TOP_BOTTOM_BALLS = SpriteManager.EnumTexture.TOP_BOTTOM_BALLS.data.texture
          val TEST             = SpriteManager.EnumTexture.TEST.data.texture
     }

}