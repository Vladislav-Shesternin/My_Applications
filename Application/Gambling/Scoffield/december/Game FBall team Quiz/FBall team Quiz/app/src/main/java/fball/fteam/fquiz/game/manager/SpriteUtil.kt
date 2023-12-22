package fball.fteam.fquiz.game.manager

import com.badlogic.gdx.graphics.g2d.TextureRegion


class AllAssets {
     private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

     val beck = getRegion("beck")
     val blu = getRegion("blu")
     val df = getRegion("df")
     val g = getRegion("g")
     val pr = getRegion("pr")
     val red = getRegion("red")

     val pipe = List(12) { getRegion("${13+it}") }

     val btnrs = SpriteManager.EnumTexture.btnrs.data.texture
     val flgs = SpriteManager.EnumTexture.flgs.data.texture
     val pole = SpriteManager.EnumTexture.pole.data.texture
     val btnrs_uk = SpriteManager.EnumTexture.btnrs_uk.data.texture
     val pisok = SpriteManager.EnumTexture.pisok.data.texture

}