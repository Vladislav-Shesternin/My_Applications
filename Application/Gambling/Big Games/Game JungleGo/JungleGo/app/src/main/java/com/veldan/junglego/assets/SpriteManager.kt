package com.veldan.junglego.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.veldan.junglego.assets.util.TextureData

object SpriteManager {

    var loadListSprite = mutableListOf<ISprite>()
    var loadListSpriteList = mutableListOf<ISpriteList>()



    fun load(assetManager: AssetManager) {
        loadListSprite.onEach { assetManager.load(it.textureData.path, Texture::class.java) }
        loadListSpriteList.onEach { listTextureData -> listTextureData.textureDataList.onEach { textureData -> assetManager.load(textureData.path, Texture::class.java) } }
    }

    fun init(assetManager: AssetManager) {
        loadListSprite.onEach { it.textureData.texture = assetManager[it.textureData.path, Texture::class.java] }
        loadListSpriteList.onEach { listTextureData -> listTextureData.textureDataList.onEach { textureData -> textureData.texture = assetManager[textureData.path, Texture::class.java] } }
    }



    enum class SplashSprite(override val textureData: TextureData): ISprite {
        BACKGROUND(TextureData("sprites/background/1.png")),
    }

    enum class MenuSprite(override val textureData: TextureData): ISprite {
        BACKGROUND(    TextureData("sprites/background/2.png")),
        BUTTON_DEF_1(  TextureData("sprites/button/button_def_1.png")  ),
        BUTTON_PRESS_1(TextureData("sprites/button/button_press_1.png")),
    }

    enum class OptionsSprite(override val textureData: TextureData): ISprite {
        BACKGROUND(         TextureData("sprites/background/1.png")),
        BACK_DEF(           TextureData("sprites/button/back_def.png")                 ),
        BACK_PRESS(         TextureData("sprites/button/back_press.png")               ),
        BOX_DEF(            TextureData("sprites/check_box/box_def.png")               ),
        BOX_PRESS(          TextureData("sprites/check_box/box_press.png")             ),
        PROGRESS_CONTROLLER(TextureData("sprites/progress_bar/progress_controller.png")),
        PROGRESS_FRAME(     TextureData("sprites/progress_bar/progress_frame.png")     ),
        PROGRESS_INDICATOR( TextureData("sprites/progress_bar/progress_indicator.png") ),
        STATIC(             TextureData("sprites/static.png")                          ),
    }

    enum class GameSprite(override val textureData: TextureData): ISprite {
        BACKGROUND(        TextureData("sprites/background/1.png")           ),
        BALANCE_PANEL(     TextureData("sprites/balance_panel.png")          ),
        AUTOSPIN_DEF(      TextureData("sprites/button/autospin_def.png")    ),
        AUTOSPIN_PRESS(    TextureData("sprites/button/autospin_press.png")  ),
        AUTOSPIN_DIS(      TextureData("sprites/button/autospin_dis.png")    ),
        MENU_DEF(          TextureData("sprites/button/menu_def.png")        ),
        MENU_PRESS(        TextureData("sprites/button/menu_press.png")      ),
        SPIN_DEF(          TextureData("sprites/button/spin_def.png")        ),
        SPIN_DIS(          TextureData("sprites/button/spin_dis.png")        ),
        SPIN_PRESS(        TextureData("sprites/button/spin_press.png")      ),
        BONUS_DONE_DEF(    TextureData("sprites/button/bonus_done_def.png")  ),
        BONUS_DONE_PRESS(  TextureData("sprites/button/bonus_done_press.png")),
        SLOT_GROUP_PANEL(  TextureData("sprites/slot_group_panel.png")       ),
        SEPARATOR(         TextureData("sprites/separator.png")              ),
        SLOT_ITEM_ALL(     TextureData("sprites/slot_item_all.png")          ),
        SLOT_ITEM_BONUS(   TextureData("sprites/slot_item_bonus.png")        ),
        LINE(              TextureData("sprites/line.png")                   ),
        BONUS_BOX(         TextureData("sprites/bonus_box.png")              ),
        BONUS_FAIL(        TextureData("sprites/bonus_fail.png")             ),
        BONUS_WIN(         TextureData("sprites/bonus_win.png")              ),
        ROULETTE(          TextureData("sprites/roulette/roulette.png")      ),
        ROULETTE_INDICATOR(TextureData("sprites/roulette/indicator.png")     ),
        ROULETTE_GO(       TextureData("sprites/roulette/go.png")            ),
        PLUS_DEF(          TextureData("sprites/button/plus_def.png")        ),
        PLUS_PRESS(        TextureData("sprites/button/plus_press.png")      ),
        MINUS_DEF(         TextureData("sprites/button/minus_def.png")       ),
        MINUS_PRESS(       TextureData("sprites/button/minus_press.png")     ),
        BET_PANEL(         TextureData("sprites/bet_panel.png")              ),
    }



    enum class MenuRUSprite(override val textureData: TextureData): ISprite {
        PLAY(TextureData(   "sprites/language/ru/play.png")   ),
        OPTIONS(TextureData("sprites/language/ru/options.png")),
        EXIT(TextureData(   "sprites/language/ru/exit.png")   ),
    }

    enum class MenuUKSprite(override val textureData: TextureData): ISprite {
        PLAY(TextureData(   "sprites/language/uk/play.png")   ),
        OPTIONS(TextureData("sprites/language/uk/options.png")),
        EXIT(TextureData(   "sprites/language/uk/exit.png")   ),
    }

    enum class MenuUSSprite(override val textureData: TextureData): ISprite {
        PLAY(TextureData(   "sprites/language/us/play.png")   ),
        OPTIONS(TextureData("sprites/language/us/options.png")),
        EXIT(TextureData(   "sprites/language/us/exit.png")   ),
    }



    enum class GameRUSprite(override val textureData: TextureData): ISprite {
        SPIN(TextureData("sprites/language/ru/spin.png")),
        WAIT(TextureData("sprites/language/ru/wait.png")),
        BET(TextureData( "sprites/language/ru/bet.png") ),
    }

    enum class GameUKSprite(override val textureData: TextureData): ISprite {
        SPIN(TextureData("sprites/language/uk/spin.png")),
        WAIT(TextureData("sprites/language/uk/wait.png")),
        BET(TextureData( "sprites/language/uk/bet.png") ),
    }

    enum class GameUSSprite(override val textureData: TextureData): ISprite {
        SPIN(TextureData("sprites/language/us/spin.png")),
        WAIT(TextureData("sprites/language/us/wait.png")),
        BET(TextureData( "sprites/language/us/bet.png") ),
    }



    enum class AnimationSpriteList(override val textureDataList: List<TextureData>): ISpriteList {
        BIRD_LIST(List(3) { TextureData("sprites/animations/bird/${it.inc()}.png") })
    }

    enum class SlotItemSpriteList(override val textureDataList: List<TextureData>): ISpriteList {
        SLOT_ITEM_LIST(List(8) { TextureData("sprites/list/slot_item/${it.inc()}.png") })
    }

    enum class PriceSpriteList(override val textureDataList: List<TextureData>): ISpriteList {
        PRICE_LIST(List(10) { TextureData("sprites/list/price/${it.inc()}.png") })
    }



    interface ISprite {
        val textureData: TextureData
    }

    interface ISpriteList {
        val textureDataList: List<TextureData>
    }

}