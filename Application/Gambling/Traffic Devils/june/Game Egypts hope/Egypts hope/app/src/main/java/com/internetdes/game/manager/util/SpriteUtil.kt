package com.internetdes.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.internetdes.game.manager.SpriteManager

class SpriteUtil {

     class Gera {
          val BAGRATION = SpriteManager.EnumTexture.BAGRATION.data.texture
     }

     class Ioma {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.UMBA.data.atlas.findRegion(name)

          val berf  = getReg("berf")
          val gdef  = getReg("gdef")
          val gpers = getReg("gpers")
          val porso = getReg("porso")

          val items  = List(10) { getReg("${it.inc()}") }

          val ARSIA = SpriteManager.EnumTexture.ARSIA.data.texture
          val BLEF  = SpriteManager.EnumTexture.BLEF.data.texture
          val COLP  = SpriteManager.EnumTexture.COLP.data.texture
          val MENO  = SpriteManager.EnumTexture.MENO.data.texture
          val asg  = SpriteManager.EnumTexture.asg.data.texture
          val doilka  = SpriteManager.EnumTexture.doilka.data.texture

     }

}