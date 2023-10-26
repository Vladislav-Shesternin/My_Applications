package investgroup.program.gaz.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import investgroup.program.gaz.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val znakiList : List<TextureRegion>

          val progressoList: List<TextureRegion>

          val Secret : Texture
          val Terbalino : Texture
          val Uzumaki : Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val znakiList  = List(9) { getGameRegion("${it.inc()}")  }
          override val progressoList = List(10) { getGameRegion("w${it.inc()}") }

          override val Secret = SpriteManager.EnumTexture.Secret .data.texture
          override val Terbalino = SpriteManager.EnumTexture.Terbalino .data.texture
          override val Uzumaki = SpriteManager.EnumTexture.Uzumaki.data.texture
     }
}