package com.guardians.galaxyguano.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.guardians.galaxyguano.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val FONIK = SpriteManager.EnumTexture.FONIK.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val ball     = getRegion("ball")
          val left     = getRegion("left")
          val platform = getRegion("platform")
          val right    = getRegion("right")

          val asteroids = List(5) { getRegion("${it.inc()}") }
     }

}