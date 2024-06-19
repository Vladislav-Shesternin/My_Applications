package com.doradogames.confli.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.doradogames.confli.game.manager.SpriteManager

class SpriteUtil {

     class Load {
          val Loading = SpriteManager.EnumTexture.Loading.data.texture
     }

     class Assets {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.ASSETS.data.atlas.findRegion(name)

          val b_d      = getReg("b_d")
          val b_p      = getReg("b_p")
          val pogresso = getReg("pogresso")
          val record   = getReg("record")

          val items = List(10) { getReg("${it.inc()}") }

          val menu  = SpriteManager.EnumTexture.menu.data.texture
          val ms    = SpriteManager.EnumTexture.ms.data.texture
          val msk   = SpriteManager.EnumTexture.msk.data.texture
          val rules = SpriteManager.EnumTexture.rules.data.texture

     }

}