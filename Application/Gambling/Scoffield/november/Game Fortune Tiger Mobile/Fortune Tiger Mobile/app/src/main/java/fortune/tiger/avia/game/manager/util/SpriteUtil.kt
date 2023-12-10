package fortune.tiger.avia.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import fortune.tiger.avia.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          protected fun getRegion(name: String = "pusk"): TextureRegion = SpriteManager.EnumAtlas.SPLASH.data.atlas.findRegion(name)

          val PUSK = getRegion()

          val SPLASHIMG = SpriteManager.EnumTexture.SPLASHIMG.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val DOMIK               = getRegion("domik")
          val NTXT                = getRegion("ntxt")
          val OOFF                = getRegion("ooff")
          val OONN                = getRegion("oonn")
          val PANEL_BIG           = getRegion("panel_big")
          val PANEL_MIN           = getRegion("panel_min")
          val PLAYE               = getRegion("playe")
          val PROGRESS            = getRegion("progress")
          val PROGRESS_BACKGROUND = getRegion("progress_background")
          val PUZAN               = getRegion("puzan")
          val STAR_ONE            = getRegion("star_one")
          val STAR_TRIPLE         = getRegion("star_triple")
          val STAR_TWO            = getRegion("star_two")
          val STAR_ZERO           = getRegion("star_zero")
          val PIPKA               = getRegion("pipka")
          val TRUSIK              = getRegion("trusik")
          val FALSIK              = getRegion("falsik")
          val RESTART             = getRegion("restart")

          val ITEM_LIST = List(6) { getRegion("i${it.inc()}") }

          val LEVELSIMG       = SpriteManager.EnumTexture.LEVELSIMG.data.texture
          val VICTORY         = SpriteManager.EnumTexture.VICTORY.data.texture
          val STEEPLY         = SpriteManager.EnumTexture.STEEPLY.data.texture
          val GOODJOB         = SpriteManager.EnumTexture.GOODJOB.data.texture
          val ULERUSIMG       = SpriteManager.EnumTexture.ULERUSIMG.data.texture
          val LOSE            = SpriteManager.EnumTexture.LOSE.data.texture
          val TESSINGSIMG     = SpriteManager.EnumTexture.TESSINGSIMG.data.texture
          val IGRABELNAGRAIMG = SpriteManager.EnumTexture.IGRABELNAGRAIMG.data.texture
          val PG_MASKA        = SpriteManager.EnumTexture.PG_MASKA.data.texture
          val MENUIMG         = SpriteManager.EnumTexture.MENUIMG.data.texture

     }

}