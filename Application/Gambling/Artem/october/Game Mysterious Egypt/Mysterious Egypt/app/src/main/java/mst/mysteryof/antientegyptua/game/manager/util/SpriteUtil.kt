package mst.mysteryof.antientegyptua.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import mst.mysteryof.antientegyptua.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
     }

     interface AllAssets {
          val COUNTER : TextureRegion
          val EX_DEF  : TextureRegion
          val EX_PRESS: TextureRegion
          val MN_DEF  : TextureRegion
          val MN_PRESS: TextureRegion
          val PANEL   : TextureRegion
          val PL_DEF  : TextureRegion
          val PL_PRESS: TextureRegion
          val SN_DEF  : TextureRegion
          val SN_PRESS: TextureRegion
          val GL      : TextureRegion

          val items: List<TextureRegion>

          val MASK: Texture

     }

     class CommonAssets: AllAssets {
          private fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val COUNTER  = getGameRegion("counter")
          override val EX_DEF   = getGameRegion("ex_def")
          override val EX_PRESS = getGameRegion("ex_press")
          override val MN_DEF   = getGameRegion("mn_def")
          override val MN_PRESS = getGameRegion("mn_press")
          override val PANEL    = getGameRegion("panel")
          override val PL_DEF   = getGameRegion("pl_def")
          override val PL_PRESS = getGameRegion("pl_press")
          override val SN_DEF   = getGameRegion("sn_def")
          override val SN_PRESS = getGameRegion("sn_press")
          override val GL       = getGameRegion("gl")

          override val items = List(14) { getGameRegion("${it.inc()}") }

          override val MASK = SpriteManager.EnumTexture.MASK.data.texture

     }

}