package com.bandagames.mpuzzle.g.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bandagames.mpuzzle.g.game.manager.SpriteManager

class SpriteUtil {

     class Frady {
          val magi = SpriteManager.EnumTexture.magi.data.texture
     }

     class Jahsgd {
          private fun getReg(name: String): TextureRegion = SpriteManager.EnumAtlas.ALLSTARS.data.atlas.findRegion(name)

          val bdef    = getReg("bdef")
          val bpref   = getReg("bpref")
          val pblu    = getReg("pblu")
          val porg    = getReg("porg")
          val prorg   = getReg("prorg")
          val prosbl  = getReg("prosbl")
          val strelki = getReg("strelki")

          val items  = List(7) { getReg("${it.inc()}") }
          val itemsO = List(4) { getReg("o${it.inc()}") }

          val pomaska    = SpriteManager.EnumTexture.pomaska.data.texture
          val saturetion = SpriteManager.EnumTexture.saturetion.data.texture
          val mesu       = SpriteManager.EnumTexture.mesu.data.texture
          val stall      = SpriteManager.EnumTexture.stall.data.texture
          val begi       = SpriteManager.EnumTexture.begi.data.texture
          val SKORO_CIA_HUETA_ZAKONCHITSA_I_BUDE_BOGATA_LIFE       = SpriteManager.EnumTexture.skoro_cia_hueta_zakonchitsa_i_bude_bogata_life.data.texture

     }

}