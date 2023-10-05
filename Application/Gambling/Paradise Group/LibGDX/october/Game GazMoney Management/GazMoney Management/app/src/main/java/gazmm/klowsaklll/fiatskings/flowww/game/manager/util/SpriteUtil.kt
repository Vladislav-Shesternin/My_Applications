package gazmm.klowsaklll.fiatskings.flowww.game.manager.util

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import gazmm.klowsaklll.fiatskings.flowww.game.manager.SpriteManager

class SpriteUtil {

     interface AllAssets {
          val _1dd       : TextureRegion
          val _1dp       : TextureRegion
          val _1hd       : TextureRegion
          val _1hp       : TextureRegion
          val _1md       : TextureRegion
          val _1mp       : TextureRegion
          val _1wd       : TextureRegion
          val _1wp       : TextureRegion
          val _1yd       : TextureRegion
          val _1yp       : TextureRegion
          val _1         : TextureRegion
          val _2         : TextureRegion
          val _3         : TextureRegion
          val _4         : TextureRegion
          val _5         : TextureRegion
          val _6         : TextureRegion
          val _7         : TextureRegion
          val _8         : TextureRegion
          val _9         : TextureRegion
          val _adef      : TextureRegion
          val _alld      : TextureRegion
          val _allp      : TextureRegion
          val _apre      : TextureRegion
          val _cdef      : TextureRegion
          val _cpre      : TextureRegion
          val _hdef      : TextureRegion
          val _hpre      : TextureRegion
          val _price_anal: TextureRegion
          val _t1        : TextureRegion
          val _t2        : TextureRegion
          val _t3        : TextureRegion
          val _t4        : TextureRegion

          val BAPALAH: Texture
          val HOMEK  : Texture
          val LINE   : Texture
     }

     class CommonAssets: AllAssets {
          protected fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val _1dd        = getGameRegion("1dd")
          override val _1dp        = getGameRegion("1dp")
          override val _1hd        = getGameRegion("1hd")
          override val _1hp        = getGameRegion("1hp")
          override val _1md        = getGameRegion("1md")
          override val _1mp        = getGameRegion("1mp")
          override val _1wd        = getGameRegion("1wd")
          override val _1wp        = getGameRegion("1wp")
          override val _1yd        = getGameRegion("1yd")
          override val _1yp        = getGameRegion("1yp")
          override val _1          = getGameRegion("1")
          override val _2          = getGameRegion("2")
          override val _3          = getGameRegion("3")
          override val _4          = getGameRegion("4")
          override val _5          = getGameRegion("5")
          override val _6          = getGameRegion("6")
          override val _7          = getGameRegion("7")
          override val _8          = getGameRegion("8")
          override val _9          = getGameRegion("9")
          override val _adef       = getGameRegion("adef")
          override val _alld       = getGameRegion("alld")
          override val _allp       = getGameRegion("allp")
          override val _apre       = getGameRegion("apre")
          override val _cdef       = getGameRegion("cdef")
          override val _cpre       = getGameRegion("cpre")
          override val _hdef       = getGameRegion("hdef")
          override val _hpre       = getGameRegion("hpre")
          override val _price_anal = getGameRegion("price_anal")
          override val _t1         = getGameRegion("t1")
          override val _t2         = getGameRegion("t2")
          override val _t3         = getGameRegion("t3")
          override val _t4         = getGameRegion("t4")

          override val BAPALAH     = SpriteManager.EnumTexture.BAPALAH.data.texture
          override val HOMEK       = SpriteManager.EnumTexture.HOMEK.data.texture
          override val LINE        = SpriteManager.EnumTexture.LINE.data.texture

          val lisovka = listOf(_1, _2, _3, _4, _5, _6, _7, _8, _9)
          val tlist   = listOf(_t1, _t2, _t3, _t4)
     }
}