package com.fortunetiger.bigwin.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.fortunetiger.bigwin.game.manager.SpriteManager
import com.fortunetiger.bigwin.game.utils.region

class SpriteUtil {

     class LoaderAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.LOADER.data.atlas.findRegion(name)

          val bahanovski     = getRegion("bahanovski")
          val progressoluter = getRegion("progressoluter")

          val FTWBackground = SpriteManager.EnumTexture.FTWBackground.data.texture
          val loa_svet      = SpriteManager.EnumTexture.loa_svet.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val bid         = getRegion("bid")
          val credit      = getRegion("credit")
          val FTW         = getRegion("FTW")
          val golowa      = getRegion("golowa")
          val menu_def    = getRegion("menu_def")
          val menu_press  = getRegion("menu_press")
          val minus_def   = getRegion("minus_def")
          val minus_press = getRegion("minus_press")
          val plus_def    = getRegion("plus_def")
          val plus_press  = getRegion("plus_press")
          val romb        = getRegion("romb")
          val spin_def    = getRegion("spin_def")
          val spin_weit   = getRegion("spin_weit")
          val valuer      = getRegion("valuer")

          val listItems = List(10) { getRegion("${it.inc()}") }

          val menu       = SpriteManager.EnumTexture.menu.data.texture
          val rules      = SpriteManager.EnumTexture.rules.data.texture
          val settings   = SpriteManager.EnumTexture.settings.data.texture
          val slot_group = SpriteManager.EnumTexture.slot_group.data.texture
     }

}