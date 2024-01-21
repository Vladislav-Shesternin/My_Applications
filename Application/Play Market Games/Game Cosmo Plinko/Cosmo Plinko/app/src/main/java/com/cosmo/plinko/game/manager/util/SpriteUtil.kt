package com.cosmo.plinko.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.cosmo.plinko.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          protected fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.SPLASH.data.atlas.findRegion(name)

          val P_BACKGROUND = getRegion("background")
          val KOMETA       = getRegion("kometa")
          val LUNA         = getRegion("luna")
          val PALANET      = getRegion("palanet")
          val PROGRESS     = getRegion("progress")

          val I_LIST     = List(6) { getRegion("i${it.inc()}") }
          val MUSOR_LIST = List(5) { getRegion("musor-${it.inc()}") }


          val BACGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
          val MASK      = SpriteManager.EnumTexture.MASK.data.texture

     }

     class GameAssets {
          protected fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val BH_TOP          = getRegion("bh_top")
          val BLACK_HOLE      = getRegion("black_hole")
          val BTN_DEF         = getRegion("btn_def")
          val BTN_PRS         = getRegion("btn_prs")
          val COSMO_BOOTTOM   = getRegion("cosmo_boottom")
          val COSMO_BOTOM_BIG = getRegion("cosmo_botom_big")
          val MENU_DEF        = getRegion("menu_def")
          val MENU_PRS        = getRegion("menu_prs")
          val NUMBERS         = getRegion("numbers")
          val PANEL           = getRegion("panel")
          val PAUSE_DEF       = getRegion("pause_def")
          val PAUSE_PRS       = getRegion("pause_prs")
          val PLATFORM        = getRegion("platform")
          val SALUTE          = getRegion("salute")
          val SHAR            = getRegion("shar")
          val STAR            = getRegion("star")
          val START           = getRegion("start")
          val TIMER           = getRegion("timer")


          val STAR_LIST = List(4) { getRegion("star-$it") }
          val S_LIST    = List(9) { getRegion("s${it.inc()}") }

          val COOL     = SpriteManager.EnumTexture.COOL.data.texture
          val GOOD     = SpriteManager.EnumTexture.GOOD.data.texture
          val LOST     = SpriteManager.EnumTexture.LOST.data.texture
          val MENU     = SpriteManager.EnumTexture.MENU.data.texture
          val NOTBAD   = SpriteManager.EnumTexture.NOTBAD.data.texture
          val RULES    = SpriteManager.EnumTexture.RULES.data.texture
          val SETTINGS = SpriteManager.EnumTexture.SETTINGS.data.texture
          val BACGROND_LEVEL = SpriteManager.EnumTexture.BACGROND_LEVEL.data.texture
          val RESULT_GOOD = SpriteManager.EnumTexture.RESULT_GOOD.data.texture
     }

}