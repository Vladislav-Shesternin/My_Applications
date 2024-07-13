package com.YovoGames.magicBo.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.YovoGames.magicBo.game.manager.SpriteManager

class SpriteUtil {

     class GSfdsghdja {
          val Soloha  = SpriteManager.EnumTexture.Soloha.data.texture
     }

     class JAshjms {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.TUMBA.data.atlas.findRegion(name)

          val bablo = getReg("bablo")
          val brp   = getReg("brp")
          val off   = getReg("off")
          val on    = getReg("on")
          val pkt   = getReg("pkt")

          val items  = List(5) { getReg("${it.inc()}") }

          val poreste = SpriteManager.EnumTexture.poreste.data.texture
          val rlese   = SpriteManager.EnumTexture.rlese.data.texture
          val somuska = SpriteManager.EnumTexture.somuska.data.texture

          val rom = SpriteManager.EnumTexture.rom.data.texture

     }

}