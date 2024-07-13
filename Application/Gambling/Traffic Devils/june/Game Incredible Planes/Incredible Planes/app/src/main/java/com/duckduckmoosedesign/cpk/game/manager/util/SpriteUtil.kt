package com.duckduckmoosedesign.cpk.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.duckduckmoosedesign.cpk.game.manager.SpriteManager

class SpriteUtil {

     class Gelexy {
          val Firster = SpriteManager.EnumTexture.Firster.data.texture
     }

     class Sarafanna {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.BERMUDKA.data.atlas.findRegion(name)

          val bkd    = getReg("bkd")
          val bkp    = getReg("bkp")
          val etd    = getReg("etd")
          val etp    = getReg("etp")
          val pgs    = getReg("pgs")
          val result = getReg("result")

          val planes      = List(6) { getReg("${it.inc()}") }
          val planesEnemy = List(8) { getReg("enemy${it.inc()}") }

          val MNL    = SpriteManager.EnumTexture.MNL.data.texture
          val MNS    = SpriteManager.EnumTexture.MNS.data.texture
          val MSKLAJ = SpriteManager.EnumTexture.MSKLAJ.data.texture
          val PNS    = SpriteManager.EnumTexture.PNS.data.texture
          val SMS    = SpriteManager.EnumTexture.SMS.data.texture

          val Ember    = SpriteManager.EnumTexture.Ember.data.texture

     }

}