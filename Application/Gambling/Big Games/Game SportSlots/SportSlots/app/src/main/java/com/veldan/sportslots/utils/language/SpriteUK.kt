package com.veldan.sportslots.utils.language

import com.badlogic.gdx.graphics.Texture
import com.veldan.sportslots.assets.SpriteManager

object SpriteUK : LanguageSprite {
    override val play_def: Texture = SpriteManager.MenuSprite.PLAY_DEF_UK.textureData.texture
    override val play_press: Texture = SpriteManager.MenuSprite.PLAY_PRESS_UK.textureData.texture

    override val settings_def: Texture = SpriteManager.MenuSprite.SETTINGS_DEF_UK.textureData.texture
    override val settings_press: Texture = SpriteManager.MenuSprite.SETTINGS_PRESS_UK.textureData.texture

    override val exit_def: Texture = SpriteManager.MenuSprite.EXIT_DEF_UK.textureData.texture
    override val exit_press: Texture = SpriteManager.MenuSprite.EXIT_PRESS_UK.textureData.texture

    override val spin_def: Texture = SpriteManager.GameSprite.SPIN_DEF_UK.textureData.texture
    override val spin_press: Texture = SpriteManager.GameSprite.SPIN_PRESS_UK.textureData.texture
    override val spin_disable: Texture = SpriteManager.GameSprite.SPIN_DISABLE_UK.textureData.texture

    override val back_def: Texture = SpriteManager.CollectionSprite.BACK_DEF_UK.textureData.texture
    override val back_press: Texture = SpriteManager.CollectionSprite.BACK_PRESS_UK.textureData.texture
}