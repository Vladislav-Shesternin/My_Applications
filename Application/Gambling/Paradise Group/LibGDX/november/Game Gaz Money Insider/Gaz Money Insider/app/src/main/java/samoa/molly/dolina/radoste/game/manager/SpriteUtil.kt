package samoa.molly.dolina.radoste.game.manager

import com.badlogic.gdx.graphics.g2d.TextureRegion

class SpriteUtil {

     class CommonAssets {
          private fun getR(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val aList = List(7) { getR("${it.inc()}") }
          val bList = List(3) { getR("a${it.inc()}") }
          val cList = List(2) { getR("d${it.inc()}") }

          val GMI   = SpriteManager.EnumTexture.GMI.data.texture
          val KK   = SpriteManager.EnumTexture.KK.data.texture
          val KKD   = SpriteManager.EnumTexture.KKD.data.texture
          val KKJ   = SpriteManager.EnumTexture.KKJ.data.texture
     }

}