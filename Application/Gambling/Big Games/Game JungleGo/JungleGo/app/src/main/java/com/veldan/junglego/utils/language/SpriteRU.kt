package com.veldan.junglego.utils.language

import com.veldan.junglego.assets.SpriteManager

object SpriteRU : LanguageSprite {

    override val PLAY    get() = SpriteManager.MenuRUSprite.PLAY.textureData.texture
    override val OPTIONS get() = SpriteManager.MenuRUSprite.OPTIONS.textureData.texture
    override val EXIT    get() = SpriteManager.MenuRUSprite.EXIT.textureData.texture

    override val SPIN    get() = SpriteManager.GameRUSprite.SPIN.textureData.texture
    override val WAIT    get() = SpriteManager.GameRUSprite.WAIT.textureData.texture
    override val BET     get() = SpriteManager.GameRUSprite.BET.textureData.texture
}