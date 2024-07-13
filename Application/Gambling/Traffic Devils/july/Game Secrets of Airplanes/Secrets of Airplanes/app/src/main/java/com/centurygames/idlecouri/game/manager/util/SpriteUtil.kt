package com.centurygames.idlecouri.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.centurygames.idlecouri.game.manager.SpriteManager

class SpriteUtil {

     class Lobster {
          private val fofA = SpriteManager.EnumTexture.fofA.data.texture
          private val fofB = SpriteManager.EnumTexture.fofB.data.texture

          val lister = listOf(fofA, fofB)
     }

     class Alpha {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.plane.data.atlas.findRegion(name)

          val bbb = getReg("bbb")
          val ddd = getReg("ddd")
          val off = getReg("off")
          val on  = getReg("on")

          val items  = List(10) { getReg("${it.inc()}") }
          val listP  = List(4) { getReg("p${it.inc()}") }

          val aaa  = SpriteManager.EnumTexture.aaa.data.texture
          val eeee = SpriteManager.EnumTexture.eeee.data.texture
          val ghg  = SpriteManager.EnumTexture.ghg.data.texture
          val kkk  = SpriteManager.EnumTexture.kkk.data.texture
          val vvv  = SpriteManager.EnumTexture.vvv.data.texture

          val Welcome = SpriteManager.EnumTexture.Welcome.data.texture
          val text    = SpriteManager.EnumTexture.text.data.texture

     }

}