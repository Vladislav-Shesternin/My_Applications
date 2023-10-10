package hekeur.buzzniess.megaglorr.game.manager.util

import com.badlogic.gdx.graphics.Texture
import hekeur.buzzniess.megaglorr.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {

          val fistingbackground: Texture
          val frramek: Texture
          val gaming: Texture
     }

     class CommonAssets: AllAssets {
          override val fistingbackground = SpriteManager.EnumTexture.fistingbackground.data.texture
          override val frramek = SpriteManager.EnumTexture.frramek.data.texture
          override val gaming = SpriteManager.EnumTexture.gaming.data.texture

     }
}