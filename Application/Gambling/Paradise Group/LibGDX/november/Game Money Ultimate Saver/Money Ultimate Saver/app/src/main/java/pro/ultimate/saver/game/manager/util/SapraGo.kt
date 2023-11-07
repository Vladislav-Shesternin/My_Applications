package pro.ultimate.saver.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import pro.ultimate.saver.game.manager.SpriteManagerUUU

class SapraGo {

     interface Aska {
          val calendula : TextureRegion
          val grenki    : TextureRegion
          val postuplen : TextureRegion
          val ramor     : TextureRegion
          val rshodiki  : TextureRegion

          val shtukiList : List<TextureRegion>

          val Mesac       : Texture
          val Postuplenia : Texture
          val Saver       : Texture
     }

     class Regioni: Aska {
          private fun getPisunchiKa(name: String): TextureRegion = SpriteManagerUUU.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val calendula = getPisunchiKa("calendula")
          override val grenki    = getPisunchiKa("grenki")
          override val postuplen = getPisunchiKa("postuplen")
          override val ramor     = getPisunchiKa("ramor")
          override val rshodiki  = getPisunchiKa("rshodiki")

          override val shtukiList  = List(6) { getPisunchiKa("${it.inc()}")  }

          override val Mesac       = SpriteManagerUUU.EnumTexture.Mesac      .data.texture
          override val Postuplenia = SpriteManagerUUU.EnumTexture.Postuplenia.data.texture
          override val Saver       = SpriteManagerUUU.EnumTexture.Saver      .data.texture
     }
}