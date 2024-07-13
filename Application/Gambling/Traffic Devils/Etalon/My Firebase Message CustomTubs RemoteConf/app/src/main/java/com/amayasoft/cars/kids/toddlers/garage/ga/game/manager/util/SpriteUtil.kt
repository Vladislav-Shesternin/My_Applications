package com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.amayasoft.cars.kids.toddlers.garage.ga.game.manager.SpriteManager

class SpriteUtil {

     class S3 {
          val Splash = SpriteManager.EnumTexture.SPLASH.data.texture
     }

     class GA {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.lud.data.atlas.findRegion(name)

          val bhj    = getReg("bhj")
          val bvg    = getReg("bvg")
          val fghf   = getReg("fghf")
          val pinrel = getReg("pinrel")
          val qwe    = getReg("qwe")
          val sdf    = getReg("sdf")
          val sdr    = getReg("sdr")

          val items  = List(5) { getReg("${it.inc()}") }

          val ERTTTYU = SpriteManager.EnumTexture.ERTTTYU.data.texture
          val OPLZCK  = SpriteManager.EnumTexture.OPLZCK.data.texture
          val XCBVVBN = SpriteManager.EnumTexture.XCBVVBN.data.texture

          val frodo = SpriteManager.EnumTexture.frodo.data.texture
     }

}