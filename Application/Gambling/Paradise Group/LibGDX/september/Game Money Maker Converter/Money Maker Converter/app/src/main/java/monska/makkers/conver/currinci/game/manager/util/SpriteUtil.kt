package monska.makkers.conver.currinci.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import monska.makkers.conver.currinci.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val couchas: TextureRegion
          val monika: TextureRegion
          val nazad: TextureRegion
          val penel: TextureRegion
          val prodojit: TextureRegion
          val usd_to: TextureRegion

//          val BACKGROUND: Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val couchas = getGameRegion("couchas")
          override val monika = getGameRegion("monika")
          override val nazad = getGameRegion("nazad")
          override val penel = getGameRegion("penel")
          override val prodojit = getGameRegion("prodojit")
          override val usd_to = getGameRegion("usd_to")

//          val listikGGG = listOf(g1, g2, g3, g4, g5)
//
//          override val BACKGROUND = SpriteManager.EnumTexture.SPLASH.data.texture
     }
}