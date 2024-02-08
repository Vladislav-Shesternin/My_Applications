package com.favsport.slots.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.favsport.slots.assets.util.TextureData

object SpriteManager {

    var loadListSprite = mutableListOf<Sprite>()
    var loadListSpriteList = mutableListOf<SpriteList>()

    fun load(assetManager: AssetManager) {
        loadListSprite.onEach { assetManager.load(it.textureData.path, Texture::class.java) }
        loadListSpriteList.onEach { listTextureData ->
            listTextureData.textureDataList.onEach { textureData ->
                assetManager.load(textureData.path, Texture::class.java)
            }
        }
    }

    fun init(assetManager: AssetManager) {
        loadListSprite.onEach { it.textureData.texture = assetManager[it.textureData.path, Texture::class.java] }
        loadListSpriteList.onEach { listTextureData ->
            listTextureData.textureDataList.onEach { textureData ->
                textureData.texture = assetManager[textureData.path, Texture::class.java]
            }
        }
    }

    fun loadAll(assetManager: AssetManager) {
        loadListSprite = mutableListOf<Sprite>(
            *MenuSprite.values(),
            *SettingsSprite.values(),
            *GameSprite.values(),
            *CollectionSprite.values(),
        )
        loadListSpriteList = mutableListOf<SpriteList>(
            *GameSpriteList.values(),
            *CollectionSpriteList.values(),
        )
        load(assetManager)
    }



    enum class MenuSprite(override val textureData: TextureData): Sprite {
        BUTTON_PANEL(  TextureData("sprites/menu/button_panel.png")  ),

        EXIT_DEF_US(      TextureData("sprites/menu/us/exit_def.png")      ),
        EXIT_PRESS_US(    TextureData("sprites/menu/us/exit_press.png")    ),
        PLAY_DEF_US(      TextureData("sprites/menu/us/play_def.png")      ),
        PLAY_PRESS_US(    TextureData("sprites/menu/us/play_press.png")    ),
        SETTINGS_DEF_US(  TextureData("sprites/menu/us/settings_def.png")  ),
        SETTINGS_PRESS_US(TextureData("sprites/menu/us/settings_press.png")),

        EXIT_DEF_UK(      TextureData("sprites/menu/uk/exit_def.png")      ),
        EXIT_PRESS_UK(    TextureData("sprites/menu/uk/exit_press.png")    ),
        PLAY_DEF_UK(      TextureData("sprites/menu/uk/play_def.png")      ),
        PLAY_PRESS_UK(    TextureData("sprites/menu/uk/play_press.png")    ),
        SETTINGS_DEF_UK(  TextureData("sprites/menu/uk/settings_def.png")  ),
        SETTINGS_PRESS_UK(TextureData("sprites/menu/uk/settings_press.png")),

        EXIT_DEF_RU(      TextureData("sprites/menu/ru/exit_def.png")      ),
        EXIT_PRESS_RU(    TextureData("sprites/menu/ru/exit_press.png")    ),
        PLAY_DEF_RU(      TextureData("sprites/menu/ru/play_def.png")      ),
        PLAY_PRESS_RU(    TextureData("sprites/menu/ru/play_press.png")    ),
        SETTINGS_DEF_RU(  TextureData("sprites/menu/ru/settings_def.png")  ),
        SETTINGS_PRESS_RU(TextureData("sprites/menu/ru/settings_press.png")),
    }

    enum class SettingsSprite(override val textureData: TextureData): Sprite {
        BOX_DEF(        TextureData("sprites/settings/box_def.png")        ),
        BOX_PRESS(      TextureData("sprites/settings/box_press.png")      ),
        PROGRESS(       TextureData("sprites/settings/progress.png")       ),
        PROGRESS_FRAME( TextureData("sprites/settings/progress_frame.png") ),
        PROGRESS_GRIP(  TextureData("sprites/settings/progress_grip.png")  ),
        SETTINGS_STATIC(TextureData("sprites/settings/settings_static.png")),
    }

