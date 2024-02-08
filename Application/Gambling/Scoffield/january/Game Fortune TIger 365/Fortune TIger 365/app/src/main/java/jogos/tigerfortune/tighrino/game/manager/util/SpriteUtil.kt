package jogos.tigerfortune.tighrino.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import jogos.tigerfortune.tighrino.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          val TLOAD  = SpriteManager.EnumTexture.TLOAD.data.texture
          val LOADER = SpriteManager.EnumTexture.LOADER.data.texture
          val MASK   = SpriteManager.EnumTexture.MASK.data.texture
     }

     class GameAssets {
          private fun getRegionGame(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)
          private fun getRegionItems(name: String): TextureRegion = SpriteManager.EnumAtlas.ITEMS.data.atlas.findRegion(name)

          val check       = getRegionGame("check")
          val cursor      = getRegionGame("cursor")
          val exit_def    = getRegionGame("exit_def")
          val exit_press  = getRegionGame("exit_press")
          val panel       = getRegionGame("panel")
          val menu_def    = getRegionGame("menu_def")
          val menu_press  = getRegionGame("menu_press")
          val mus_check   = getRegionGame("mus_check")
          val mus_def     = getRegionGame("mus_def")
          val pause_check = getRegionGame("pause_check")
          val pause_def   = getRegionGame("pause_def")
          val t1          = getRegionGame("t1")
          val t2          = getRegionGame("t2")

          val backgrounds = List(4) { getRegionGame("b${it.inc()}") }

          val bim = getRegionItems("bim")

          val items = List(5) { getRegionItems("${it.inc()}") }

          val B_FAILE    = SpriteManager.EnumTexture.B_FAILE.data.texture
          val B_MAIN     = SpriteManager.EnumTexture.B_MAIN.data.texture
          val B_WIN      = SpriteManager.EnumTexture.B_WIN.data.texture
          val F_CHOOSE   = SpriteManager.EnumTexture.F_CHOOSE.data.texture
          val F_RULES    = SpriteManager.EnumTexture.F_RULES.data.texture
          val F_SETTINGS = SpriteManager.EnumTexture.F_SETTINGS.data.texture
          val TIGER1     = SpriteManager.EnumTexture.TIGER1.data.texture
          val TIGER2     = SpriteManager.EnumTexture.TIGER2.data.texture
          val F_LEVEL    = SpriteManager.EnumTexture.F_LEVEL.data.texture
          val F_MENU     = SpriteManager.EnumTexture.F_MENU.data.texture

          val BG1 = SpriteManager.EnumTexture.BG1.data.texture
          val BG2 = SpriteManager.EnumTexture.BG2.data.texture
          val BG3 = SpriteManager.EnumTexture.BG3.data.texture
          val BG4 = SpriteManager.EnumTexture.BG4.data.texture
     }

}