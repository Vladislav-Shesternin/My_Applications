package com.fotune.tiger.slotthighrino.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.fotune.tiger.slotthighrino.game.manager.SpriteManager
import com.fotune.tiger.slotthighrino.game.utils.region

class SpriteUtil {

     class LoaderAssets {
//          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.LOADER.data.atlas.findRegion(name)

          val main = SpriteManager.EnumTexture.main.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val check  = getRegion("check")
          val def    = getRegion("def")
          val glow   = getRegion("glow")
          val tigers = getRegion("tigers")

          val listTiger = List(9) { getRegion("${it.inc()}") }

          val slotG = SpriteManager.EnumTexture.slotG.data.texture
          val numbers = SpriteManager.EnumTexture.numbers.data.texture

     }

}