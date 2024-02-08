package com.fortunetiger.carnival.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.fortunetiger.carnival.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.LOADER.data.atlas.findRegion(name)

          val ts_backich  = getRegion("ts_backich")
          val ts_progress = getRegion("ts_progress")


          val CARNAVAL = SpriteManager.EnumTexture.CARNAVAL.data.texture
          val ts_msk   = SpriteManager.EnumTexture.ts_msk.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val boom = getRegion("boom")
          val hdf  = getRegion("hdf")
          val hprs = getRegion("hprs")
          val i3   = getRegion("i3")
          val i4   = getRegion("i4")
          val mdf  = getRegion("mdf")
          val mprs = getRegion("mprs")
          val pdf  = getRegion("pdf")
          val pprs = getRegion("pprs")
          val sdf  = getRegion("sdf")
          val sprs = getRegion("sprs")
          val pnl  = getRegion("pnl")

          val shars = List(4) { getRegion("${it.inc()}") }

          val FUu_BLA  = SpriteManager.EnumTexture.FUu_BLA.data.texture
          val GAMNAVAL = SpriteManager.EnumTexture.GAMNAVAL.data.texture
          val Mentino  = SpriteManager.EnumTexture.Mentino.data.texture
          val Music    = SpriteManager.EnumTexture.Music.data.texture
          val OGO_SKA  = SpriteManager.EnumTexture.OGO_SKA.data.texture
     }

}