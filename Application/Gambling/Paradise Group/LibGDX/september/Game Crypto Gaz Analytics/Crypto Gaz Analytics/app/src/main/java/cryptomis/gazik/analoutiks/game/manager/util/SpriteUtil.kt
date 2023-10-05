package cryptomis.gazik.analoutiks.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import cryptomis.gazik.analoutiks.game.manager.SpriteManager
import cryptomis.gazik.analoutiks.game.utils.TextureEmpty

class SpriteUtil {

     interface AllAssets {
          val gren: TextureRegion
          val nazd: TextureRegion
          val red: TextureRegion
          val title: TextureRegion
          val g1: TextureRegion
          val g2: TextureRegion
          val g3: TextureRegion
          val g4: TextureRegion
          val g5: TextureRegion

          val BACKGROUND: Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val gren = getGameRegion("gren")
          override val nazd = getGameRegion("nazd")
          override val red = getGameRegion("red")
          override val title = getGameRegion("title")
          override val g1 = getGameRegion("g1")
          override val g2 = getGameRegion("g2")
          override val g3 = getGameRegion("g3")
          override val g4 = getGameRegion("g4")
          override val g5 = getGameRegion("g5")

          val listikGGG = listOf(g1, g2, g3, g4, g5)

          override val BACKGROUND = SpriteManager.EnumTexture.SPLASH.data.texture
     }
}