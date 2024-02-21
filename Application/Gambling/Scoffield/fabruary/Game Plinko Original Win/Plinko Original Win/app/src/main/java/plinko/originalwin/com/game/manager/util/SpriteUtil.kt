package plinko.originalwin.com.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import plinko.originalwin.com.game.manager.SpriteManager

class SpriteUtil {

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val button_exit      = getRegion("button_exit")
          val button_sound_off = getRegion("button_sound_off")
          val button_sound_on  = getRegion("button_sound_on")
          val state_a          = getRegion("state_a")
          val state_b          = getRegion("state_b")
          val wins             = getRegion("wins")
          val circle           = getRegion("circle")
          val arrows           = getRegion("arrows")
          val coin             = getRegion("coin")
          val green_coin       = getRegion("green_coin")


          val GAME_OVER    = SpriteManager.EnumTexture.GAME_OVER.data.texture
          val GAME_PANEL   = SpriteManager.EnumTexture.GAME_PANEL.data.texture
          val SUPER_PLINKO = SpriteManager.EnumTexture.SUPER_PLINKO.data.texture
     }

}