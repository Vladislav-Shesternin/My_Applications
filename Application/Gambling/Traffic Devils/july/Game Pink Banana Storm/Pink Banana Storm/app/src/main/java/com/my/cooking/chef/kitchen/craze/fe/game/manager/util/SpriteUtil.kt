package com.my.cooking.chef.kitchen.craze.fe.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.my.cooking.chef.kitchen.craze.fe.game.manager.SpriteManager

class SpriteUtil {

     class yyhAAGS {
          private val b1 = SpriteManager.EnumTexture.b1.data.texture
          private val b2 = SpriteManager.EnumTexture.b2.data.texture
          private val b3 = SpriteManager.EnumTexture.b3.data.texture

          val listB = listOf(b1,b2,b3)
     }

     class AoplKA {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.ALOLO.data.atlas.findRegion(name)

          val bll1       = getReg("bll1")
          val bll2       = getReg("bll2")
          val cbb        = getReg("cbb")
          val cbd        = getReg("cbd")
          val deva       = getReg("deva")
          val fruitcandy = getReg("fruitcandy")
          val menu       = getReg("menu")
          val panalka    = getReg("panalka")
          val pla        = getReg("pla")
          val pltfrma    = getReg("pltfrma")
          val srdce      = getReg("srdce")

          val items  = List(14) { getReg("${it.inc()}") }

          val BUTORS            = SpriteManager.EnumTexture.BUTORS.data.texture
          val SETTINGESOPLUYTRE = SpriteManager.EnumTexture.SETTINGESOPLUYTRE.data.texture
          val TEXESTEREWSWE     = SpriteManager.EnumTexture.TEXESTEREWSWE.data.texture

          val diloger = SpriteManager.EnumTexture.diloger.data.texture

     }

}