package uniwersal.pictures.present.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import uniwersal.pictures.present.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {

          val teterkaList: List<TextureRegion>
          val wertyuiList: List<TextureRegion>

          val ALL: TextureRegion
          val DD: TextureRegion
          val HH: TextureRegion
          val MM: TextureRegion
          val WW: TextureRegion
          val YY: TextureRegion


          val Pordoje: Texture
          val Prajke  : Texture
          val Pulshje   : Texture
          val Sevakje   : Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGaGaRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GOOGLE.data.atlas.findRegion(name)

          override val teterkaList = List(8) { getGaGaRegion(it.inc().toString()) }
          override val wertyuiList = List(4) { getGaGaRegion("rr" + it.inc().toString()) }

          override val ALL = getGaGaRegion("ALL")
          override val DD = getGaGaRegion("DD")
          override val HH = getGaGaRegion("HH")
          override val MM = getGaGaRegion("MM")
          override val WW = getGaGaRegion("WW")
          override val YY = getGaGaRegion("YY")

          override val Pordoje  = SpriteManager.EnumTexture.Pordoje.data.texture
          override val Prajke  = SpriteManager.EnumTexture.Prajke.data.texture
          override val Pulshje  = SpriteManager.EnumTexture.Pulshje.data.texture
          override val Sevakje  = SpriteManager.EnumTexture.Sevakje.data.texture
     }
}