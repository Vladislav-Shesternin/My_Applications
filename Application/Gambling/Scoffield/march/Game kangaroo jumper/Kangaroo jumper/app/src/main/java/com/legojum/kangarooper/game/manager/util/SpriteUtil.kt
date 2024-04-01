package com.legojum.kangarooper.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.legojum.kangarooper.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val TOP = SpriteManager.EnumTexture.TOP.data.texture
          val BOT = SpriteManager.EnumTexture.BOT.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val control  = getRegion("control")
          val coup     = getRegion("coup")
          val enemy    = getRegion("enemy")
          val kangaroo = getRegion("kangaroo")
          val line     = getRegion("line")
          val panel    = getRegion("panel")
          val play     = getRegion("play")
     }

}