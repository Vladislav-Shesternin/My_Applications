package com.hlperki.pesgllra.game.manager

import com.badlogic.gdx.graphics.g2d.TextureRegion

class AllAssets {
     private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

     val btn_mini   = getRegion("btn_mini")
     val exit       = getRegion("exit")
     val ggg        = getRegion("ggg")
     val make_merry = getRegion("make_merry")
     val menuuu     = getRegion("menuuu")
     val music_def  = getRegion("music_def")
     val music_not  = getRegion("music_not")
     val rrr        = getRegion("rrr")

     val Menu = SpriteManager.EnumTexture.Menu.data.texture
     val blure = SpriteManager.EnumTexture.blure.data.texture
     val blure_3 = SpriteManager.EnumTexture.blure_3.data.texture
     val blure2 = SpriteManager.EnumTexture.blure2.data.texture
     val ok     = SpriteManager.EnumTexture.ok.data.texture

}