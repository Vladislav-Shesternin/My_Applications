package finann.promik.technikuss.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import finann.promik.technikuss.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val REDON : TextureRegion
          val ROM   : TextureRegion

          val FINISHBACKGROUND: Texture
          val HELLOBACKGROUND : Texture
          val QUIZBACKGROUND  : Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val REDON = getGameRegion("redon")
          override val ROM   = getGameRegion("rom")

          override val FINISHBACKGROUND = SpriteManager.EnumTexture.FINISHBACKGROUND.data.texture
          override val HELLOBACKGROUND  = SpriteManager.EnumTexture.HELLOBACKGROUND.data.texture
          override val QUIZBACKGROUND   = SpriteManager.EnumTexture.QUIZBACKGROUND.data.texture

     }
}