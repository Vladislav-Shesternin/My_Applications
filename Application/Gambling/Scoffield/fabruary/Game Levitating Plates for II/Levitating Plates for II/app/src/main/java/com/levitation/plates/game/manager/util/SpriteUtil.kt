package com.levitation.plates.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.levitation.plates.game.manager.SpriteManager

class SpriteUtil {

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)
          private fun getRegionPLANETS(name: String): TextureRegion = SpriteManager.EnumAtlas.PLANETS.data.atlas.findRegion(name)

          val blue          = getRegion("blue")
          val blue_tarelka  = getRegion("blue_tarelka")
          val green         = getRegion("green")
          val green_tarelka = getRegion("green_tarelka")

          val planets = List(22) { getRegionPLANETS("${it.inc()}") }

          val MAIN = SpriteManager.EnumTexture.MAIN.data.texture
     }

}