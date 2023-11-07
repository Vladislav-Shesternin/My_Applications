package novibet.leoforos.irakloiu.office.helper.game.manager.util

import com.badlogic.gdx.graphics.g2d.TextureRegion
import novibet.leoforos.irakloiu.office.helper.game.manager.SpriteManager

class SpriteUtil {

     class SplashAssets {
          val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
          val TITLE      = SpriteManager.EnumTexture.TITLE.data.texture
     }

     interface AllAssets {
          val CALENDAR       : TextureRegion
          val MENU_ABOUT_US  : TextureRegion
          val MENU_CALENDAR  : TextureRegion
          val MENU_RULES     : TextureRegion
          val PHOTO          : TextureRegion
          val SELECTOR       : TextureRegion
          val SELECTOR_PANEL : TextureRegion
          val WEEKEND_DAY    : TextureRegion
          val WORKING_DAY    : TextureRegion
          val CELL           : TextureRegion
     }

     class CommonAssets: AllAssets {
          private fun getGameRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

          override val CALENDAR       = getGameRegion("calendar")
          override val MENU_ABOUT_US  = getGameRegion("menu_about_us")
          override val MENU_CALENDAR  = getGameRegion("menu_calendar")
          override val MENU_RULES     = getGameRegion("menu_rules")
          override val PHOTO          = getGameRegion("photo")
          override val SELECTOR       = getGameRegion("selector")
          override val SELECTOR_PANEL = getGameRegion("selector_panel")
          override val WEEKEND_DAY    = getGameRegion("weekend_day")
          override val WORKING_DAY    = getGameRegion("working_day")
          override val CELL           = getGameRegion("cell")
     }

}