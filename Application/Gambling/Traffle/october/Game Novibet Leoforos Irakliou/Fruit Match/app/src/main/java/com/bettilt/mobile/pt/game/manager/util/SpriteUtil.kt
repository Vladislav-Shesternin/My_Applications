package com.bettilt.mobile.pt.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.bettilt.mobile.pt.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
          val GIRL       = SpriteManager.EnumTexture.GIRL.data.texture
     }

     interface IAssets {
          val B_DEF             : TextureRegion
          val B_PRS             : TextureRegion
          val BOM               : TextureRegion
          val MENU_DEF          : TextureRegion
          val MENU_PRESS        : TextureRegion
          val PROGRESS_FRAME    : TextureRegion
          val PROGRESS_INDICATOR: TextureRegion
          val WIN               : TextureRegion
          val TOUCH             : TextureRegion
          val PANEL             : TextureRegion
          val GLUED             : TextureRegion

          val ITEM_LIST: List<TextureRegion>

          val BACKGROUND   : Texture
          val GIRL         : Texture
          val PROGRESS_MASK: Texture
     }

     class AllAssets: IAssets {
          private val splashAssets = SplashAssets()

          private fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val B_DEF              = getGameRegion("b_def")
          override val B_PRS              = getGameRegion("b_prs")
          override val BOM                = getGameRegion("bom")
          override val MENU_DEF           = getGameRegion("menu_def")
          override val MENU_PRESS         = getGameRegion("menu_press")
          override val PROGRESS_FRAME     = getGameRegion("progress_frame")
          override val PROGRESS_INDICATOR = getGameRegion("progress_indicator")
          override val WIN                = getGameRegion("win")
          override val TOUCH              = getGameRegion("touch")
          override val PANEL              = getGameRegion("panel")
          override val GLUED              = getGameRegion("glued")

          override val ITEM_LIST = List(6) { getGameRegion(it.inc().toString()) }

          override val BACKGROUND    = splashAssets.BACKGROUND
          override val GIRL          = splashAssets.GIRL
          override val PROGRESS_MASK = SpriteManager.EnumTexture.PROGRESS_MASK.data.texture

     }
}