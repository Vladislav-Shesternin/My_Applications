package plinko.gameballs.nine.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import plinko.gameballs.nine.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          val B_LIGHTNING      = SpriteManager.EnumTexture.B_LIGHTNING.data.texture
          val B_LOADING        = SpriteManager.EnumTexture.B_LOADING.data.texture
          val LOADER           = SpriteManager.EnumTexture.LOADER.data.texture
          val BALL_AND_LOADING = SpriteManager.EnumTexture.BALL_AND_LOADING.data.texture
     }

     class GameAssets {
          protected fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val CHECKED    = getRegion("checked")
          val DEFAULT    = getRegion("default")
          val SOUND      = getRegion("sound")
          val SOUND_NOT  = getRegion("sound_not")
          val TETRA      = getRegion("tetra")
          val BALL_PANEL = getRegion("ball_panel")


          val BALLS = List(6) { getRegion("ball ${it.inc()}") }

          val B_GAME      = SpriteManager.EnumTexture.B_GAME.data.texture
          val BUTTONS     = SpriteManager.EnumTexture.BUTTONS.data.texture
          val RULLES      = SpriteManager.EnumTexture.RULLES.data.texture
          val SETTINGS    = SpriteManager.EnumTexture.SETTINGS.data.texture
          val WHITE       = SpriteManager.EnumTexture.WHITE.data.texture
     }

}