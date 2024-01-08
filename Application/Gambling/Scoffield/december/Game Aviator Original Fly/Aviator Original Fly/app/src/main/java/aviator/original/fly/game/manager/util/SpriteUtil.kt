package aviator.original.fly.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import aviator.original.fly.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          protected fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.LOADING.data.atlas.findRegion(name)

          val avia_and_loading = getRegion("avia_and_loading")
          val loadinger        = getRegion("loadinger")

          val AviatorLoading = SpriteManager.EnumTexture.AviatorLoading.data.texture
     }

     class GameAssets {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          val cursorro    = getRegion("cursorro")
          val exit        = getRegion("exit")
          val menu_def    = getRegion("menu_def")
          val menu_press  = getRegion("menu_press")
          val music_check = getRegion("music_check")
          val music_def   = getRegion("music_def")
          val panella     = getRegion("panella")
          val pause_check = getRegion("pause_check")
          val pause_def   = getRegion("pause_def")
          val progresska  = getRegion("progresska")
          val timer_panel = getRegion("timer_panel")

          val avia_btns          = SpriteManager.EnumTexture.avia_btns.data.texture
          val AviatorResultFalse = SpriteManager.EnumTexture.AviatorResultFalse.data.texture
          val AviatorResultTrue  = SpriteManager.EnumTexture.AviatorResultTrue.data.texture
          val menu               = SpriteManager.EnumTexture.menu.data.texture
          val rules              = SpriteManager.EnumTexture.rules.data.texture
          val settings           = SpriteManager.EnumTexture.settings.data.texture

          private val _1 = SpriteManager.EnumTexture.puzzle1.data.texture
          private val _2 = SpriteManager.EnumTexture.puzzle2.data.texture
          private val _3 = SpriteManager.EnumTexture.puzzle3.data.texture
          private val _4 = SpriteManager.EnumTexture.puzzle4.data.texture
          private val _5 = SpriteManager.EnumTexture.puzzle5.data.texture
          private val _6 = SpriteManager.EnumTexture.puzzle6.data.texture
          private val _7 = SpriteManager.EnumTexture.puzzle7.data.texture
          private val _8 = SpriteManager.EnumTexture.puzzle8.data.texture

          val puzzleList = listOf(_1,_2,_3,_4,_5,_6,_7,_8,)
     }

}