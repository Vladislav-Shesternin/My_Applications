package com.ottplay.ottpl.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.ottplay.ottpl.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val background = SpriteManager.EnumTexture.background.data.texture
          val loading    = SpriteManager.EnumTexture.loading.data.texture
          val mask       = SpriteManager.EnumTexture.mask.data.texture
          val progress   = SpriteManager.EnumTexture.progress.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.All.data.atlas.findRegion(name)

          val heart = getRegion("heart")
          val palet = getRegion("palet")
          val panel = getRegion("panel")
          val shar  = getRegion("shar")

          val airList   = List(5) { getRegion("air${it.inc()}") }
          val moneyList = List(4) { getRegion("money${it.inc()}") }

          val lose     = SpriteManager.EnumTexture.lose.data.texture
          val menu     = SpriteManager.EnumTexture.menu.data.texture
          val rules    = SpriteManager.EnumTexture.rules.data.texture
          val settings = SpriteManager.EnumTexture.settings.data.texture
          val shop     = SpriteManager.EnumTexture.shop.data.texture
          val win      = SpriteManager.EnumTexture.win.data.texture
          val background = SpriteManager.EnumTexture.game_background.data.texture
     }

}