package com.kongregate.mobile.burrit.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.kongregate.mobile.burrit.game.manager.SpriteManager

class SpriteUtil {

     class Lobster {
          val bacMini = SpriteManager.EnumTexture.bacMini.data.texture
     }

     class Alpha {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.kontrol.data.atlas.findRegion(name)

          val ba       = getReg("ba")
          val backl    = getReg("backl")
          val miniotfr = getReg("miniotfr")
          val msoff    = getReg("msoff")
          val mson     = getReg("mson")
          val ott      = getReg("ott")
          val pflfne   = getReg("pflfne")
          val player   = getReg("player")
          val psewdo   = getReg("psewdo")
          val sonon    = getReg("sonon")
          val sooff    = getReg("sooff")
          val prc      = getReg("prc")

          val items  = List(11) { getReg("${it.inc()}") }
          val listA  = List(3) { getReg("a${it.inc()}") }

          val cklekeis     = SpriteManager.EnumTexture.cklekeis.data.texture
          val plsttstarext = SpriteManager.EnumTexture.plsttstarext.data.texture
          val rluse        = SpriteManager.EnumTexture.rluse.data.texture

          val DupaloG = SpriteManager.EnumTexture.DupaloG.data.texture

     }

}