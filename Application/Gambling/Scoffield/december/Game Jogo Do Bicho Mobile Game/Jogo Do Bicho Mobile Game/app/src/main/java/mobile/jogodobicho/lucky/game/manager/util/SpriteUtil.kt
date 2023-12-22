package mobile.jogodobicho.lucky.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import mobile.jogodobicho.lucky.game.manager.SpriteManager

class SpriteUtil {

     class LoadingAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.LOADING.data.atlas.findRegion(name)

          val bull             = getRegion("bull")
          val liading_liading  = getRegion("liading_liading")
          val loading_babochke = getRegion("loading_babochke")
          val loadingPlay      = getRegion("loadingPlay")
          val progigress       = getRegion("progigress")

          val PROGIGRESS_MASKARADJA  = SpriteManager.EnumTexture.PROGIGRESS_MASKARADJA.data.texture
          val SUPER_PUPER_BACKGROUND = SpriteManager.EnumTexture.SUPER_PUPER_BACKGROUND.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val menu        = getRegion("menu")
          val menu_press  = getRegion("menu_press")
          val olaf        = getRegion("olaf")
          val pause       = getRegion("pause")
          val pause_yes   = getRegion("pause_yes")
          val sound       = getRegion("sound")
          val sound_not   = getRegion("sound_not")
          val trim_a      = getRegion("trim_a")
          val trim_back   = getRegion("trim_back")
          val trim_prog   = getRegion("trim_prog")
          val volumerka   = getRegion("volumerka")
          val zelene_pole = getRegion("zelene_pole")

          val BULL_LEVEL       = SpriteManager.EnumTexture.BULL_LEVEL.data.texture
          val BULL_MENU        = SpriteManager.EnumTexture.BULL_MENU.data.texture
          val LEVELFAILED      = SpriteManager.EnumTexture.LEVELFAILED.data.texture
          val LEVELPASSED      = SpriteManager.EnumTexture.LEVELPASSED.data.texture
          val MENU_BABOCHKE    = SpriteManager.EnumTexture.MENU_BABOCHKE.data.texture
          val OPTIONALE        = SpriteManager.EnumTexture.OPTIONALE.data.texture
          val TRIM_MASK        = SpriteManager.EnumTexture.TRIM_MASK.data.texture
          val VOLUMERKA_MASAKA = SpriteManager.EnumTexture.VOLUMERKA_MASAKA.data.texture
          val PALANKA          = SpriteManager.EnumTexture.PALANKA.data.texture

          val puzzle_easy_1   = SpriteManager.EnumTexture.puzzle_easy_1.data.texture
          val puzzle_easy_2   = SpriteManager.EnumTexture.puzzle_easy_2.data.texture
          val puzzle_easy_3   = SpriteManager.EnumTexture.puzzle_easy_3.data.texture
          val puzzle_medium_1 = SpriteManager.EnumTexture.puzzle_medium_1.data.texture
          val puzzle_medium_2 = SpriteManager.EnumTexture.puzzle_medium_2.data.texture
          val puzzle_medium_3 = SpriteManager.EnumTexture.puzzle_medium_3.data.texture
          val puzzle_hard_1   = SpriteManager.EnumTexture.puzzle_hard_1.data.texture
          val puzzle_hard_2   = SpriteManager.EnumTexture.puzzle_hard_2.data.texture
          val puzzle_hard_3   = SpriteManager.EnumTexture.puzzle_hard_3.data.texture

          val puziles = listOf(
               puzzle_easy_1,
               puzzle_easy_2,
               puzzle_easy_3,
               puzzle_medium_1,
               puzzle_medium_2,
               puzzle_medium_3,
               puzzle_hard_1,
               puzzle_hard_2,
               puzzle_hard_3,
          )
     }

}