package com.dogbytegames.offtheroa.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.dogbytegames.offtheroa.game.manager.SpriteManager

class SpriteUtil {

     class Lida {
          val LDR = SpriteManager.EnumTexture.LDR.data.texture
     }

     class Allamigos {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.babak.data.atlas.findRegion(name)

          val back_def     = getReg("back_def")
          val back_press   = getReg("back_press")
          val chika_check  = getReg("chika_check")
          val chika_def    = getReg("chika_def")
          val nubik        = getReg("nubik")
          val result_panel = getReg("result_panel")

          val items  = List(4) { getReg("${it.inc()}") }

          val musatina = SpriteManager.EnumTexture.musatina.data.texture
          val nubasina = SpriteManager.EnumTexture.nubasina.data.texture
          val vegasina = SpriteManager.EnumTexture.vegasina.data.texture
          val buttons  = SpriteManager.EnumTexture.buttons.data.texture
          val dupa  = SpriteManager.EnumTexture.dupa.data.texture

     }

}