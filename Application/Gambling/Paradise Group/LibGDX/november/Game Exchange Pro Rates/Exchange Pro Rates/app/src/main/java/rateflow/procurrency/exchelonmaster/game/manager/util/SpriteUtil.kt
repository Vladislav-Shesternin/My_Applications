package rateflow.procurrency.exchelonmaster.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import rateflow.procurrency.exchelonmaster.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val baad: TextureRegion
          val belwa: TextureRegion
          val tuminute: TextureRegion
          val framwa: TextureRegion
          val good: TextureRegion
          val vremachko: TextureRegion

          val AlphaA:   Texture
          val Golem:   Texture
          val maswa:   Texture
          val ResultOprosa:   Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val baad  = getGameRegion("baad")
          override val belwa  = getGameRegion("belwa")
          override val tuminute  = getGameRegion("tuminute")
          override val framwa  = getGameRegion("framwa")
          override val good  = getGameRegion("good")
          override val vremachko  = getGameRegion("vremachko")

          override val AlphaA               = SpriteManager.EnumTexture.AlphaA.data.texture
          override val Golem           = SpriteManager.EnumTexture.Golem.data.texture
          override val maswa           = SpriteManager.EnumTexture.maswa.data.texture
          override val ResultOprosa         = SpriteManager.EnumTexture.ResultOprosa.data.texture
     }
}