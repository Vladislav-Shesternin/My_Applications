package com.mvgamesteam.mineta.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mvgamesteam.mineta.game.manager.SpriteManager

class SpriteUtil {

     class Sap {
          val Splash = SpriteManager.EnumTexture.Splash.data.texture
     }

     class Jer {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.GERMAN.data.atlas.findRegion(name)

          val bd       = getReg("bd")
          val bp       = getReg("bp")
          val ed       = getReg("ed")
          val ep       = getReg("ep")
          val lpo      = getReg("lpo")
          val progress = getReg("progress")
          val titr     = getReg("titr")

          val items  = List(6) { getReg("${it.inc()}") }

          val CLRFL = SpriteManager.EnumTexture.CLRFL.data.texture
          val MS    = SpriteManager.EnumTexture.MS.data.texture
          val MSK   = SpriteManager.EnumTexture.MSK.data.texture
          val MSP   = SpriteManager.EnumTexture.MSP.data.texture
          val PSR   = SpriteManager.EnumTexture.PSR.data.texture

          val diasaga   = SpriteManager.EnumTexture.diasaga.data.texture
     }

}