package com.gentle.spring.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.gentle.spring.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val hart  = getRegion("hart")
          val harts = List(11) { getRegion("${it.inc()}") }
     }

}