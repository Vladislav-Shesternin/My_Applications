package com.minimal.tennis.fortwo.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.minimal.tennis.fortwo.game.manager.SpriteManager

class SpriteUtil {

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val a_user = getRegion("a_user")
          val b_user = getRegion("b_user")
          val ball   = getRegion("ball")
          val a_hand = getRegion("a_hand")
          val b_hand = getRegion("b_hand")
          val portal_a = getRegion("portal_a")
          val portal_b = getRegion("portal_b")

          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
     }

}