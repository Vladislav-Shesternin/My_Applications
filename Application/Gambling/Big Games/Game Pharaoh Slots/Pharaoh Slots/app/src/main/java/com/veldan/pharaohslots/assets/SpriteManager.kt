package com.veldan.pharaohslots.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.veldan.pharaohslots.assets.util.TextureData

object SpriteManager {

    var loadListSprite = mutableListOf<ISprite>()
    var loadListSpriteList = mutableListOf<ISpriteList>()


    private val parameter = TextureLoader.TextureParameter().apply {
        minFilter = Texture.TextureFilter.Linear
        magFilter = Texture.TextureFilter.Linear
    }



    fun load(assetManager: AssetManager) {
        loadListSprite.onEach { assetManager.load(it.data.path, Texture::class.java, parameter) }
        loadListSpriteList.onEach { listTextureData -> listTextureData.dataList.onEach { textureData -> assetManager.load(textureData.path, Texture::class.java, parameter) } }
    }

    fun init(assetManager: AssetManager) {
        loadListSprite.onEach { it.data.texture = assetManager[it.data.path, Texture::class.java] }
        loadListSpriteList.onEach { listTextureData -> listTextureData.dataList.onEach { textureData -> textureData.texture = assetManager[textureData.path, Texture::class.java] } }
    }



    enum class SplashSprite(override val data: TextureData): ISprite {
        BACKGROUND(TextureData("sprites/background/1.png")),
        PROGRESS_PANEL(TextureData("sprites/progress_panel.png")),
    }

    enum class MenuSprite(override val data: TextureData): ISprite {
        BACKGROUND(    TextureData("sprites/background/2.png")),
        BUTTON_DEF_1(  TextureData("sprites/button/button_def_1.png")  ),
        BUTTON_PRESS_1(TextureData("sprites/button/button_press_1.png")),
    }

    enum class OptionsSprite(override val data: TextureData): ISprite {
        BACKGROUND(         TextureData("sprites/background/3.png")                    ),
        BACK_DEF(           TextureData("sprites/button/back_def.png")                 ),
        BACK_PRESS(         TextureData("sprites/button/back_press.png")               ),
        BOX_DEF(            TextureData("sprites/check_box/checkbox_def.png")          ),
        BOX_PRESS(          TextureData("sprites/check_box/checkbox_che.png")          ),
        PROGRESS_PANEL(     TextureData("sprites/progress_bar/progress_panel.png")     ),
        PROGRESS_GRIP(      TextureData("sprites/progress_bar/progress_grip.png")      ),
        PROGRESS_RANGE(     TextureData("sprites/progress_bar/progress_range.png")     ),
        STATIC(             TextureData("sprites/static.png")                          ),
    }

    enum class GameSprite(override val data: TextureData): ISprite {
        BACKGROUND(      TextureData("sprites/background/4.png")         ),
        BALANCE_PANEL(   TextureData("sprites/balance_panel.png")        ),
        AUTOGO_DEF(      TextureData("sprites/button/autogo_def.png")    ),
        AUTOGO_PRESS(    TextureData("sprites/button/autogo_press.png")  ),
        AUTOGO_DIS(      TextureData("sprites/button/autogo_dis.png")    ),
        GO_DEF(          TextureData("sprites/button/go_def.png")        ),
        GO_PRESS(        TextureData("sprites/button/go_press.png")      ),
        GO_DIS(          TextureData("sprites/button/go_dis.png")        ),
        PLUS_DEF(        TextureData("sprites/button/plus_def.png")      ),
        PLUS_PRESS(      TextureData("sprites/button/plus_press.png")    ),
        PLUS_DIS(        TextureData("sprites/button/plus_dis.png")      ),
        MINUS_DEF(       TextureData("sprites/button/minus_def.png")     ),
        MINUS_PRESS(     TextureData("sprites/button/minus_press.png")   ),
        MINUS_DIS(       TextureData("sprites/button/minus_dis.png")     ),
        BET_PANEL(       TextureData("sprites/bet_panel.png")            ),
        MENU_DEF(        TextureData("sprites/button/menu_def.png")      ),
        MENU_PRESS(      TextureData("sprites/button/menu_press.png")    ),
        GLOW(            TextureData("sprites/glow.png")                 ),
        SLOT_GROUP_PANEL(TextureData("sprites/slot_group_panel.png")     ),
        SEPARATOR(       TextureData("sprites/separator.png")            ),
        SLOT_ITEM_ALL(   TextureData("sprites/slot_item_all.png")        ),
        SLOT_ITEM_BONUS( TextureData("sprites/slot_item_bonus.png")      ),
        BLUE(            TextureData("sprites/super_game/blue.png")      ),
        INDICATOR(       TextureData("sprites/super_game/indicator.png") ),
        RED(             TextureData("sprites/super_game/red.png")       ),
        ROULETTE(        TextureData("sprites/super_game/roulette.png")  ),
        X0(              TextureData("sprites/super_game/x0.png")        ),
        X2(              TextureData("sprites/super_game/x2.png")        ),
    }



    enum class MenuRUSprite(override val data: TextureData): ISprite {
        PLAY(TextureData(   "sprites/language/ru/play.png")   ),
        OPTIONS(TextureData("sprites/language/ru/options.png")),
        EXIT(TextureData(   "sprites/language/ru/exit.png")   ),
    }

    enum class MenuUKSprite(override val data: TextureData): ISprite {
        PLAY(TextureData(   "sprites/language/uk/play.png")   ),
        OPTIONS(TextureData("sprites/language/uk/options.png")),
        EXIT(TextureData(   "sprites/language/uk/exit.png")   ),
    }

    enum class MenuUSSprite(override val data: TextureData): ISprite {
        PLAY(TextureData(   "sprites/language/us/play.png")   ),
        OPTIONS(TextureData("sprites/language/us/options.png")),
        EXIT(TextureData(   "sprites/language/us/exit.png")   ),
    }



    enum class GameUKSprite(override val data: TextureData): ISprite {
        GO(     TextureData("sprites/language/uk/go.png")     ),
        GO_DIS( TextureData("sprites/language/uk/go_dis.png") ),
        BET(    TextureData("sprites/language/uk/bet.png")    ),
        FREE_GO(TextureData("sprites/language/uk/free_go.png")),
        TAKE(   TextureData("sprites/language/uk/take.png")   ),
    }

    enum class GameRUSprite(override val data: TextureData): ISprite {
        GO(     TextureData("sprites/language/ru/go.png")     ),
        GO_DIS( TextureData("sprites/language/ru/go_dis.png") ),
        BET(    TextureData("sprites/language/ru/bet.png")    ),
        FREE_GO(TextureData("sprites/language/ru/free_go.png")),
        TAKE(   TextureData("sprites/language/ru/take.png")   ),
    }

    enum class GameUSSprite(override val data: TextureData): ISprite {
        GO(     TextureData("sprites/language/us/go.png")     ),
        GO_DIS( TextureData("sprites/language/us/go_dis.png") ),
        BET(    TextureData("sprites/language/us/bet.png")    ),
        FREE_GO(TextureData("sprites/language/us/free_go.png")),
        TAKE(   TextureData("sprites/language/us/take.png")   ),
    }



    enum class SlotItemSpriteList(override val dataList: List<TextureData>): ISpriteList {
        SLOT_ITEM_LIST(List(9) { TextureData("sprites/list/slot_item/${it.inc()}.png") })
    }



    interface ISprite {
        val data: TextureData
    }

    interface ISpriteList {
        val dataList: List<TextureData>
    }

}