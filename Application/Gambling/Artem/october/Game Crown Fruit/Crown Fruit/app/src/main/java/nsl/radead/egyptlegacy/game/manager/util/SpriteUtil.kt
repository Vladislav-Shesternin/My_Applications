package nsl.radead.egyptlegacy.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import nsl.radead.egyptlegacy.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val BTN_DEF : TextureRegion
          val BTN_PRS : TextureRegion
          val GO      : TextureRegion
          val GO_PRESS: TextureRegion
          val PANEL   : TextureRegion
          val MENU_DEF: TextureRegion
          val MENU_PRS: TextureRegion
          val WIN     : TextureRegion

          val BACKGROUND : Texture

          val items: List<TextureRegion>
     }

     class CommonAssets: AllAssets {
          private fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val BTN_DEF  = getGameRegion("btn_def")
          override val BTN_PRS  = getGameRegion("btn_prs")
          override val GO       = getGameRegion("go")
          override val GO_PRESS = getGameRegion("go_press")
          override val PANEL    = getGameRegion("panel")
          override val MENU_DEF = getGameRegion("menu_def")
          override val MENU_PRS = getGameRegion("menu_prs")
          override val WIN      = getGameRegion("win")

          override val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture

          override val items = List(14) { getGameRegion("${it.inc()}") }
     }

}