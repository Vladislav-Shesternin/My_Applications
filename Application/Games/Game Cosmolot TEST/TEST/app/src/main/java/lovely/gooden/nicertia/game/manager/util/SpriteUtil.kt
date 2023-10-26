package lovely.gooden.nicertia.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import lovely.gooden.nicertia.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val a: TextureRegion
          val b: TextureRegion
          val c: TextureRegion
          val d: TextureRegion
          val ger: TextureRegion
          val reg: TextureRegion

          val invest_gaz_quiz_background: Texture
          val gameka  : Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val a = getGameRegion("a")
          override val b = getGameRegion("b")
          override val c = getGameRegion("c")
          override val d = getGameRegion("d")
          override val ger = getGameRegion("ger")
          override val reg = getGameRegion("reg")

          override val invest_gaz_quiz_background     = SpriteManager.EnumTexture.invest_gaz_quiz_background .data.texture
          override val gameka       = SpriteManager.EnumTexture.gameka .data.texture

          val liba = listOf(a,b,c,d)
          val progressList = List(20) { getGameRegion("${it.inc()}") }
     }
}