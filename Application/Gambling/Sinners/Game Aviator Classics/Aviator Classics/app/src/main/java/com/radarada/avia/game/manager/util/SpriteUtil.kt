package com.radarada.avia.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.radarada.avia.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          val loadingB  = SpriteManager.EnumTexture.loadingB.data.texture
          val masks     = SpriteManager.EnumTexture.masks.data.texture
          val load      = SpriteManager.EnumTexture.load.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val ballon   = getRegion("ballon")
          val heart    = getRegion("heart")
          val md       = getRegion("md")
          val mp       = getRegion("mp")
          val pan_coin = getRegion("pan_coin")
          val pan_time = getRegion("pan_time")
          val sub      = getRegion("sub")

          val aviaList  = List(5) { getRegion("${it.inc()}") }
          val moneyList = List(4) { getRegion("money${it.inc()}") }

          val Fail   = SpriteManager.EnumTexture.Fail.data.texture
          val mainB  = SpriteManager.EnumTexture.mainB.data.texture
          val menuPA = SpriteManager.EnumTexture.menuPA.data.texture
          val rils   = SpriteManager.EnumTexture.rils.data.texture
          val setPa  = SpriteManager.EnumTexture.setPa.data.texture
          val sipos  = SpriteManager.EnumTexture.sipos.data.texture
          val win    = SpriteManager.EnumTexture.win.data.texture
     }

}