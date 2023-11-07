package fludilia.mamkinarow.lotrends.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import fludilia.mamkinarow.lotrends.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val izba      : TextureRegion
          val monet      : TextureRegion

          val them: List<TextureRegion>

          val Banner: Texture
          val Dobavit   : Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val izba        = getGameRegion("izba")
          override val monet        = getGameRegion("monet")

          override val Banner       = SpriteManager.EnumTexture.Banner.data.texture
          override val Dobavit       = SpriteManager.EnumTexture.Dobavit.data.texture

          override val them = List(6) { getGameRegion(it.inc().toString()) }
     }
}