    enum class GameSprite(override val textureData: TextureData): Sprite {
        BACKGROUND(      TextureData("sprites/game/background.png")          ),
        BALANCE_PANEL(   TextureData("sprites/game/balance_panel.png")       ),
        COLLECTION_DEF(  TextureData("sprites/game/collection_def.png")      ),
        COLLECTION_PRESS(TextureData("sprites/game/collection_press.png")    ),
        LINE_WIN(        TextureData("sprites/game/line_win.png")            ),
        MENU_DEF(        TextureData("sprites/game/menu_def.png")            ),
        MENU_PRESS(      TextureData("sprites/game/menu_press.png")          ),
        SLOT_GROUP_PANEL(TextureData("sprites/game/slot_group_panel.png")    ),
        SLOT_SEPARATOR(  TextureData("sprites/game/slot_separator.png")      ),
        GLOW(            TextureData("sprites/game/glow.png")                ),
        ALL(             TextureData("sprites/game/all.png")                 ),
        BONUS(           TextureData("sprites/game/bonus.png")               ),
        BONUS_CLOSE(     TextureData("sprites/game/bonus_close.png")         ),
        BONUS_BACKGROUND(TextureData("sprites/game/bonus_background.png")    ),
        BONUS_BOX(       TextureData("sprites/game/bonus_box.png")           ),
        AUTOSPIN_DEF(    TextureData("sprites/game/autospin_def.png")        ),
        AUTOSPIN_PRESS(  TextureData("sprites/game/autospin_press.png")      ),
        AUTOSPIN_DISABLE(TextureData("sprites/game/autospin_disable.png")    ),
        NONE(            TextureData("sprites/game/none.png")                ),

        SPIN_DEF_US(        TextureData("sprites/game/us/spin_def.png")      ),
        SPIN_PRESS_US(      TextureData("sprites/game/us/spin_press.png")    ),
        SPIN_DISABLE_US(      TextureData("sprites/game/us/spin_disable.png")),

        SPIN_DEF_UK(        TextureData("sprites/game/uk/spin_def.png")      ),
        SPIN_PRESS_UK(      TextureData("sprites/game/uk/spin_press.png")    ),
        SPIN_DISABLE_UK(      TextureData("sprites/game/uk/spin_disable.png")),

        SPIN_DEF_RU(        TextureData("sprites/game/ru/spin_def.png")      ),
        SPIN_PRESS_RU(      TextureData("sprites/game/ru/spin_press.png")    ),
        SPIN_DISABLE_RU(      TextureData("sprites/game/ru/spin_disable.png")),
    }

    enum class CollectionSprite(override val textureData: TextureData): Sprite {
        COIN(       TextureData("sprites/collection/coin.png")       ),
        LEFT_DEF(   TextureData("sprites/collection/left_def.png")   ),
        LEFT_PRESS( TextureData("sprites/collection/left_press.png") ),
        RIGHT_DEF(  TextureData("sprites/collection/right_def.png")  ),
        RIGHT_PRESS(TextureData("sprites/collection/right_press.png")),
        STAND(      TextureData("sprites/collection/stand.png")      ),

        BACK_DEF_US(   TextureData("sprites/collection/us/back_def.png")   ),
        BACK_PRESS_US( TextureData("sprites/collection/us/back_press.png") ),

        BACK_DEF_UK(   TextureData("sprites/collection/uk/back_def.png")   ),
        BACK_PRESS_UK( TextureData("sprites/collection/uk/back_press.png") ),

        BACK_DEF_RU(   TextureData("sprites/collection/ru/back_def.png")   ),
        BACK_PRESS_RU( TextureData("sprites/collection/ru/back_press.png") ),
    }

    enum class GameSpriteList(override val textureDataList: List<TextureData>): SpriteList {
        ITEM_LIST( List(8) { TextureData("sprites/game/item_list/${it.inc()}.png")  }),
    }

    enum class CollectionSpriteList(override val textureDataList: List<TextureData>): SpriteList {
        ITEM_LIST( List(8) { TextureData("sprites/collection/item_list/${it.inc()}.png")  }),
        PRICE_LIST(List(8) { TextureData("sprites/collection/price_list/${it.inc()}.png") }),
    }



    interface Sprite {
        val textureData: TextureData
    }

    interface SpriteList {
        val textureDataList: List<TextureData>
    }

}