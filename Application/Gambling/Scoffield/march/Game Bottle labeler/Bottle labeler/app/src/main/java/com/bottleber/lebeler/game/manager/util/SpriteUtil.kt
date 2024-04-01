package com.bottleber.lebeler.game.manager.util

import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bottleber.lebeler.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val fonit = SpriteManager.EnumTexture.fonit.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)
          private fun getPath(name: String): NinePatch = SpriteManager.EnumAtlas.ALL.data.atlas.createPatch(name)

          val precel    = getRegion("precel")
          val shot_menu = getRegion("shot_menu")
          val arrow     = getRegion("arrow")

          val separator  = getPath("separator")

          val bottles = List(32) { getRegion("${it.inc()}") }

          val miui = SpriteManager.EnumTexture.miui.data.texture
     }

}