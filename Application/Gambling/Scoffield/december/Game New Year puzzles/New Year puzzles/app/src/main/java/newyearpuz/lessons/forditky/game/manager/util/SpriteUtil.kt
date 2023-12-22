package newyearpuz.lessons.forditky.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import newyearpuz.lessons.forditky.game.manager.SpriteManager


     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val a3   = getRegion("a3")
          val a3p   = getRegion("a3p")
          val a4   = getRegion("a4")
          val a4p   = getRegion("a4p")
          val a5   = getRegion("a5")
          val a5p   = getRegion("a5p")
          val exit   = getRegion("exit")
          val exitp   = getRegion("exitp")
          val next   = getRegion("next")
          val nextp   = getRegion("nextp")

          val back = SpriteManager.EnumTexture.back.data.texture
          val panel = SpriteManager.EnumTexture.panel.data.texture
          val win = SpriteManager.EnumTexture.win.data.texture

          val t1 = SpriteManager.EnumTexture.t1.data.texture
          val t2 = SpriteManager.EnumTexture.t2.data.texture
          val t3 = SpriteManager.EnumTexture.t3.data.texture
          val t4 = SpriteManager.EnumTexture.t4.data.texture
          val t5 = SpriteManager.EnumTexture.t5.data.texture
          val t6 = SpriteManager.EnumTexture.t6.data.texture

          val puziles = listOf(t1,        t2,        t3,        t4,        t5,        t6,)
     }
