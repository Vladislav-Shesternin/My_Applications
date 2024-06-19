package com.hepagame.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.hepagame.game.manager.SpriteManager

class SpriteUtil {

     class Loading {
          val PAGRAN = SpriteManager.EnumTexture.PAGRAN.data.texture
     }

     class All {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.ASSETS.data.atlas.findRegion(name)

          val bik      = getReg("bik")
          val pardon   = getReg("pardon")
          val pirogovo = getReg("pirogovo")

          val items = List(6) { getReg("${it.inc()}") }

          val DD         = SpriteManager.EnumTexture.DD.data.texture
          val LAPATE     = SpriteManager.EnumTexture.LAPATE.data.texture
          val LILDONEZIA = SpriteManager.EnumTexture.LILDONEZIA.data.texture
          val MURLO      = SpriteManager.EnumTexture.MURLO.data.texture
          val PKD        = SpriteManager.EnumTexture.PKD.data.texture
          val SS         = SpriteManager.EnumTexture.SS.data.texture
          val VSTAVEN    = SpriteManager.EnumTexture.VSTAVEN.data.texture

          val dianis = SpriteManager.EnumTexture.dianis.data.texture

     }

}