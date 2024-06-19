package com.duckduckmoosedesign.cpkid.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.duckduckmoosedesign.cpkid.game.manager.SpriteManager

class SpriteUtil {

     class Loading {
          val BEDROOM = SpriteManager.EnumTexture.BEDROOM.data.texture
     }

     class All {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.ASSETS.data.atlas.findRegion(name)

          val babrun       = getReg("babrun")
          val esmiraldo    = getReg("esmiraldo")
          val exibit_def   = getReg("exibit_def")
          val exibit_press = getReg("exibit_press")
          val hibiskus     = getReg("hibiskus")
          val serun        = getReg("serun")
          val pogless      = getReg("pogless")

          val items = List(5) { getReg("${it.inc()}") }

          val kilotonna    = SpriteManager.EnumTexture.kilotonna.data.texture
          val left         = SpriteManager.EnumTexture.left.data.texture
          val miskappppppp = SpriteManager.EnumTexture.miskappppppp.data.texture
          val paramon      = SpriteManager.EnumTexture.paramon.data.texture
          val right        = SpriteManager.EnumTexture.right.data.texture
          val rudles       = SpriteManager.EnumTexture.rudles.data.texture
          val soundersia   = SpriteManager.EnumTexture.soundersia.data.texture

     }

}