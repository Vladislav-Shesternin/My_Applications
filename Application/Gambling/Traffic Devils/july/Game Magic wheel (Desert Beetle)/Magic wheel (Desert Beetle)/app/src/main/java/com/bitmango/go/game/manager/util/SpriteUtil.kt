package com.bitmango.go.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bitmango.go.game.manager.SpriteManager

class SpriteUtil {

     class AAA {
          private val g1 = SpriteManager.EnumTexture.g1.data.texture
          private val g2 = SpriteManager.EnumTexture.g2.data.texture
          private val g3 = SpriteManager.EnumTexture.g3.data.texture
          val back       = listOf(g1,g2,g3)
     }

     class BBB {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.leonardo.data.atlas.findRegion(name)

          val bavg   = getReg("bavg")
          val ffggs  = getReg("ffggs")
          val qwe3   = getReg("qwe3")
          val tghy   = getReg("tghy")
          val tty5   = getReg("tty5")
          val vcbxf  = getReg("vcbxf")
          val vcxzd  = getReg("vcxzd")
          val weq    = getReg("weq")
          val yytweh = getReg("yytweh")

          val items  = List(15) { getReg("${it.inc()}") }
          val itemsT = List(15) { getReg("t${it.inc()}") }

          val ewr    = SpriteManager.EnumTexture.ewr.data.texture
          val qsde   = SpriteManager.EnumTexture.qsde.data.texture
          val utytrg = SpriteManager.EnumTexture.utytrg.data.texture

          val Jorg = SpriteManager.EnumTexture.Jorg.data.texture

     }

}