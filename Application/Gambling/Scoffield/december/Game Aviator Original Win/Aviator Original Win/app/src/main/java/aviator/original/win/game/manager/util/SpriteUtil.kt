package aviator.original.win.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import aviator.original.win.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.LOADING.data.atlas.findRegion(name)

          val circul   = getRegion("circul")
          val progress = getRegion("progress")

          val TigerLoading  = SpriteManager.EnumTexture.OriginalLoading.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val hart        = getRegion("hart")
          val menu_def    = getRegion("menu_def")
          val menu_press  = getRegion("menu_press")
          val music_check = getRegion("music_check")
          val music_def   = getRegion("music_def")
          val pause_check = getRegion("pause_check")
          val pause_def   = getRegion("pause_def")
          val point       = getRegion("point")

          val aviaList  = List(5) { getRegion("avia${it.inc()}") }
          val coinList  = List(4) { getRegion("coin${it.inc()}") }
          val enemyList = List(3) { getRegion("enemy${it.inc()}") }

          val MainBackground = SpriteManager.EnumTexture.MainBackground.data.texture
          val Menu           = SpriteManager.EnumTexture.Menu.data.texture
          val OriginalGame   = SpriteManager.EnumTexture.OriginalGame.data.texture
          val OriginalLose   = SpriteManager.EnumTexture.OriginalLose.data.texture
          val OriginalWin    = SpriteManager.EnumTexture.OriginalWin.data.texture
          val Rules          = SpriteManager.EnumTexture.Rules.data.texture
          val Settings       = SpriteManager.EnumTexture.Settings.data.texture
          val shop           = SpriteManager.EnumTexture.shop.data.texture
     }

}