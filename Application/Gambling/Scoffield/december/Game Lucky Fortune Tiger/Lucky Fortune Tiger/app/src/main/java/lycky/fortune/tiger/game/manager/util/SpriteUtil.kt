package lycky.fortune.tiger.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import lycky.fortune.tiger.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.SPLASH.data.atlas.findRegion(name)

          val tiger_loader    = getRegion("tiger_loader")
          val tiger_progrress = getRegion("tiger_progrress")

          val TIGER_MASK           = SpriteManager.EnumTexture.TIGER_MASK.data.texture
          val VARIOUS_LUXURY_ITEMS = SpriteManager.EnumTexture.VARIOUS_LUXURY_ITEMS.data.texture
          val FORTUNE_TIGER        = SpriteManager.EnumTexture.FORTUNE_TIGER.data.texture
          val FIRST_BACKGROUND     = SpriteManager.EnumTexture.FIRST_BACKGROUND.data.texture
     }

     class GameAssets {
          private fun getRegionGame(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)
          private fun getRegionToy(name: String): TextureRegion = SpriteManager.EnumAtlas.TOY.data.atlas.findRegion(name)

          val toys = List(5) { getRegionToy("toy_${it.inc()}") }

          val grip            = getRegionGame("grip")
          val rectangle       = getRegionGame("rectangle")
          val rectangle_check = getRegionGame("rectangle_check")
          val tiger_menu_def  = getRegionGame("tiger_menu_def")
          val tiger_menu_dis  = getRegionGame("tiger_menu_dis")
          val tiger_pause_def = getRegionGame("tiger_pause_def")
          val tiger_pause_dis = getRegionGame("tiger_pause_dis")
          val tiger_sound     = getRegionGame("tiger_sound")
          val tiger_sound_not = getRegionGame("tiger_sound_not")
          val tiger_time      = getRegionGame("tiger_time")
          val time_clock      = getRegionGame("time_clock")
          val time_progress   = getRegionGame("time_progress")
          val volume_progress = getRegionGame("volume_progress")

          val BAD              = SpriteManager.EnumTexture.BAD.data.texture
          val GREEAT           = SpriteManager.EnumTexture.GREEAT.data.texture
          val RUL              = SpriteManager.EnumTexture.RUL.data.texture
          val SET              = SpriteManager.EnumTexture.SET.data.texture
          val TIGER_MENU       = SpriteManager.EnumTexture.TIGER_MENU.data.texture
          val TIME_MASK        = SpriteManager.EnumTexture.TIME_MASK.data.texture
          val TOY_PANEL        = SpriteManager.EnumTexture.TOY_PANEL.data.texture
     }

}