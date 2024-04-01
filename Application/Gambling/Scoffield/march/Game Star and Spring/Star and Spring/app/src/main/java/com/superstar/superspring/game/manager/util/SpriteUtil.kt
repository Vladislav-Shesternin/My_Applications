package com.superstar.superspring.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.superstar.superspring.game.manager.SpriteManager

class SpriteUtil {

     class LoaderAssets {
          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
     }

     class AllAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.ALL.data.atlas.findRegion(name)

          val spring = getRegion("spring")
          val star   = getRegion("star")

          val GAME_B = SpriteManager.EnumTexture.GAME_B.data.texture
          val MENU_B = SpriteManager.EnumTexture.MENU_B.data.texture
     }

}