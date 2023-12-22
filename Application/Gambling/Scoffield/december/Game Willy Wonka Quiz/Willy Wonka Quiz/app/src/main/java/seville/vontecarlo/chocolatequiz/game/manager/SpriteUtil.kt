package seville.vontecarlo.chocolatequiz.game.manager

import com.badlogic.gdx.graphics.g2d.TextureRegion

class AllAssets {
     private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

     val answer     = getRegion("answer")
     val exit       = getRegion("exit")
     val falses     = getRegion("falses")
     val music      = getRegion("music")
     val music_netu = getRegion("music_netu")
     val question   = getRegion("question")
     val quiz       = getRegion("quiz")
     val trues      = getRegion("trues")

     val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture

}