package italodisco.fernando.lucherano.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import italodisco.fernando.lucherano.game.manager.SpriteManagerUUU

class TutaSparta {

     interface Figa {
          val cadar  : TextureRegion
          val dobavka        : TextureRegion

          val chtetka : List<TextureRegion>

          val Barboval       : Texture
          val Lodogor : Texture
          val Sarafan       : Texture
          val hashodik       : Texture
          val postitutka       : Texture
     }

     class Gera: Figa {
          private fun getPisunchiKa(name: String): TextureRegion = SpriteManagerUUU.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val cadar      = getPisunchiKa("cadar")
          override val dobavka    = getPisunchiKa("dobavka")

          override val chtetka  = List(6) { getPisunchiKa("${it.inc()}")  }

          override val Barboval       = SpriteManagerUUU.EnumTexture.Barboval.data.texture
          override val Lodogor          = SpriteManagerUUU.EnumTexture.Lodogor.data.texture
          override val Sarafan       = SpriteManagerUUU.EnumTexture.Sarafan.data.texture
          override val hashodik       = SpriteManagerUUU.EnumTexture.hashodik.data.texture
          override val postitutka       = SpriteManagerUUU.EnumTexture.postitutka.data.texture
     }
}