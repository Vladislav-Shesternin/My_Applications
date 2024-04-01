package com.winterria.jumpilo.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.winterria.jumpilo.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val WINTER = SpriteManager.EnumTexture.WINTER.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val jump  = getRegion("jump")
          val mount = getRegion("mount")
          val panel = getRegion("panel")
          val star  = getRegion("star")

          val snows = List(4) { getRegion("${it.inc()}") }

     }

}