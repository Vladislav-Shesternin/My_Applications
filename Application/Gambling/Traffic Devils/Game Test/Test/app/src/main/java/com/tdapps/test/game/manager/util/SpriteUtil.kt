package com.tdapps.test.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tdapps.test.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
     }

     interface AllAssets {
          val BALACA     : TextureRegion
          val EX_DEF     : TextureRegion
          val EX_PRS     : TextureRegion
          val MINUAS_DEF : TextureRegion
          val MINUAS_PERS: TextureRegion
          val PL_DEF     : TextureRegion
          val PL_PRS     : TextureRegion
          val PULSEK_DEF : TextureRegion
          val PULSEK_PRS : TextureRegion
          val SLOGROUP   : TextureRegion
          val SP_DEF     : TextureRegion
          val SP_WAIT    : TextureRegion
          val WIN        : TextureRegion

          val items: List<TextureRegion>

          val MASK: Texture

     }

     class CommonAssets: AllAssets {
          private fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val BALACA      = getGameRegion("balaca")
          override val EX_DEF      = getGameRegion("ex_def")
          override val EX_PRS      = getGameRegion("ex_prs")
          override val MINUAS_DEF  = getGameRegion("minuas_def")
          override val MINUAS_PERS = getGameRegion("minuas_pers")
          override val PL_DEF      = getGameRegion("pl_def")
          override val PL_PRS      = getGameRegion("pl_prs")
          override val PULSEK_DEF  = getGameRegion("pulsek_def")
          override val PULSEK_PRS  = getGameRegion("pulsek_prs")
          override val SLOGROUP    = getGameRegion("slogroup")
          override val SP_DEF      = getGameRegion("sp_def")
          override val SP_WAIT     = getGameRegion("sp_wait")
          override val WIN         = getGameRegion("win")

          override val items = List(3) { getGameRegion("${it.inc()}") }

          override val MASK = SpriteManager.EnumTexture.SLOMASK.data.texture

     }

}