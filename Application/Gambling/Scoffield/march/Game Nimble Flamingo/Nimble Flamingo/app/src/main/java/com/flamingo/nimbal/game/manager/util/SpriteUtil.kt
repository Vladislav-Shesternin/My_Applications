package com.flamingo.nimbal.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.flamingo.nimbal.game.manager.SpriteManager

class SpriteUtil {

     class StartAssets {
          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val balka = getRegion("balka")

          val birds  = List(6) { getRegion("${it.inc()}") }
     }

}