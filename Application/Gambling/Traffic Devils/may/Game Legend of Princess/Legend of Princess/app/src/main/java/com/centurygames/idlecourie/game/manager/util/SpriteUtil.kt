package com.centurygames.idlecourie.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.centurygames.idlecourie.game.manager.SpriteManager

class SpriteUtil {

     class Globus {
          val farmer = SpriteManager.EnumTexture.farmer.data.texture
     }

     class Valuha {
          private fun getVilliam(name: String): TextureRegion = SpriteManager.EnumAtlas.velodrom.data.atlas.findRegion(name)

          val asd           = getVilliam("asd")
          val cvbn          = getVilliam("cvbn")
          val dsaj          = getVilliam("dsaj")
          val eers          = getVilliam("eers")
          val fdr           = getVilliam("fdr")
          val nbh           = getVilliam("nbh")
          val piricessssa   = getVilliam("piricessssa")
          val play_def      = getVilliam("play_def")
          val play_pres     = getVilliam("play_pres")
          val popap         = getVilliam("popap")
          val qwqw          = getVilliam("qwqw")
          val qwsd          = getVilliam("qwsd")
          val sdax          = getVilliam("sdax")
          val settindhfhdfj = getVilliam("settindhfhdfj")
          val xcvxv         = getVilliam("xcvxv")
          val zxc           = getVilliam("zxc")
          val pitannie      = getVilliam("pitannie")

          val zamuhrushki = List(11) { getVilliam("${it.inc()}") }

          val musa    = SpriteManager.EnumTexture.musa.data.texture
          val onboard = SpriteManager.EnumTexture.onboard.data.texture
          val win     = SpriteManager.EnumTexture.win.data.texture

     }

}