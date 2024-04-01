package com.destroyer.buildings.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.destroyer.buildings.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val BUILDINGS_AREA = SpriteManager.EnumTexture.BUILDINGS_AREA.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val back    = getRegion("back")
          val chain   = getRegion("chain")
          val destroy = getRegion("destroy")
          val stone   = getRegion("stone")

          val builds = List(4) { getRegion("${it.inc()}") }
          val bb     = List(4) { getRegion("b${it.inc()}") }

          val MENU = SpriteManager.EnumTexture.MENU.data.texture

     }

}