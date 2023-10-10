package finalizer.masturbaizer.lotos.game.manager.util

import com.badlogic.gdx.graphics.Texture
import finalizer.masturbaizer.lotos.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {

          val barabanovka: Texture
          val f_bacgro: Texture
          val rezult_backig: Texture
          val www: Texture
          val qqq: Texture
     }

     class CommonAssets: AllAssets {
          override val barabanovka = SpriteManager.EnumTexture.barabanovka.data.texture
          override val f_bacgro = SpriteManager.EnumTexture.f_bacgro.data.texture
          override val rezult_backig = SpriteManager.EnumTexture.rezult_backig.data.texture
          override val www = SpriteManager.EnumTexture.www.data.texture
          override val qqq = SpriteManager.EnumTexture.qqq.data.texture

     }
}