package aiebu.kakono.tutokazalos.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import aiebu.kakono.tutokazalos.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val itemList : List<TextureRegion>
          val graphList: List<TextureRegion>

          val BANAKIR  : Texture
          val IBIZIJA  : Texture
          val SOSKAPOT : Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val itemList  = List(5) { getGameRegion("${it.inc()}")  }
          override val graphList = List(4) { getGameRegion("a${it.inc()}") }

          override val BANAKIR  = SpriteManager.EnumTexture.BANAKIR .data.texture
          override val IBIZIJA  = SpriteManager.EnumTexture.IBIZIJA .data.texture
          override val SOSKAPOT = SpriteManager.EnumTexture.SOSKAPOT.data.texture
     }
}