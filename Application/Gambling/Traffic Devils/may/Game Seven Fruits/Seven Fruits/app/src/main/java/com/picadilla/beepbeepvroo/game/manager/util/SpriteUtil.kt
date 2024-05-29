package com.picadilla.beepbeepvroo.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.picadilla.beepbeepvroo.game.manager.SpriteManager

class SpriteUtil {

     class Dgop {
          val meduna = SpriteManager.EnumTexture.meduna.data.texture
     }

     class Elias {
          private fun getParmesto(name: String): TextureRegion = SpriteManager.EnumAtlas.REgidon.data.atlas.findRegion(name)

          val bix_dif        = getParmesto("bix_dif")
          val bix_press      = getParmesto("bix_press")
          val leften_def     = getParmesto("leften_def")
          val leften_press   = getParmesto("leften_press")
          val malchonka      = getParmesto("malchonka")
          val menu_def       = getParmesto("menu_def")
          val menu_press     = getParmesto("menu_press")
          val misika_soudika = getParmesto("misika_soudika")
          val prely          = getParmesto("prely")
          val recordiy       = getParmesto("recordiy")
          val rigite_def     = getParmesto("rigite_def")
          val rigite_press   = getParmesto("rigite_press")
          val rubeliy        = getParmesto("rubeliy")
          val stronsiy       = getParmesto("stronsiy")

          val irems = List(6) { getParmesto("${it.inc()}") }

          val musishion = SpriteManager.EnumTexture.texet.data.texture

     }

}