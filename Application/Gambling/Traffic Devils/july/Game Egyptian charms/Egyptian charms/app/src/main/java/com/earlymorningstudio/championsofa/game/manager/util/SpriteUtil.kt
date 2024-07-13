package com.earlymorningstudio.championsofa.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.earlymorningstudio.championsofa.game.manager.SpriteManager

class SpriteUtil {

     class sreqe {
          val PUNDIC = SpriteManager.EnumTexture.pundic.data.texture
     }

     class lichinkos {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.gulivet.data.atlas.findRegion(name)

          val asd11  = getReg("asd11")
          val bedty  = getReg("bedty")
          val erad   = getReg("erad")
          val ioy    = getReg("ioy")
          val iuil   = getReg("iuil")
          val trytry = getReg("trytry")

          val items  = List(9) { getReg("${it.inc()}") }

          val pzqfgm = SpriteManager.EnumTexture.pzqfgm.data.texture
          val rtfs   = SpriteManager.EnumTexture.rtfs.data.texture
          val uubm   = SpriteManager.EnumTexture.uubm.data.texture
          val asdddd   = SpriteManager.EnumTexture.asdddd.data.texture
          val fortepiano   = SpriteManager.EnumTexture.fortepiano.data.texture

     }

}