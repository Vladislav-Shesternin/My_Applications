package com.sca.rab.que.stgame.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.sca.rab.que.stgame.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          val LOA_LOAD     = SpriteManager.EnumTexture.LOA_LOAD.data.texture
          val LOA_LOADING  = SpriteManager.EnumTexture.LOA_LOADING.data.texture
          val LOA_MASK     = SpriteManager.EnumTexture.LOA_MASK.data.texture
          val LOALOAD_MAIN = SpriteManager.EnumTexture.LOALOAD_MAIN.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val fik             = getRegion("fik")
          val flow            = getRegion("flow")
          val lapa            = getRegion("lapa")
          val msk             = getRegion("msk")
          val panal           = getRegion("panal")
          val pikalka         = getRegion("pikalka")
          val pnsd            = getRegion("pnsd")
          val poas            = getRegion("poas")
          val pusih           = getRegion("pusih")
          val suslik          = getRegion("suslik")
          val tim_ic          = getRegion("tim_ic")
          val timi_terner     = getRegion("timi_terner")
          val volume_progress = getRegion("volume_progress")

          val LILIK           = SpriteManager.EnumTexture.LILIK.data.texture
          val LOA_GAME        = SpriteManager.EnumTexture.LOA_GAME.data.texture
          val LOA_LEVEL       = SpriteManager.EnumTexture.LOA_LEVEL.data.texture
          val LOA_LOSE        = SpriteManager.EnumTexture.LOA_LOSE.data.texture
          val LOA_MENU        = SpriteManager.EnumTexture.LOA_MENU.data.texture
          val LOA_RILUSE      = SpriteManager.EnumTexture.LOA_RILUSE.data.texture
          val LOA_SETINGERROI = SpriteManager.EnumTexture.LOA_SETINGERROI.data.texture
          val LOA_WIN         = SpriteManager.EnumTexture.LOA_WIN.data.texture
          val TIMR_MASKR      = SpriteManager.EnumTexture.TIMR_MASKR.data.texture
          val VOLUME_MASK     = SpriteManager.EnumTexture.VOLUME_MASK.data.texture

          private val _1 = SpriteManager.EnumTexture._1.data.texture
          private val _2 = SpriteManager.EnumTexture._2.data.texture
          private val _3 = SpriteManager.EnumTexture._3.data.texture
          private val _4 = SpriteManager.EnumTexture._4.data.texture
          private val _5 = SpriteManager.EnumTexture._5.data.texture
          private val _6 = SpriteManager.EnumTexture._6.data.texture
          private val _7 = SpriteManager.EnumTexture._7.data.texture
          private val _8 = SpriteManager.EnumTexture._8.data.texture
          private val _9 = SpriteManager.EnumTexture._9.data.texture

          val puzzleList = listOf(_1,_2,_3,_4,_5,_6,_7,_8,_9)
     }

}