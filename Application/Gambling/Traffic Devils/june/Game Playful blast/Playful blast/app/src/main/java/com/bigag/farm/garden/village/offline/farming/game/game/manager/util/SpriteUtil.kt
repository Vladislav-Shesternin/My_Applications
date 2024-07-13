package com.bigag.farm.garden.village.offline.farming.game.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bigag.farm.garden.village.offline.farming.game.game.manager.SpriteManager

class SpriteUtil {

     class Sapunaro {
          val Splash = SpriteManager.EnumTexture.Splash.data.texture
     }

     class NikolKidman {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.SOLO.data.atlas.findRegion(name)

          val faf     = getReg("faf")
          val fif     = getReg("fif")
          val kek_def = getReg("kek_def")
          val kek_prs = getReg("kek_prs")
          val kolo    = getReg("kolo")
          val rude    = getReg("rude")

          val items  = List(11) { getReg("${it.inc()}") }

          val BURBAS = SpriteManager.EnumTexture.BURBAS.data.texture
          val RIOPLS = SpriteManager.EnumTexture.RIOPLS.data.texture
          val SAIOL  = SpriteManager.EnumTexture.SAIOL.data.texture

          val dali  = SpriteManager.EnumTexture.dali.data.texture

     }

}